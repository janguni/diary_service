package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Color {

    private String textColor;
    private String backgroundColor;
    private String borderColor;

    protected Color() {
    }

    public Color(String textColor, String backgroundColor, String borderColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
}
