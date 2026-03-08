package com.sweet.service.service.impl;

import com.sweet.service.entity.ProductLog;
import com.sweet.service.mapper.ProductLogMapper;
import com.sweet.service.service.ProductLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductLogServiceImpl extends BaseServiceImpl<ProductLogMapper, ProductLog> implements ProductLogService {
}
