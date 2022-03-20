package com.zwf.data.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.zwf.data.config.ZwfMybatisProperties;
import com.zwf.data.datascope.DataScopeHandle;
import com.zwf.data.datascope.DataScopeInnerInterceptor;
import com.zwf.data.datascope.DataScopeSqlInjector;
import com.zwf.data.datascope.ZwfDefaultDatascopeHandle;
import com.zwf.data.resolver.SqlFilterArgumentResolver;
import com.zwf.data.tenant.ZwfTenantHandler;
import com.zwf.security.service.ZwfUser;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:24:15
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(ZwfMybatisProperties.class)
public class MybatisPlusConfiguration implements WebMvcConfigurer {

    /**
     * 增加请求参数解析器，对请求中的参数注入SQL 检查
     * @param resolverList
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList) {
        resolverList.add(new SqlFilterArgumentResolver());
    }

    /**
     * mks 默认数据权限处理器
     * @return MksDefaultDatascopeHandle
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(ZwfUser.class)
    public DataScopeHandle dataScopeHandle() {
        return new ZwfDefaultDatascopeHandle();
    }

    /**
     * mybatis plus 拦截器配置
     * @return MksDefaultDatascopeHandle
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户支持
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(zwfTenantHandler());
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
        // 数据权限
        DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor();
        dataScopeInnerInterceptor.setDataScopeHandle(dataScopeHandle());
        interceptor.addInnerInterceptor(dataScopeInnerInterceptor);
        // 分页支持
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(1000L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    /**
     * 创建租户维护处理器对象
     * @return 处理后的租户维护处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public ZwfTenantHandler zwfTenantHandler() {
        return new ZwfTenantHandler();
    }

    /**
     * 扩展 mybatis-plus baseMapper 支持数据权限
     * @return
     */
    @Bean
    @ConditionalOnBean(DataScopeHandle.class)
    public DataScopeSqlInjector dataScopeSqlInjector() {
        return new DataScopeSqlInjector();
    }

    /**
     * SQL 日志格式化
     * @return DruidSqlLogFilter
     */
    @Bean
    public DruidSqlLogFilter sqlLogFilter(ZwfMybatisProperties properties) {
        return new DruidSqlLogFilter(properties);
    }

}
