package diary.Service.repository;

import diary.Service.domain.Calendar;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface CalendarRepository {
    List<Calendar> calendarList(HttpServletRequest request);

    Calendar findById(Long id);

    Long save(Calendar calendar, HttpServletRequest request);




}
