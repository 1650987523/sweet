package com.sweet.service.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.constant.AdminConstant;
import com.sweet.service.dto.TableQrcodeDto;
import com.sweet.service.dto.TableQrcodeGenerateDto;
import com.sweet.service.entity.TableQrcode;
import com.sweet.service.mapper.TableQrcodeMapper;
import com.sweet.service.service.CloudflareR2Service;
import com.sweet.service.service.TableQrcodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TableQrcodeServiceImpl extends BaseServiceImpl<TableQrcodeMapper, TableQrcode> implements TableQrcodeService {

    private final CloudflareR2Service cloudflareR2Service;

    @Override
    public Page<TableQrcode> getPage(Integer pageNo, Integer pageSize, String qrcodeName) {
        Page<TableQrcode> page = new Page<>(pageNo, pageSize);

        QueryWrapper<TableQrcode> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().
                like(StringUtils.hasText(qrcodeName), TableQrcode::getQrcodeName, qrcodeName );
        queryWrapper.lambda().orderByDesc(TableQrcode::getCreateTime);

        return super.page(page, queryWrapper);
    }

    @Override
    public boolean save(TableQrcode entity) {
        log.info("table qrcode save entity:{}", entity);

        //参数处理
        String qrcodeName = entity.getQrcodeName();
        String qrcodeNo = entity.getQrcodeNo();
        Map<String, Object> qrcodeInfo = entity.getQrcodeInfo();
        String qrcodeUrl = entity.getQrcodeUrl();
        Integer storeId = entity.getStoreId();

        Assert.hasText(qrcodeName, "桌码名称不能为空");
        Assert.hasText(qrcodeNo, "桌码编号不能为空");
        Assert.notEmpty(qrcodeInfo, "桌码信息不能为空");
        Assert.hasText(qrcodeUrl, "桌码图片url不能为空");
        Assert.notNull(storeId, "关联门店ID不能为空");

        return super.save(entity);
    }

    @Override
    public boolean updateById(TableQrcode entity) {

        //参数校验
        Integer id = entity.getId();
        String qrcodeName = entity.getQrcodeName();
        String qrcodeNo = entity.getQrcodeNo();
        Map<String, Object> qrcodeInfo = entity.getQrcodeInfo();
        String qrcodeUrl = entity.getQrcodeUrl();
        Assert.hasText(qrcodeName, "桌码名称不能为空");
        Assert.hasText(qrcodeNo, "桌码编号不能为空");
        Assert.notEmpty(qrcodeInfo, "桌码信息不能为空");
        Assert.hasText(qrcodeUrl, "桌码图片url不能为空");

        //要更新的二维码url和数据库的不一致则删除旧二维码url
        TableQrcode oldEntity = super.getById(id);
        String oldQrcodeUrl = oldEntity.getQrcodeUrl();
        if(!oldQrcodeUrl.equals(qrcodeUrl)){
            this.deleteQrcode(oldQrcodeUrl);
            log.info("table qrcode update upload id:{}, oldQrcodeUrl:{}, qrcodeUrl:{}",
                    id, oldQrcodeUrl, qrcodeUrl);
        }

        return super.updateById(entity);
    }

    @Override
    public String generateUploadQrcode(Integer storeId, String qrcodeNo, String qrcodeContent) {
        Assert.notNull(storeId, "关联门店ID不能为空");
        Assert.hasText(qrcodeNo, "桌码编号不能为空");
        Assert.hasText(qrcodeContent, "桌码关联内容不能为空");

        //生成二维码
        BufferedImage bufferedImage = QrCodeUtil.generate(qrcodeContent, new QrConfig());

        //生成文件名
        String key = String.format("%s/%s-%s.%s",
                AdminConstant.OSS_PRODUCT_PREFIX_QRCODE,
                storeId,
                qrcodeNo,
                ImgUtil.IMAGE_TYPE_PNG);

        //上传
        cloudflareR2Service.putObject(key, ImgUtil.toStream(bufferedImage, ImgUtil.IMAGE_TYPE_PNG));
        return cloudflareR2Service.generateUploadUrl(key);
    }

    @Override
    public boolean deleteQrcode(String qrCodeUrl) {
        log.info("deleteQrcode qrCodeUrl:{}", qrCodeUrl);

        try {
            URI uri = new URI(qrCodeUrl);
            String key = uri.getPath();
            cloudflareR2Service.deleteObject(key);
        }catch (Exception e){
            throw new RuntimeException("deleteQrcode failed" + e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean updateQrcodeUrl(Integer id, String qrcodeUrl) {
        UpdateWrapper<TableQrcode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(TableQrcode::getId, id);
        updateWrapper.lambda().set(TableQrcode::getQrcodeUrl, qrcodeUrl);
        return super.update(updateWrapper);
    }

    @Override
    public String generateQrcode(Long storeId, String qrcodeNo, TableQrcodeGenerateDto dto, String appId, String appSecret) {
        log.info("generateQrcode storeId:{}, qrcodeNo:{}, dto:{}", storeId, qrcodeNo, dto);

        try {
            String scene = dto.getScene();
            String page = dto.getPage();
            String envVersion = dto.getEnvVersion();
            Integer width = dto.getWidth();
            Boolean checkPath = dto.getCheckPath();
            Boolean autoColor = dto.getAutoColor();
            Boolean isHyaline = dto.getIsHyaline();
            WxMaCodeLineColor lineColor = dto.getLineColor();

            //参数校验
            Assert.hasText(scene, "场景值不能为空");
            Assert.hasText(page, "小程序页面路径不能为空");
            Assert.hasText(envVersion, "小程序版本不能为空");

            //配置微信小程序
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(appId);
            config.setSecret(appSecret);

            //小程序桌码生成
            WxMaService wxMaService = new WxMaServiceImpl();
            wxMaService.setWxMaConfig(config);
            byte[] wxaCodeUnlimitBytes = wxMaService.getQrcodeService().createWxaCodeUnlimitBytes(scene, page,
                    checkPath, envVersion, width, autoColor,
                    lineColor, isHyaline);
            InputStream inputStream = new ByteArrayInputStream(wxaCodeUnlimitBytes);

            //小程序桌码上传
            String key = String.format("%s/%s-%s.%s",
                    AdminConstant.OSS_PRODUCT_PREFIX_QRCODE,
                    storeId,
                    qrcodeNo,
                    ImgUtil.IMAGE_TYPE_PNG);
            cloudflareR2Service.putObject(key, new BufferedInputStream(inputStream));
            return cloudflareR2Service.generateUploadUrl(key);
        } catch (WxErrorException e) {
            log.error("generateQrcode error", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public boolean removeById(Serializable id) {

        //删除二维码
        TableQrcode tableQrcode = super.getById(id);
        this.deleteQrcode(tableQrcode.getQrcodeUrl());

        //删除数据表记录
        return super.removeById(id);
    }
}