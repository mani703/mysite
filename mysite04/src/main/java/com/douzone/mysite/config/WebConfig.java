package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;

@Configuration
@EnableAspectJAutoProxy // <!-- auto proxy -->
@ComponentScan({"douzone.mysite.controller", "com.douzone.mysite.exception"}) // <context:component-scan
@Import({MvcConfig.class, MessageConfig.class})
public class WebConfig {

}
