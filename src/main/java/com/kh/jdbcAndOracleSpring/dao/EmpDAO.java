package com.kh.jdbcAndOracleSpring.dao;

import com.kh.jdbcAndOracleSpring.vo.EmpVO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmpDAO {
    private final JdbcTemplate jdbcTemplate;

    public EmpDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<EmpVO> empSelect() {
        String sql = "select * from emp";
        return jdbcTemplate.query(sql, new EmpRowMapper());
    }
    public boolean empInsert(EmpVO vo) {
        int result = 0;
        String sql = "INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, " +
                "HIREDATE, SAL, COMM, DEPTNO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(sql, vo.getEmpNO(), vo.getName(), vo.getJob()
                    , vo.getMgr(), vo.getDate(),vo.getSal(), vo.getComm(), vo.getDeptNO());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;

    }

    public void empUpdate(EmpVO emp) {
        String sql = "UPDATE EMP SET JOB = ?, HIREDATE = ?, SAL = ?, COMM = ?, WHERE ENAME = ?;";
        jdbcTemplate.update(sql,emp.getJob(), emp.getSal(), emp.getComm(), emp.getName());


    }

    public boolean empDelete(String name) {
        int result = 0;
        String sql = "DELETE FROM EMP WHERE EMPNO = ?";
        try {
            result = jdbcTemplate.update(sql, name);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }




    private static class EmpRowMapper implements RowMapper<EmpVO> {

        @Override
        public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EmpVO(
                    rs.getInt("EMPNO"),
                    rs.getString("ENAME"),
                    rs.getString("JOB"),
                    rs.getInt("MGR"),
                    rs.getDate("HIREDATE"),
                    rs.getBigDecimal("SAL"),
                    rs.getBigDecimal("COMM"),
                    rs.getInt("DEPTNO")
            );
        }
    }
    public void empSelectResult(List<EmpVO> list) {
        System.out.println("-".repeat(60));
        System.out.println(" ".repeat(25) + "사원  정보");
        System.out.println("-".repeat(60));
        for(EmpVO vo : list) {
            System.out.print(vo.getEmpNO() + " ");
            System.out.print(" ".repeat(6- vo.getName().length()) + vo.getName() + " ");
            System.out.print(" ".repeat(10- vo.getJob().length()) + vo.getJob() + " ");
            System.out.print(vo.getMgr() + " ");
            System.out.print(vo.getDate() + " ");
            System.out.print(vo.getSal() + " ");
            System.out.print(vo.getComm() + " ");
            System.out.print(vo.getDeptNO() + " ");
            System.out.println();
        }
        System.out.println("-".repeat(60));
    }
}
