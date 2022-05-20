package com.bitc.board2.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

  @Autowired
  private ApplicationContext appContext;

  @Bean
//    @ConfigurationProperties : @PropertySource 어노테이션을 통해서 지정한 파일 내에 있는 설정 중 prefix 로 지정한 설정을 가져옴
//    application.properties 파일에서 설정했던 데이터베이스 관련 설정을 가져와서 사용하도록 지정
  @ConfigurationProperties(prefix="spring.datasource.hikari")
  public HikariConfig hikariConfig() {
    return new HikariConfig();
  }

  //    위에서 생성한 히카리cp의 설정 파일을 이용하여 데이터 베이스와 연결하는 데이터소스를 생성
  @Bean
  public DataSource dataSource() throws Exception {
    DataSource dataSource = new HikariDataSource(hikariConfig());
    System.out.println(dataSource.toString());
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
    ssfb.setDataSource(dataSource);
//        ORM인 Mybatis를 사용하여 xml 파일의 내용을 불러옴
//        sql 쿼리문이 입력되어 있는 xml 파일의 위치를 지정함
//        /**/ 는 모든 폴더를 뜻함 (폴더 단계에 관계없이 지정한 파일형식대로 검색하여 사용하겠다는 의미)
//        sql-*.xml 은 첫두사는 sql-이고, 접미사는 .xml인 모든 파일을 검색하여 사용
    ssfb.setMapperLocations(appContext.getResources("classpath:/sql/sql-*.xml"));
    ssfb.setConfiguration(mybatisConfig());
    return ssfb.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  //    application.properties 에 설정된 마이바티스 관련 설정을 불러와서 사용
//    카멜명명법을 사용한다는 내요을 로드함
  @Bean
  @ConfigurationProperties(prefix = "mybatis.configuration")
  public org.apache.ibatis.session.Configuration mybatisConfig() {
    return new org.apache.ibatis.session.Configuration();
  }
}
