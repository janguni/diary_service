package diary.Service.repository;

import diary.Service.domain.Diary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DiaryMapper implements RowMapper<Diary> {
    @Override
    public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
        Diary diary = new Diary();

        diary.setId(rs.getLong("id"));
        diary.setTitle(rs.getString("title"));
        diary.setWriter(rs.getString("writer"));
        diary.setContent(rs.getString("content"));
        diary.setStart(rs.getString("start"));
        diary.setEnd(rs.getString("end"));
        diary.setAllDay(rs.getBoolean("allDay"));
        diary.setTextColor(rs.getString("textColor"));
        diary.setBackgroundColor(rs.getString("backgroundColor"));
        diary.setBorderColor(rs.getString("borderColor"));

        return diary;
    }
}
