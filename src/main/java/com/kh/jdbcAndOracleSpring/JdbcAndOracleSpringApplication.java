package com.kh.jdbcAndOracleSpring;

import com.kh.jdbcAndOracleSpring.dao.EmpDAO;
import com.kh.jdbcAndOracleSpring.vo.EmpVO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JdbcAndOracleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcAndOracleSpringApplication.class, args);
	}

}
