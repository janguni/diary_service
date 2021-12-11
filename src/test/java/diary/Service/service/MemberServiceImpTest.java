package diary.Service.service;

import diary.Service.domain.Member;
import diary.Service.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;


@WebAppConfiguration
//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //나중에 rollback
class MemberServiceImpTest {
        @Autowired
        MemberService memberService;

        @Autowired
        MemberRepository memberRepository;

        @Autowired
        EntityManager em;

        @Test
        public void 회원가입() throws Exception{
            //given
            Member member = new Member("memberid_1","memberpw","장윤희",23,"w",1);

            //when
            String savedId = memberService.join(member);

            //then
            //em.flush();
            Assert.assertEquals(member,memberRepository.findById(savedId));

        }

        @Test
        public void 중복_회원_예외() throws Exception {
            //given
            Member member1 = new Member("id1", "pw", "양충욱", 24, "m", 1);
            Member member2 = new Member("id1", "pw", "양충욱", 24, "m", 1);

            //when
            memberService.join(member1);
            try{
                memberService.join(member2);
            }catch(IllegalStateException e){
                return;
            }

            //then
            fail("예외가 발생해야 합니다.");

        }

    }