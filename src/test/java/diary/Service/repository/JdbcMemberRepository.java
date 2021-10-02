package diary.Service.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import web.diaryservice.domain.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcMemberRepository implements web.diaryservice.repository.MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private web.diaryservice.repository.MemberMapper memberMapper;

    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Member member) {
        String sql = "insert into member(mem_id, mem_pw, mem_name, mem_age, mem_gender, mem_usertype) values (?,?,?,?,?,?)";

        int result = jdbcTemplate.update(sql, member.getMem_id(), member.getMem_pw(), member.getMem_name(),
                member.getMem_age(), member.getMem_gender(), member.getMem_usertype());
        //1이면 성공
        log.info("{}",result);
    }

//    @Override
//    public Optional<Member> findByEmail(String email) {
//        String sql = "select * from member where email = ?";
//        List<Member> result = jdbcTemplate.query(sql, memberMapper, email);
//        return result.stream().findAny();
//    }

    @Override
    public Optional<Member> findById(String mem_id) {
        String sql = "select * from member where mem_id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberMapper, mem_id);
        return result.stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return jdbcTemplate.query(sql, memberMapper);
    }
}
