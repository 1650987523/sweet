package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sweet.service.service.BaseService;

public class BaseServiceImpl<M extends com.sweet.service.mapper.BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T>{
}

