package diary.Service.controller;

import diary.Service.domain.Calendar;
import diary.Service.repository.CalendarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("calendar.do")
public class CalendarController {

    private final CalendarRepository calendarRepository;

    public CalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @GetMapping(params="method=list")
    public String list() {
        return "basic/calendar";
    }

    @GetMapping(params="method=data")
    public @ResponseBody List<Calendar> data(Model model, HttpServletRequest request) { //model도 받을필요 없음
        List<Calendar> calendars = calendarRepository.calendarList(request);
//        model.addAttribute("list", calendarRepository.calendarList());
        //여기에 필요하고 여기는 바뀐게 없엇
        return calendars;
    }

    @GetMapping("/add")
    public String addForm() {return "basic/addcalendar";}

    @PostMapping("/add")
    public String add(@ModelAttribute Calendar calendar, HttpServletRequest request){
        calendarRepository.save(calendar, request);
        return "basic/calendar";
    }










//    @GetMapping(params="method=data")
//    public String data(Model model) {
//        model.addAttribute("list", calendarRepository.calendarList());
//        return "pageJsonReport";
//    }

//    @GetMapping("/list")
//    public String members(Model model){
//        List<Calendar> calendars = calendarRepository.calendarList();
//        model.addAttribute("calendars",calendars);
//        return "basic/calendars";
//    }
}

