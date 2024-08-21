package com.standard.sparta.repository;

import com.standard.sparta.domain.Member;
import com.standard.sparta.domain.MemberRowMapper;
import com.standard.sparta.dto.MemberDeleteRequestDto;
import com.standard.sparta.dto.MemberUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Slf4j
@Repository
public class MemberJdbcRepository implements MemberRepository {

    // 1. 속성
    private final JdbcTemplate jdbcTemplate;

    // 2. 생성자
    public MemberJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 3. 메서드
    /**
     * 회원 저장.
     * @param newMember 저장할 회원 엔티티
     * @return Member 저장된 회원 엔티티
     */
    @Override
    public Member save(Member newMember) {
        log.info("::: - - MemberJdbcRepository - save() :::");
        // 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO member (name, email, is_deleted) VALUES (?, ?, ?)";

        int affectedRow = jdbcTemplate.update(connection -> {
            // PreparedStatement 생성
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 파라미터 설정
            ps.setString(1, newMember.getName());
            ps.setString(2, newMember.getEmail());
            ps.setBoolean(3, newMember.isDeleted());
            return ps;
        }, keyHolder);

        // 조회
        if (affectedRow == 1) {
            long saveMemberId = keyHolder.getKey().longValue();
            return findById(saveMemberId);

        } else {
            return null;
        }
    }


    /**
     * 아이디로 회원 조회.
     * @param memberId 회원 아이디
     * @return Member 조회된 엔티티
     */
    @Override
    public Member findById(Long memberId) {
        log.info("::: - - MemberJdbcRepository - findById() :::");
        String sql = "SELECT id, name, email, is_deleted FROM member WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), memberId);
    }

    /**
     * 모든 회원 조회.
     * @return 멤버 엔티티 리스트
     */
    @Override
    public List<Member> findAll() {
        log.info("::: - - MemberJdbcRepository - findAll() :::");
        String sql = "SELECT id, email, name, is_deleted FROM member WHERE is_deleted=false";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    /**
     * 회원 정보 수정.
     * @param data 회정 정보 수정을 위한 DTO 객체
     * @return Member
     */
    @Override
    public Member update(MemberUpdateRequestDto data) {
        log.info("::: - - MemberJdbcRepository - update() :::");
        String sql = "UPDATE member SET name = ?, email = ? WHERE id = ? AND is_deleted=false";
        int affectedRow = jdbcTemplate.update(sql, data.getName(), data.getEmail(), data.getId());

        if (affectedRow == 1) {
            return findById(data.getId());

        } else {
            return null;
        }
    }

    /**
     * 회원 정보 삭제.
     * 회원 정보 삭제처리는 is_deleted=true 변경처리하는 것입니다.
     * @param data 회원 정보 삭제를 위한 DTO 객체
     * @return Member
     */
    @Override
    public Member delete(MemberDeleteRequestDto data) {
        log.info("::: - - MemberJdbcRepository - delete() :::");
        String sql = "UPDATE member SET is_deleted = true WHERE id = ? AND is_deleted=false";
        int affectedRow = jdbcTemplate.update(sql, data.getId());
        if (affectedRow == 1) {
            return findById(data.getId());

        } else {
            return null;
        }
    }
}
