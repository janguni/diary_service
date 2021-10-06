package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Calendar {

    @Id @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //FK 이름 지정정    private Member member;
    private Member member;

    @Embedded
    private TextInfo textInfo;

    @Embedded
    private Date date;

    private boolean allDay; //0으로하여 캘린더와 구분 추후에 다시 동일하게 1로하고 컬러로 구분하는게 어떤지?-?

    @Embedded
    private Color color;

    public Calendar(Long id, Member member, TextInfo textInfo, Date date, boolean allDay, Color color) {
        this.id = id;
        this.member = member;
        this.textInfo = textInfo;
        this.date = date;
        this.allDay = allDay;
        this.color = color;
    }
}
