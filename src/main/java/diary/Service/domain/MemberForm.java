package diary.Service.domain;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class MemberForm {

    @NotEmpty(message = "아이디 필수 입력란입니다")
    private String id;
    @NotNull @Size(min = 6, max = 14, message = "비밀번호는 6자리 이상 14자리 이하여야 합니다")
    private String pw;
    @NotNull @Size(min = 3, message = "이름은 최소 3글자 이상이여야 합니다")
    private String name;
    private int age;
    private String gender;
    private int usertype;

    public MemberForm(){}


    public MemberForm(String id, String pw, String name, int age, String gender, int usertype) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.usertype = usertype;
    }
}
