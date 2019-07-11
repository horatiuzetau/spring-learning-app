package com.reply.ro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages = {"models", "repository", "service", "mvc"})
public class ProjectConfig {
}
