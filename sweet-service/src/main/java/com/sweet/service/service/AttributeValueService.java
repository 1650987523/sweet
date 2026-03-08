package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.AttributeValue;

import java.util.List;

/**
 * 属性值 Service 接口
 */
public interface AttributeValueService extends BaseService<AttributeValue> {

    /**
     * 分页查询属性值
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param valueName 属性值名称
     * @return 分页结果
     */
    Page<AttributeValue> getPage(Integer pageNo, Integer pageSize, String valueName);

    /**
     * 根据属性 ID 获取属性值列表
     *
     * @param attributeId 属性 ID
     * @return 属性值列表
     */
    List<AttributeValue> getByAttributeId(Long attributeId);

    /**
     * 根据属性 ID 列表批量获取属性值列表
     *
     * @param attributeIds 属性 ID 列表
     * @return 属性值列表
     */
    List<AttributeValue> getByAttributeIds(List<Long> attributeIds);

    /**
     * 根据属性 ID 批量删除属性值
     *
     * @param attributeId 属性 ID
     * @return 是否删除成功
     */
    boolean removeByAttributeId(Long attributeId);
}