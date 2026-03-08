package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.AttributeDTO;
import com.sweet.service.entity.Attribute;
import com.sweet.service.vo.AttributeOptionVO;

import java.util.List;

/**
 * 商品属性模板 Service 接口
 */
public interface AttributeService extends BaseService<Attribute> {

    /**
     * 获取属性分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param storeId 门店 ID
     * @param name 属性名称
     * @param type 属性类型
     * @param status 状态
     * @return 分页结果
     */
    Page<Attribute> getPage(Integer pageNo, Integer pageSize, Long storeId, String name, Integer type, Integer status);

    /**
     * 获取属性选项列表（用于下拉选择）
     *
     * @param storeId 门店 ID
     * @param attributeType 属性类型
     * @return 属性选项列表
     */
    List<AttributeOptionVO> getOptionList(Long storeId, Integer attributeType);

    /**
     * 新增属性（包含属性值）
     *
     * @param dto 属性 DTO
     * @return 属性 ID
     */
    Long saveAttribute(AttributeDTO dto);

    /**
     * 更新属性（包含属性值）
     *
     * @param dto 属性 DTO
     * @return 是否更新成功
     */
    boolean updateAttribute(AttributeDTO dto);

    /**
     * 删除属性（同时删除关联的属性值）
     *
     * @param id 属性 ID
     * @return 是否删除成功
     */
    boolean removeAttribute(Long id);

    /**
     * 根据门店 ID 获取属性模板列表（包含属性值）
     *
     * @param storeId 门店 ID
     * @return 属性模板及属性值列表
     */
    List<com.sweet.service.vo.AttributeWithValueVO> getAttributeTemplates(Long storeId);
}
