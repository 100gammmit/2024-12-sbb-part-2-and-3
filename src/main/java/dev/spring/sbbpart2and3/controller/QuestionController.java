package dev.spring.sbbpart2and3.controller;

import dev.spring.sbbpart2and3.dto.QuestionListDTO;
import dev.spring.sbbpart2and3.form.AnswerForm;
import dev.spring.sbbpart2and3.form.QuestionForm;
import dev.spring.sbbpart2and3.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public String createQuestion(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        questionService.save(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}")
    public String getQuestionDetail(@PathVariable("id") Long id, AnswerForm answerForm, Model model) {
        model.addAttribute("question", questionService.findQuestionDtoById(id));
        return "question_detail";
    }

    @GetMapping("/save")
    public String saveQuestion() {

        return "question_list";
    }

}
