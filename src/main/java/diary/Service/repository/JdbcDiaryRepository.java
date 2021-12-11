package diary.Service.repository;

import diary.Service.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JdbcDiaryRepository implements DiaryRepository {

    private final EntityManager em;

    private final MemberRepository memberRepository;

    @Override
    public List<Diary> diaryList(HttpServletRequest request) {//위부터 아래로 오름차순으로 정렬해서 갖다줌
        HttpSession session = request.getSession();
        String findId = String.valueOf(session.getAttribute("mem_id"));
        Member findMember = memberRepository.findById(findId);
        return em.createQuery("select d from Diary d where d.member = :member", Diary.class)
                .setParameter("member", findMember)
                .getResultList();
    }

    @Override
    public Diary findById(Long id) {
        return em.find(Diary.class, id);
    }

    @Override
    public Long save(Diary diary, HttpServletRequest request) { //id는 db에서 자동으로 생성되도록 설계
        HttpSession session = request.getSession();
        String findId = String.valueOf(session.getAttribute("mem_id"));
        Member findMember = memberRepository.findById(findId);

        diary.createDiary(findMember, diary.getTextInfo(), diary.getDate(), diary.isAllDay(), diary.getColor());
        em.persist(diary);
        return diary.getId();
    }

    @Override
    public Long update(Diary diary, TextInfo  textInfo,Date date, boolean allDay, Color color) { //title, content, id를 따로 변수로 받아와야하려나
        diary.changeDiary(diary, textInfo, diary.getDate(), diary.getColor());
        return diary.getId();
    }

    @Override
    public void delete(Diary diary) {
        em.remove(diary);
    }

    public int getPages(HttpServletRequest request){ //이거 필요없으려나???????????
        return 0;
    }


}
