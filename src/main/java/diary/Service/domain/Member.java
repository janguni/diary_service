package diary.Service.domain;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Member {

    @Id
    private String id;
    private String pw;
    private String name;
    private int age;
    private String gender;
    private int usertype;


    public Member(){}

    public Member(String id, String pw, String name, int age, String gender, int usertype) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.usertype = usertype;
    }
}
