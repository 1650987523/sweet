package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.AttributeDTO;
import com.sweet.service.dto.AttributeValueDTO;
import com.sweet.service.entity.Attribute;
import com.sweet.service.entity.AttributeValue;
import com.sweet.service.mapper.AttributeMapper;
import com.sweet.service.service.AttributeService;
import com.sweet.service.service.AttributeValueService;
import com.sweet.service.vo.AttributeOptionVO;
import com.sweet.service.vo.AttributeWithValueVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品属性模板 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttributeServiceImpl extends BaseServiceImpl<AttributeMapper, Attribute> implements AttributeService {

    private final AttributeValueService attributeValueService;

    @Override
    public Page<Attribute> getPage(Integer pageNo, Integer pageSize, Long storeId, String name, Integer type, Integer status) {
        Page<Attribute> page = new Page<>(pageNo, pageSize);

        return super.page(page, Wrappers.<Attribute>lambdaQuery()
                .eq(Objects.nonNull(storeId), Attribute::getStoreId, storeId)
                .like(StringUtils.hasText(name), Attribute::getName, name)
                .eq(Objects.nonNull(type), Attribute::getType, type)
                .eq(Objects.nonNull(status), Attribute::getStatus, status)
                .orderByAsc(Attribute::getSort, Attribute::getId));
    }

    @Override
    public List<AttributeOptionVO> getOptionList(Long storeId, Integer attributeType) {
        List<Attribute> attributes = super.list(Wrappers.<Attribute>lambdaQuery()
                .eq(Attribute::getStatus, 1)
                .eq(Objects.nonNull(storeId), Attribute::getStoreId, storeId)
                .eq(Objects.nonNull(attributeType), Attribute::getType, attributeType)
                .orderByAsc(Attribute::getSort, Attribute::getId));

        return attributes.stream().map(attribute -> new AttributeOptionVO()
            .setId(attribute.getId())
            .setName(attribute.getName())
            .setType(attribute.getType())
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveAttribute(AttributeDTO dto) {
        log.info("saveAttribute dto:{}", dto);

        // 1. 保存属性
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(dto, attribute);
        super.save(attribute);
        Long attributeId = attribute.getId();

        // 2. 保存属性值
        List<AttributeValueDTO> values = dto.getValues();
        if (!CollectionUtils.isEmpty(values)) {
            List<AttributeValue> saveValues = new ArrayList<>();
            for (AttributeValueDTO valueDTO : values) {
                AttributeValue value = new AttributeValue()
                    .setAttributeId(attributeId)
                    .setValue(valueDTO.getValue())
                    .setSort(valueDTO.getSort());
                saveValues.add(value);
            }
            attributeValueService.saveBatch(saveValues);
        }

        log.info("saveAttribute success attributeId:{}", attributeId);
        return attributeId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAttribute(AttributeDTO dto) {
        log.info("updateAttribute dto:{}", dto);

        if (Objects.isNull(dto.getId())) {
            throw new RuntimeException("属性 ID 不能为空");
        }

        // 1. 获取原有属性
        Attribute oldAttribute = super.getById(dto.getId());
        if (Objects.isNull(oldAttribute)) {
            throw new RuntimeException("属性不存在");
        }

        // 2. 更新属性
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(dto, attribute);
        super.updateById(attribute);

        // 3. 删除原有属性值
        attributeValueService.removeByAttributeId(dto.getId());

        // 4. 保存新属性值
        List<AttributeValueDTO> values = dto.getValues();
        if (!CollectionUtils.isEmpty(values)) {
            List<AttributeValue> saveValues = new ArrayList<>();
            for (AttributeValueDTO valueDTO : values) {
                AttributeValue value = new AttributeValue()
                    .setAttributeId(dto.getId())
                    .setValue(valueDTO.getValue())
                    .setSort(valueDTO.getSort());
                saveValues.add(value);
            }
            attributeValueService.saveBatch(saveValues);
        }

        log.info("updateAttribute success attributeId:{}", dto.getId());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAttribute(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return false;
        }
        log.info("removeAttribute id:{}", id);

        // 1. 获取属性
        Attribute attribute = super.getById(id);
        if (Objects.isNull(attribute)) {
            throw new RuntimeException("属性不存在");
        }

        // 2. 删除属性值
        attributeValueService.removeByAttributeId(id);

        // 3. 删除属性
        super.removeById(id);

        log.info("removeAttribute success id:{}", id);
        return true;
    }

    @Override
    public List<AttributeWithValueVO> getAttributeTemplates(Long storeId) {
        // 1. 查询属性列表
        List<Attribute> attributes = super.list(Wrappers.<Attribute>lambdaQuery()
                .eq(Objects.nonNull(storeId), Attribute::getStoreId, storeId)
                .eq(Attribute::getStatus, 1)
                .orderByAsc(Attribute::getSort, Attribute::getId));

        if (CollectionUtils.isEmpty(attributes)) {
            return List.of();
        }

        // 2. 批量查询所有属性值（IN 查询）
        List<Long> attributeIds = attributes.stream().map(Attribute::getId).toList();
        List<AttributeValue> allValues = attributeValueService.getByAttributeIds(attributeIds);

        // 3. 按 attributeId 分组
        Map<Long, List<AttributeValue>> valuesGroupedByAttributeId = allValues.stream()
                .collect(Collectors.groupingBy(AttributeValue::getAttributeId));

        // 4. 构建结果
        return attributes.stream().map(attribute -> {
            AttributeWithValueVO vo = new AttributeWithValueVO()
                .setId(attribute.getId())
                .setName(attribute.getName())
                .setType(attribute.getType())
                .setSort(attribute.getSort());

            // 5. 分配对应的属性值
            List<AttributeValue> values = valuesGroupedByAttributeId.getOrDefault(attribute.getId(), List.of());
            vo.setValues(values);

            return vo;
        }).toList();
    }
}
