package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Date {
    private String start;
    private String end; //diary는 start == end

    protected Date() {
    }

    public Date(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
