package diary.Service.repository;

import diary.Service.domain.Calendar;
import diary.Service.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcCalendarRepository implements CalendarRepository{

    @PersistenceContext
    private EntityManager em;

    private final MemberRepository memberRepository;


    @Override
    public List<Calendar> calendarList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String findId = String.valueOf(session.getAttribute("mem_id"));
        Member findMember = memberRepository.findById(findId);
        return em.createQuery("select c from Calendar c where c.member = :member", Calendar.class)
                .setParameter("member", findMember)
                .getResultList();
    }

    @Override
    public void save(Calendar calendar, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String findId = String.valueOf(session.getAttribute("mem_id"));
        Member findMember = memberRepository.findById(findId);
        Calendar saveCalendar = new Calendar(calendar.getId(), findMember, calendar.getTextInfo(), calendar.getDate(), calendar.isAllDay(), calendar.getColor());
        em.persist(saveCalendar);
    }
}
