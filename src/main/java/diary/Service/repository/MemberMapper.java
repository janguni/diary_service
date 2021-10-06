package diary.Service.repository;

import diary.Service.domain.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MemberMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();

        member.setMem_id(rs.getString("mem_id"));
        member.setMem_pw(rs.getString("mem_pw"));
        member.setMem_name(rs.getString("mem_name"));
        member.setMem_age(rs.getInt("mem_age"));
        member.setMem_gender(rs.getString("mem_gender"));
        member.setMem_usertype(rs.getInt("mem_usertype"));

        return member;
    }
}
