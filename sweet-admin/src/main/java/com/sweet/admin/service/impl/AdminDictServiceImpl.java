package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDict;
import com.sweet.admin.mapper.AdminDictMapper;
import com.sweet.admin.service.AdminDictService;
import com.sweet.admin.vo.AdminDictVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据字典 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class AdminDictServiceImpl extends BaseServiceImpl<AdminDictMapper, AdminDict> implements AdminDictService {

    @Override
    public Page<AdminDict> getPage(Integer pageNo, Integer pageSize, String dictType, String dictLabel, Integer status) {
        Page<AdminDict> page = new Page<>(pageNo, pageSize);

        QueryWrapper<AdminDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.hasText(dictType), AdminDict::getDictType, dictType)
                .like(StringUtils.hasText(dictLabel), AdminDict::getDictLabel, dictLabel)
                .eq(status != null, AdminDict::getStatus, status)
                .orderByAsc(AdminDict::getSort, AdminDict::getId);

        return super.page(page, queryWrapper);
    }

    @Override
    public List<AdminDictVo> getDictItemsByType(String dictType) {
        QueryWrapper<AdminDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AdminDict::getDictType, dictType)
                .eq(AdminDict::getStatus, 1)  // 只查询启用的
                .orderByAsc(AdminDict::getSort);

        List<AdminDict> dicts = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(dicts)) {
            return List.of();
        }

        return dicts.stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDictTypes() {
        QueryWrapper<AdminDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(AdminDict::getDictType).groupBy(AdminDict::getDictType);
        List<AdminDict> dicts = super.list(queryWrapper);
        return dicts.stream()
                .map(AdminDict::getDictType)
                .filter(StringUtils::hasText)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Object getDictItemsByTypes(List<String> dictTypes) {
        QueryWrapper<AdminDict> queryWrapper = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(dictTypes)) {
            queryWrapper.lambda().in(AdminDict::getDictType, dictTypes);
        }
        queryWrapper.lambda()
                .eq(AdminDict::getStatus, 1)
                .orderByAsc(AdminDict::getDictType, AdminDict::getSort);

        List<AdminDict> dicts = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(dicts)) {
            return Map.of();
        }

        // 按 dictType 分组
        return dicts.stream()
                .collect(Collectors.groupingBy(
                        AdminDict::getDictType,
                        Collectors.mapping(this::convertToVo, Collectors.toList())
                ));
    }

    /**
     * 转换为 VO
     */
    private AdminDictVo convertToVo(AdminDict dict) {
        AdminDictVo vo = new AdminDictVo();
        BeanUtils.copyProperties(dict, vo);
        return vo;
    }
}
