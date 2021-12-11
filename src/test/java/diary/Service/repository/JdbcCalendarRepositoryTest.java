package diary.Service.repository;

import diary.Service.domain.*;
import diary.Service.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class JdbcCalendarRepositoryTest {

    @Autowired
    MemberService memberService;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    EntityManager em;

    private MockMvc mockMvc;
    protected MockHttpSession session;

    @Test
    public void 캘린더_저장() throws Exception {
        //given
        Member member = new Member("yellow0719", "memberPw", "장윤희", 23, "w", 1);
        memberService.join(member);

        Date date = new Date("2021-07-26", "2021-10-25");
        TextInfo textInfo = new TextInfo("제목", "글쓴이", "내용");
        Color color = new Color("#ffffff", "#45c99a", "#ffffff");
        Calendar calendar = Calendar.createCalendar(member, textInfo, date, true, color);

        session = new MockHttpSession();    //세션 생성
        session.setAttribute("mem_id", "yellow0719");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);


        //when
        Long savedId = calendarRepository.save(calendar, request);


        //then
        Assert.assertEquals(calendar, calendarRepository.findById(savedId));
        Assertions.assertThat(member.getCalendarList()).contains(calendar);
    }
}
