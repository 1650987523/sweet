package com.sweet.service.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.constant.AdminConstant;
import com.sweet.service.dto.TableQrcodeDto;
import com.sweet.service.entity.TableQrcode;
import com.sweet.service.mapper.TableQrcodeMapper;
import com.sweet.service.service.CloudflareR2Service;
import com.sweet.service.service.TableQrcodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.URI;

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
        String qrcodeContent = entity.getQrcodeContent();
        Integer storeId = entity.getStoreId();

        Assert.hasText(qrcodeName, "桌码名称不能为空");
        Assert.hasText(qrcodeNo, "桌码编号不能为空");
        Assert.hasText(qrcodeContent, "桌码内容不能为空");
        Assert.notNull(storeId, "关联门店ID不能为空");

        //保存
        boolean ret = super.save(entity);

        //更新二维码
        Integer id = entity.getId();
        String qrcodeUrl  = this.generateUploadQrcode(storeId, qrcodeNo, qrcodeContent);
        this.updateQrcodeUrl(id, qrcodeUrl);
        log.info("table qrcode save id:{}, qrcodeUrl:{}", id, qrcodeUrl);

        return ret;
    }

    @Override
    public boolean updateById(TableQrcode entity) {

        //参数校验
        Integer id = entity.getId();
        String qrcodeName = entity.getQrcodeName();
        String qrcodeNo = entity.getQrcodeNo();
        String qrcodeContent = entity.getQrcodeContent();
        Assert.hasText(qrcodeName, "桌码名称不能为空");
        Assert.hasText(qrcodeNo, "桌码编号不能为空");
        Assert.hasText(qrcodeContent, "桌码关联内容不能为空");

        //获取详情
        TableQrcode oldEntity = super.getById(id);
        String oldQrcodeContent = oldEntity.getQrcodeContent();

        //对比关联内容(1.存在变化出发删除操作,并重新上传二维码 2.不存在变化则不需要删除步骤)
        String newQrcodeContent = entity.getQrcodeContent();
        String oldQrcodeUrl = oldEntity.getQrcodeUrl();
        String qrcodeUrl = entity.getQrcodeUrl();
        Integer storeId = entity.getStoreId();
        if(!oldQrcodeContent.equals(newQrcodeContent)){

            //删除旧二维码
            this.deleteQrcode(oldQrcodeUrl);

            //上传新二维码
            qrcodeUrl = this.generateUploadQrcode(storeId, qrcodeNo, qrcodeContent);
            log.info("table qrcode update upload id:{}, qrcodeUrl:{}", id, qrcodeUrl);
        }

        //更新
        entity.setQrcodeUrl(qrcodeUrl);
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
    public boolean removeById(Serializable id) {

        //删除二维码
        TableQrcode tableQrcode = super.getById(id);
        this.deleteQrcode(tableQrcode.getQrcodeUrl());

        //删除数据表记录
        return super.removeById(id);
    }
}