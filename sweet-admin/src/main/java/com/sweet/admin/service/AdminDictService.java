package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDict;
import com.sweet.admin.vo.AdminDictVo;
import com.sweet.service.service.BaseService;

import java.util.List;

/**
 * 数据字典 Service 接口
 */
public interface AdminDictService extends BaseService<AdminDict> {

    /**
     * 分页查询字典列表
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param dictType 字典类型（可选）
     * @param dictLabel 字典标签（可选，模糊查询）
     * @param status 状态（可选）
     * @return 分页结果
     */
    Page<AdminDict> getPage(Integer pageNo, Integer pageSize, String dictType, String dictLabel, Integer status);

    /**
     * 根据字典类型获取字典列表
     * @param dictType 字典类型
     * @return 字典列表
     */
    List<AdminDictVo> getDictItemsByType(String dictType);

    /**
     * 获取所有字典类型（去重后的类型列表）
     * @return 字典类型列表
     */
    List<String> getDictTypes();

    /**
     * 根据字典类型分组获取字典数据
     * @param dictTypes 字典类型列表
     * @return Map<字典类型，字典列表>
     */
    Object getDictItemsByTypes(List<String> dictTypes);
}
