package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.AttributeValue;
import com.sweet.service.mapper.AttributeValueMapper;
import com.sweet.service.service.AttributeValueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 属性值 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttributeValueServiceImpl extends BaseServiceImpl<AttributeValueMapper, AttributeValue> implements AttributeValueService {

    @Override
    public Page<AttributeValue> getPage(Integer pageNo, Integer pageSize, String valueName) {
        Page<AttributeValue> page = new Page<>(pageNo, pageSize);

        return super.page(page, Wrappers.<AttributeValue>lambdaQuery()
                .like(StringUtils.hasText(valueName), AttributeValue::getValue, valueName));
    }

    @Override
    public List<AttributeValue> getByAttributeId(Long attributeId) {
        if (ObjectUtils.isEmpty(attributeId)) {
            return List.of();
        }
        return super.list(Wrappers.<AttributeValue>lambdaQuery()
                .eq(AttributeValue::getAttributeId, attributeId));
    }

    @Override
    public boolean removeByAttributeId(Long attributeId) {
        if (ObjectUtils.isEmpty(attributeId)) {
            return false;
        }
        return super.remove(Wrappers.<AttributeValue>lambdaQuery()
                .eq(AttributeValue::getAttributeId, attributeId));
    }

    @Override
    public List<AttributeValue> getByAttributeIds(List<Long> attributeIds) {
        if (CollectionUtils.isEmpty(attributeIds)) {
            return List.of();
        }
        return super.list(Wrappers.<AttributeValue>lambdaQuery()
                .in(AttributeValue::getAttributeId, attributeIds));
    }
}