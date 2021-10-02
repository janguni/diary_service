package diary.Service.repository;

import web.diaryservice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
//    Optional<Member> findByEmail(String email);
    Optional<Member> findById(String mem_id);
    List<Member> findAll();

}
