package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Calendar {
    private Long id;
    private Long groupId;
    private String title;
    private String writer;
    private String content;
    private String start;
    private String end;
    private boolean allDay;
    private String textColor;
    private String backgroundColor;
    private String borderColor;

    public Calendar(){}

    public Calendar(Long id, Long groupId, String title, String writer, String content, String start,
                    String end, boolean allDay, String textColor, String backgroundColor, String borderColor) {
        this.id = id;
        this.groupId = groupId;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
}
