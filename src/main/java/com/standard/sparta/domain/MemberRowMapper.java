package com.standard.sparta.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  JDBC ResultSet 의 행을 Member 객체로 매핑하는 클래스.
 *  RowMapper 를 구현하며 데이터베이스에서 조회한 회원 정보를 Member 객체로 변환합니다.
 */
public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Member(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("name"),
                rs.getBoolean("is_deleted")
        );
    }
}
