package com.zjx.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zjx.mapper", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourceMapperTest {


	@Bean(name = "test1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
	public DataSource testDataSource() {
		 return DataSourceBuilder.create().build();
	}


 
	/**
	 * @methodDesc: 功能描述:(test1 sql会话工厂)
	 */
	@Bean(name = "test1SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
			throws Exception {
		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new
						PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml"));


		 //自己设置分页插件  需要在启动类(exclude ={PageHelperAutoConfiguration.class})去掉自动添加分页插件
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
	@Bean(name = "test1TransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
 
	@Bean(name = "test1SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
