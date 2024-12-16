package dev.spring.sbbpart2and3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @GetMapping("/list")
    @ResponseBody
    public String getQuestionList() {
        return "question/list";
    }
}
