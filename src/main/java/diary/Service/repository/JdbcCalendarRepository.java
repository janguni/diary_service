package diary.Service.repository;

import diary.Service.domain.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class JdbcCalendarRepository implements CalendarRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private CalendarMapper calendarMapper;

    @Autowired
    public JdbcCalendarRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Calendar> calendarList(HttpServletRequest request) {
        String sql = "select id,groupId,title,writer,content,start,end,allDay,textColor,backgroundColor,borderColor from calendar where mem_id = ?";
        HttpSession session = request.getSession();

        return jdbcTemplate.query(sql, calendarMapper, session.getAttribute("mem_id"));
    }

    @Override
    public void save(Calendar calendar, HttpServletRequest request) {
        String sql = "insert into calendar(id,groupId,title,writer,content,start,end,allDay,textColor,backgroundColor,borderColor,mem_id) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        HttpSession session = request.getSession();

        int result = jdbcTemplate.update(sql, calendar.getId(), calendar.getGroupId(),
                calendar.getTitle(),calendar.getWriter(), calendar.getContent(),
                calendar.getStart(),calendar.getEnd(),1,"#ffffff","#45c99a","#ffffff",
                session.getAttribute("mem_id"));
        //1이면 성공
        log.info("{}",result);
    }
}
