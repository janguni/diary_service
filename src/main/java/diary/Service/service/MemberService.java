package diary.Service.service;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    int login(String mem_id, String mem_pw, HttpServletRequest request);
    //0이면 실패, 1이면 성공



}
