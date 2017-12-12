package com.jaso.system.controller;

import com.jaso.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by dllo on 17/12/8.
 */
@Controller
public class SystemController {

    @Autowired
    @Qualifier("systemService")
    private SystemService systemService;


}
