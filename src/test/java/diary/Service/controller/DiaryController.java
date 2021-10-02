package diary.Service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.diaryservice.domain.Diary;
import web.diaryservice.repository.DiaryRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("diary.do")
public class DiaryController {

    private final DiaryRepository diaryRepository;

    public DiaryController(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @GetMapping(params="method=list")
    public String list() {
        return "basic/diary";
    }

    @GetMapping(params="method=data")
    public @ResponseBody
    List<Diary> data(Model model, HttpServletRequest request) { //model도 받을필요 없음
        List<Diary> diaries = diaryRepository.diaryList(request);
        model.addAttribute("list", diaryRepository.diaryList(request));

        return diaries;
    }

    @GetMapping("/add")
    public String addForm() {return "basic/adddiary";}
    @PostMapping("/add")
    public String add(@ModelAttribute Diary diary, HttpServletRequest request){
        diaryRepository.save(diary, request);
        return "redirect:/diary.do/diaries";
    }

    @GetMapping("/{id}") //다이어리 글 상세보기
    public String diary(@PathVariable Long id, Model model){
        Optional<Diary> find = diaryRepository.findById(id);
        Diary diary = find.get();

        model.addAttribute("diary",diary);
        return "basic/diary";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model){
        Optional<Diary> diary = diaryRepository.findById(id);
        model.addAttribute("diary", diary.get());
        return "basic/editdiary";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Diary diary){
        diaryRepository.update(diary);
        return "redirect:/diary.do/diaries";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model){
        Optional<Diary> diary = diaryRepository.findById(id);
        model.addAttribute("diary", diary.get());
        return "basic/deletediary";
    }
    @GetMapping("/{id}/delete/do")
    public String delete(@PathVariable Long id, @ModelAttribute Diary diary){
        diaryRepository.delete(diary);
        return "redirect:/diary.do/diaries";
    }

    @GetMapping("diaries")
    public String diaries(Model model, HttpServletRequest request){
        List<Diary> diaries = diaryRepository.diaryList(request);
        model.addAttribute("diaries",diaries);

        return "basic/diaries";
    }

}
