package diary.Service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Diary {
    @Id @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private TextInfo textInfo;

    @Embedded
    private Date date;

    private boolean allDay; //0으로하여 캘린더와 구분 추후에 다시 동일하게 1로하고 컬러로 구분하는게 어떤지?-?

    @Embedded
    private Color color;

    public Diary() {
    }

    //==생성메서드==//
    public static Diary createDiary(Member member, TextInfo textInfo, Date date, boolean allDay, Color color){
        Diary diary = new Diary();
        diary.setMember(member);
        diary.setTextInfo(textInfo);
        diary.setDate(date);
        diary.setAllDay(allDay);
        diary.setColor(color);

        return diary;
    }

    //==수정 메서드==//
    public Diary changeDiary(Diary diary, TextInfo textInfo, Date date, Color color){
        diary.setTextInfo(textInfo);
        diary.setDate(date);
        diary.setColor(color);

        return diary;
    }

    private void setMember(Member member) {
        this.member = member;
    }

    public void setTextInfo(TextInfo textInfo) {
        this.textInfo = textInfo;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    /*public Diary(Long id, Member member, TextInfo textInfo, Date date, boolean allDay, Color color) {
        this.id = id;
        this.member = member;
        this.textInfo = textInfo;
        this.date = date;
        this.allDay = allDay;
        this.color = color;
    }*/
}
