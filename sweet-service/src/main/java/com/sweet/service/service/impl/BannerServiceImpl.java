package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.util.FileExtUtil;
import com.sweet.service.dto.BannerBatchSaveDto;
import com.sweet.service.dto.BannerUpdateStatusDto;
import com.sweet.service.entity.Banner;
import com.sweet.service.mapper.BannerMapper;
import com.sweet.service.service.BannerService;
import com.sweet.service.service.CloudflareR2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class BannerServiceImpl extends BaseServiceImpl<BannerMapper, Banner> implements BannerService {

    private final CloudflareR2Service cloudflareR2Service;

    @Override
    public Page<Banner> getPage(Integer pageNo, Integer pageSize, Long storeId, Integer status, Integer linkType, String title) {
        Page<Banner> page = new Page<>(pageNo, pageSize);

        return super.page(page, Wrappers.<Banner>lambdaQuery()
                .eq(Objects.nonNull(storeId), Banner::getStoreId, storeId)
                .eq(Objects.nonNull(status), Banner::getStatus, status)
                .eq(Objects.nonNull(linkType), Banner::getLinkType, linkType)
                .like(StringUtils.hasText(title), Banner::getTitle, title)
                .orderByDesc(Banner::getSortOrder, Banner::getId));
    }

    @Override
    public boolean saveBatch(BannerBatchSaveDto dto) {

        List<Banner> saveBanners = new ArrayList<>();

        List<String> imageUrls = dto.getImageUrls();
        for (String imageUrl : imageUrls) {
            Banner banner = new Banner();
            BeanUtils.copyProperties(dto, banner);
            banner.setImageUrl(imageUrl);
            saveBanners.add(banner);
        }
        return super.saveBatch(saveBanners);
    }

    @Override
    public List<Banner> getValidBanners(Long storeId) {
        LocalDateTime now = LocalDateTime.now();

       return null;
    }

    @Override
    public List<String> uploadBannerImages(Long storeId, List<MultipartFile> files) {
        try {
            // 参数校验
            Assert.notNull(storeId, "门店 ID 不能为空");
            Assert.notEmpty(files, "图片列表不能为空");

            // 上传
            Map<String, InputStream> imageKeyToInputStream = new HashMap<>();
            List<String> imageKeys = new ArrayList<>();
            for (MultipartFile file : files) {
                String key = String.format("%s/%s-%s.%s",
                        AdminConstant.OSS_PRODUCT_PREFIX_BANNER,
                        storeId,
                        System.currentTimeMillis(),
                        FileExtUtil.getExtension(file.getOriginalFilename()));
                imageKeyToInputStream.put(key, file.getInputStream());
                imageKeys.add(key);
            }
            cloudflareR2Service.putBatchObjects(imageKeyToInputStream);

            // 返回上传后的 URL 列表
            List<String> urls = new ArrayList<>();
            for (String imageKey : imageKeys) {
                String url = cloudflareR2Service.generateUploadUrl(imageKey);
                urls.add(url);
            }
            return urls;
        } catch (Exception e) {
            log.error("uploadBannerImages failed", e);
            throw new RuntimeException("上传轮播图图片失败：" + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteBannerImages(List<String> imageUrls) {
        if (CollectionUtils.isEmpty(imageUrls)) {
            return true;
        }

        for (String imageUrl : imageUrls) {
            cloudflareR2Service.deleteObject(imageUrl);
        }

        return true;
    }

    @Override
    public boolean updateStatus(BannerUpdateStatusDto dto) {
        log.info("updateStatus dto:{}", dto);

        Long id = dto.getId();
        Integer status = dto.getStatus();

        Assert.notNull(id, "轮播图 ID 不能为空");
        Assert.notNull(status, "轮播图状态 不能为空");

        UpdateWrapper<Banner> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Banner::getId, id)
                .set(Banner::getStatus, status);
        return super.update(updateWrapper);
    }
}
