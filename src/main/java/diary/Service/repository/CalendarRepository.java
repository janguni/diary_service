package diary.Service.repository;

import diary.Service.domain.Calendar;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public interface CalendarRepository {
    List<Calendar> calendarList(HttpServletRequest request);

    void save(Calendar calendar, HttpServletRequest request);


}
