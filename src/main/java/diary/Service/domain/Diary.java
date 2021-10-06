package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
    public class Diary {
        private Long id;
        private String title;
        private String writer; //사용자의 이름을 넣을까?
        private String content;
        private String start;
        private String end; //start와 동일하게 할 예정
        private boolean allDay; //0으로하여 캘린더와 구분 추후에 다시 동일하게 1로하고 컬러로 구분하는게 어떤지?-?
        private String textColor;
        private String backgroundColor;
        private String borderColor;

    public Diary(){}

    public Diary(Long id, String title, String writer, String content, String start,
                 String end, boolean allDay, String textColor, String backgroundColor, String borderColor) {
        this.id = id;
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
