package toyproject.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.login.member.Member;
import toyproject.login.member.MemberForm;
import toyproject.login.member.MemberService;

import java.util.List;

@Controller
public class HomeController {

    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "createForm";
    }

    @PostMapping("members/new")
    public String createMember(MemberForm form, Model model){
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        memberService.join(member);

        List<Member> list = memberService.findMembers();
        model.addAttribute("members", list);
        return "memberList";
    }

    @GetMapping("members")
    public String memberList(Model model){

        List<Member> list = memberService.findMembers();
        model.addAttribute("members", list);
        return "memberList";
    }
}
