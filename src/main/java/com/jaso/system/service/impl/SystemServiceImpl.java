package com.jaso.system.service.impl;

import com.jaso.system.mapper.SystemMapper;
import com.jaso.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/8.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemMapper systemMapper;


}
