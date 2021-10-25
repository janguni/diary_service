package diary.Service.service;

import diary.Service.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    int login(String mem_id, String mem_pw, HttpServletRequest request);

    //0이면 실패, 1이면 성공
    String join(Member member);



}
