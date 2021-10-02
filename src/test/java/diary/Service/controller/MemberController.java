package diary.Service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.diaryservice.domain.Member;
import web.diaryservice.domain.MemberForm;
import web.diaryservice.repository.MemberRepository;
import web.diaryservice.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class MemberController {

    MemberRepository memberRepository;
    MemberService memberService;

    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @GetMapping("/diary/main")
    public String mainform(){
        return "basic/main";
    }

    @GetMapping("/diary/join")
    public String joinform(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "basic/join";
    }
    @PostMapping("/diary/join")
    public String join(@Valid MemberForm memberForm, BindingResult bindingResult, Model model){
        if(validateDuplicateMember(memberForm)){
            model.addAttribute("validate",true);
            log.info("중복되는 아이디입니다.");
            return "basic/join";
        }
        if (bindingResult.hasErrors()) {
            log.info("binding result = {}", bindingResult);
            return "basic/join"; // 현재 폼을 다시 랜더링
        }
        Member member = new Member(memberForm.getMem_id(),memberForm.getMem_pw(),
                memberForm.getMem_name(), memberForm.getMem_age(), memberForm.getMem_gender(),
                memberForm.getMem_usertype());

        memberRepository.save(member);
        return "redirect:localhost:8080/diary/basic/main";
    }

//    @PostMapping("/diary/join")
//    public String join(@ModelAttribute Member member, Model model){
//        if(validateDuplicateMember(member)){
//            model.addAttribute("validate",true);
//            log.info("중복되는 아이디입니다.");
//            return "basic/join";
//        }
//        memberRepository.save(member);
//        return "basic/main";
//    }

//    @PostMapping("/diary/join")
//    public String join(@Valid MemberForm memberform, BindingResult bindingResult, Model model){
//        if(validateDuplicateMember(memberform)){
//            model.addAttribute("validate",true);
//            log.info("중복되는 아이디입니다.");
//            return "basic/join";
//        }
//        if (bindingResult.hasErrors()) {
//            log.info("binding result = {}", bindingResult);
//            return "basic/join"; // 현재 폼을 다시 랜더링
//        }
//        Member member = new Member(memberform.getMem_id(),memberform.getMem_pw(),
//                memberform.getMem_name(), memberform.getMem_age(), memberform.getMem_gender(),
//                memberform.getMem_usertype());
//
//        memberRepository.save(member);
//        return "basic/main";
//    }

//    @GetMapping("/{email}")
//    public String member(@PathVariable String email,
//                         Model model){
//        Optional<Member> member = memberRepository.findByEmail(email);
//        model.addAttribute("member",member);
//        return "basic/member";
//    }

    @GetMapping("/diary/members")
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "basic/members";
    }

    @GetMapping("/diary/login")
    public String loginform(){
        return "basic/login";
    }

    @PostMapping("/diary/login")
    public String login(@RequestParam String mem_id,
                        @RequestParam String mem_pw,
                        HttpServletRequest request){
        int result = memberService.login(mem_id, mem_pw, request);
        HttpSession session = request.getSession(); //세션받기

        if(result == 1) log.info("login success");
        else log.info("login fail");

        log.info("{}",session.getAttribute("mem_id")); // 세션받기 성공

        return "basic/main";
    }

    @GetMapping("/diary/logout")
    public String logout(HttpServletRequest request){
        /*HttpSession session = request.getSession();
        session.invalidate();*/

        request.getSession().invalidate();  //무효화 처리(사용자가 다시 요청하면 다시 정보를 줌)
        request.getSession(true);

        return "basic/main";
    }

    @GetMapping("/diary/calendar")
    public String calendar(){
        return "basic/calendar";
    }
//    @PostMapping("/login")
//    public String save(@RequestParam String id,
//                       @RequestParam String pw,
//                       @RequestParam String name,
//                       @RequestParam int age,
//                       @RequestParam String gender,
//                       @RequestParam String email,
//                       @RequestParam int usertype,
//                       Model model){
//
//        Member member = new Member(id,pw,name,age,gender,email,usertype);
//        memberRepository.save(member);
//
//        model.addAttribute("member", member);
//        return "basic/member";
//    }

    private boolean validateDuplicateMember(MemberForm memberForm) {
        if(memberRepository.findById(memberForm.getMem_id())
                .isPresent()) return true; //중복이면 true
        else return false;
    }
}
