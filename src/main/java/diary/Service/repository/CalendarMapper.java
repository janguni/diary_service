package diary.Service.repository;

import diary.Service.domain.Calendar;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CalendarMapper implements RowMapper<Calendar> {

    @Override
    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Calendar calendar = new Calendar();

        calendar.setId(rs.getLong("id"));
        calendar.setGroupId(rs.getLong("groupId"));
        calendar.setTitle(rs.getString("title"));
        calendar.setWriter(rs.getString("writer"));
        calendar.setContent(rs.getString("content"));
        calendar.setStart(rs.getString("start"));
        calendar.setEnd(rs.getString("end"));
        calendar.setAllDay(rs.getBoolean("allDay"));
        calendar.setTextColor(rs.getString("textColor"));
        calendar.setBackgroundColor(rs.getString("backgroundColor"));
        calendar.setBorderColor(rs.getString("borderColor"));

        return calendar;
    }
}
