package com.board.configuration;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// 스프링 은 @configuration이 지정된 클래스를 자바 기반의 설정 파일로 인식
@PropertySource("classpath:/application.properties")
// 해당 클래스에서 참조할 properties 파일의 위치를 지정
public class DBConfiguration {

	@Autowired
	//빈으로 등록된 인스턴스를 클래스에 주입하는데 사용합니다.
	private ApplicationContext applicationContext;
	// 스프링 컨테이너중 하나 무언가를 담는 용기 또는 그릇을 의미하는데 Bean의 생성과 사용 관계 생명 주기등을 관리함 // bean 은 객체
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());						
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		factoryBean.setTypeAliasesPackage("com.board.domain");
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
	
}