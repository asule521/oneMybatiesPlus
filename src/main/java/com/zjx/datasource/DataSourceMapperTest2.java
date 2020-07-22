package com.zjx.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author zhangjinxing
 * @date 2020/7/21 14:37
 */
@Configuration
@MapperScan(basePackages = "com.zjx.mapper2", sqlSessionFactoryRef = "test1SqlSessionFactory2")
public class DataSourceMapperTest2 {


	@Bean(name = "test1DataSource2")
	@ConfigurationProperties(prefix = "spring.datasource.druid.master")

	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * @methodDesc: 功能描述:(test1 sql会话工厂)
	 */
	@Bean(name = "test1SqlSessionFactory2")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource2") DataSource dataSource)
			throws Exception {
		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
		new
				PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml"));
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("params", "count=countSql");
		PaginationInterceptor pageInterceptor = new PaginationInterceptor();
		pageInterceptor.setProperties(properties);
		bean.setPlugins(pageInterceptor);

		return bean.getObject();
	}

	/**
	 * @methodDesc: 功能描述:(test1 事物管理)
	 */
	@Bean(name = "test1TransactionManager2")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource2") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "test1SqlSessionTemplate2")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
