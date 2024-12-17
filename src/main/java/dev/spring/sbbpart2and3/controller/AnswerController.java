package dev.spring.sbbpart2and3.controller;

import dev.spring.sbbpart2and3.form.AnswerForm;
import dev.spring.sbbpart2and3.service.AnswerService;
import dev.spring.sbbpart2and3.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Autowired
    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @PostMapping("create/{id}")
    public String create(@PathVariable("id") Long questionId,
                         @Valid AnswerForm answerForm, BindingResult bindingResult,
                         Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/question/" + questionId;
        }
        answerService.save(answerForm.getContent(), questionService.findQuestionById(questionId));
        return "redirect:/question/detail/" + questionId;
    }
}
