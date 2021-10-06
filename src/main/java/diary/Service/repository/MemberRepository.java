package diary.Service.repository;


import diary.Service.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository {
    void save(Member member);
//    Optional<Member> findByEmail(String email);
    Optional<Member> findById(String mem_id);
    List<Member> findAll();

}
