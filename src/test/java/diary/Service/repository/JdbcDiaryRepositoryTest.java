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
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class JdbcDiaryRepositoryTest {

    @Autowired
    MemberService memberService;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    EntityManager em;

    private MockMvc mockMvc;
    protected MockHttpSession session;

    @Test
    public void 다이어리_저장() throws Exception{
        //given
        Member member = new Member("yellow0719", "memberPw", "장윤희", 23, "w", 1);
        memberService.join(member);

        Date date = new Date("2021-07-26", "2021-10-25");
        TextInfo textInfo = new TextInfo("제목", "글쓴이", "내용");
        Color color = new Color("#ffffff", "#45c99a", "#ffffff");
        Diary diary = Diary.createDiary(member, textInfo, date, true, color);

        session = new MockHttpSession();    //세션 생성
        session.setAttribute("mem_id", "yellow0719");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        //when
        Long savedId = diaryRepository.save(diary, request);

        //then
        Assert.assertEquals(diary,diaryRepository.findById(savedId));
        List<Diary> diaryList = diaryRepository.diaryList(request);
        Assertions.assertThat(diaryList).contains(diary);

    }

    @Test
    public void 다이어리_업데이트() throws Exception{
        //given
        Member member = new Member("yellow0719", "memberPw", "장윤희", 23, "w", 1);
        memberService.join(member);

        Date date = new Date("2021-07-26", "2021-10-25");
        TextInfo textInfo1 = new TextInfo("제목", "글쓴이1", "내용");
        Color color = new Color("#ffffff", "#45c99a", "#ffffff");
        Diary diary = Diary.createDiary(member, textInfo1, date, true, color);

        session = new MockHttpSession();    //세션 생성
        session.setAttribute("mem_id", "yellow0719");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        Long savedId = diaryRepository.save(diary, request);

        //when
        TextInfo textInfo2 = new TextInfo("제목", "글쓴이2", "내용");
        Long updatedId = diaryRepository.update(diary, textInfo2,diary.getDate(), true, diary.getColor());

        //then
        em.flush();
        Assert.assertEquals(savedId,updatedId);
        Assert.assertEquals("글쓴이2",diaryRepository.findById(updatedId).getTextInfo().getWriter());

    }

    @Test
    public void 다이어리_삭제() throws Exception{
        //given
        Member member = new Member("yellow0719", "memberPw", "장윤희", 23, "w", 1);
        memberService.join(member);

        Date date = new Date("2021-07-26", "2021-10-25");
        TextInfo textInfo1 = new TextInfo("제목", "글쓴이1", "내용");
        Color color = new Color("#ffffff", "#45c99a", "#ffffff");
        Diary diary = Diary.createDiary(member, textInfo1, date, true, color);

        session = new MockHttpSession();    //세션 생성
        session.setAttribute("mem_id", "yellow0719");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        Long savedId = diaryRepository.save(diary, request);

        //when
        diaryRepository.delete(diary);

        //then
        Assert.assertNull(diaryRepository.findById(savedId));

    }



}