package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class TextInfo {
    private String title;
    private String writer; //넣을지 말지 고민
    private String content;

    protected TextInfo() {
    }

    public TextInfo(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
