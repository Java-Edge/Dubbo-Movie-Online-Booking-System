package com.javaedge.guns.rest.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author javaedge
 * @Date 2017年8月23日12:51:41
 */
@Configuration
@MapperScan(basePackages = {"com.javaedge.guns.rest.*.dao", "com.javaedge.guns.rest.common.persistence.dao"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
