package diary.Service.domain;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    private String id;
    private String pw;
    private int age;
    private String gender;
    private int usertype;


    public Member(){}

    public Member(String id, String pw, int age, String gender, int usertype) {
        this.id = id;
        this.pw = pw;
        this.age = age;
        this.gender = gender;
        this.usertype = usertype;
    }




}
