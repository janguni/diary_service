package diary.Service.service;

import diary.Service.domain.Member;
import diary.Service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public String join(Member member) {
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public int login(String mem_id, String mem_pw, HttpServletRequest request) {
        Member member = memberRepository.findById(mem_id);
        HttpSession session = request.getSession();

        if(member!=null){
            if(member.getPw().equals(mem_pw)){
                session.setAttribute("mem_id", mem_id);
                log.info("1");
                return 1; //로그인 성공

            }else {
                log.info("0");
                return 0; //로그인 실패
            }
        }
        else{
            log.info("0");
            return 0; //로그인 실패
        }
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findById(member.getId());
        if(findMember!=null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
