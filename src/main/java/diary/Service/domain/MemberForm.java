package diary.Service.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MemberForm {

    @NotEmpty(message = "아이디 필수 입력란입니다")
    private String mem_id;
    @NotNull @Size(min = 6, max = 14, message = "비밀번호는 6자리 이상 14자리 이하여야 합니다")
    private String mem_pw;
    @NotNull @Size(min = 3, message = "이름은 최소 3글자 이상이여야 합니다")
    private String mem_name;
    private int mem_age;
    private String mem_gender;
    private int mem_usertype;

    public MemberForm(){}


    public MemberForm(String mem_id, String mem_pw, String mem_name, int mem_age, String mem_gender, int mem_usertype) {
        this.mem_id = mem_id;
        this.mem_pw = mem_pw;
        this.mem_name = mem_name;
        this.mem_age = mem_age;
        this.mem_gender = mem_gender;
        this.mem_usertype = mem_usertype;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public int getMem_age() {
        return mem_age;
    }

    public void setMem_age(int mem_age) {
        this.mem_age = mem_age;
    }

    public String getMem_gender() {
        return mem_gender;
    }

    public void setMem_gender(String mem_gender) {
        this.mem_gender = mem_gender;
    }

    public int getMem_usertype() {
        return mem_usertype;
    }

    public void setMem_usertype(int mem_usertype) {
        this.mem_usertype = mem_usertype;
    }
}
