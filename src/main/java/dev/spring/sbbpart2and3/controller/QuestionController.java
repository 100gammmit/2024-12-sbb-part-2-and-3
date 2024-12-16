package dev.spring.sbbpart2and3.controller;

import dev.spring.sbbpart2and3.dto.QuestionDTO;
import dev.spring.sbbpart2and3.dto.QuestionListDTO;
import dev.spring.sbbpart2and3.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/list")
    public String getQuestionList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Page<QuestionListDTO> questionList = questionService.findPagedQuestionsAsDTO(page);
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/details/{id}")
    public String getQuestionDetail(@PathVariable("id") Long id, Model model) {
        QuestionDTO question = questionService.findQuestionById(id);
        model.addAttribute("question", question);
        return "question_list";
    }

    @GetMapping("/save")
    public String saveQuestion() {

        return "question_list";
    }

}
