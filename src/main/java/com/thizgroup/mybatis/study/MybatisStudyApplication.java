package com.thizgroup.mybatis.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.thizgroup.mybatis.study.mapper")
@SpringBootApplication
public class MybatisStudyApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisStudyApplication.class,args);
  }
}
