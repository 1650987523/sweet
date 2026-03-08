package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.TableQrcodeDto;
import com.sweet.service.entity.TableQrcode;
import org.springframework.web.multipart.MultipartFile;

public interface TableQrcodeService extends BaseService<TableQrcode>{

    /**
     * 获取桌码分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param qrcodeName 桌号名称
     * @return 分页结果
     */
    Page<TableQrcode> getPage(Integer pageNo, Integer pageSize, String qrcodeName);

    /**
     * 生成并上传桌码图片
     * @param storeId
     * @param qrcodeNo
     * @param qrcodeContent
     * @return
     */
    String generateUploadQrcode(Integer storeId, String qrcodeNo, String qrcodeContent);

    /**
     * 删除桌码图片
     * @param key
     * @return
     */
    boolean deleteQrcode(String key);

    /**
     * 更新桌码图片url
     * @param id
     * @param qrcodeUrl
     * @return
     */
    boolean updateQrcodeUrl(Integer id, String qrcodeUrl);
}