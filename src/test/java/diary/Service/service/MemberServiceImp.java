package diary.Service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.diaryservice.domain.Member;
import web.diaryservice.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImp implements MemberService{

    MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int login(String mem_id, String mem_pw, HttpServletRequest request) {
        Optional<Member> member = memberRepository.findById(mem_id);
        HttpSession session = request.getSession(); //세션을 만들엇

        if(member.isPresent()){
            if(member.get().getMem_pw().equals(mem_pw)){
                session.setAttribute("mem_id", mem_id); //로그인에 성공할 경우 세션 값 저장
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
}
