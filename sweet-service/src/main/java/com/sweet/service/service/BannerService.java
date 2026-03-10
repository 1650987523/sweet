package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.BannerBatchSaveDto;
import com.sweet.service.dto.BannerUpdateStatusDto;
import com.sweet.service.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 轮播图 Service
 */
public interface BannerService extends BaseService<Banner> {

    /**
     * 获取轮播图分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param storeId 门店 ID
     * @param status 状态
     * @param linkType 跳转类型
     * @param title 轮播图标题
     * @return 分页结果
     */
    Page<Banner> getPage(Integer pageNo, Integer pageSize, Long storeId, Integer status, Integer linkType, String title);

    /**
     * 批量保存轮播图
     * @param dto
     * @return
     */
    boolean saveBatch(BannerBatchSaveDto dto);

    /**
     * 获取有效的轮播图列表（小程序端使用）
     *
     * @param storeId 门店 ID
     * @return 轮播图列表
     */
    List<Banner> getValidBanners(Long storeId);

    /**
     * 上传轮播图图片
     * @param storeId 门店 ID
     * @param files 图片文件列表
     * @return 上传后的 URL 列表
     */
    List<String> uploadBannerImages(Long storeId, List<MultipartFile> files);

    /**
     * 删除轮播图图片
     * @param imageUrls 图片 URL 列表
     * @return 是否删除成功
     */
    Boolean deleteBannerImages(List<String> imageUrls);

    /**
     * 更新轮播图状态
     * @param dto
     * @return
     */
    boolean updateStatus(BannerUpdateStatusDto dto);
}
