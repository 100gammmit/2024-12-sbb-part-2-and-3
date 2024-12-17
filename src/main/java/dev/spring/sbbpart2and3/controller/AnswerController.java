package dev.spring.sbbpart2and3.controller;

import dev.spring.sbbpart2and3.domain.Answer;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.dto.AnswerDTO;
import dev.spring.sbbpart2and3.form.AnswerForm;
import dev.spring.sbbpart2and3.service.AnswerService;
import dev.spring.sbbpart2and3.service.QuestionService;
import dev.spring.sbbpart2and3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequestMapping("answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @Autowired
    public AnswerController(AnswerService answerService, QuestionService questionService, UserService userService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("create/{id}")
    public String createAnswer(@PathVariable("id") Long questionId,
                         @Valid AnswerForm answerForm, BindingResult bindingResult,
                         Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/question/" + questionId;
        }
        Question question = questionService.getQuestionById(questionId);
        SiteUser siteUser = userService.findUserByUsername(principal.getName());
        answerService.save(answerForm.getContent(), question, siteUser);
        return "redirect:/question/detail/" + questionId;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("modify/{id}")
    public String modifyAnswer(@PathVariable("id") Long id, AnswerForm answerForm, Principal principal) {
        AnswerDTO answer = answerService.getAnswerDTOById(id);
        if(!answer.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("modify/{id}")
    public String modifyAnswer(@PathVariable("id") Long id, Principal principal,
            @Valid AnswerForm answerForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "answer_form";
        }
        AnswerDTO answer = answerService.getAnswerDTOById(id);
        if(!answer.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        answerService.modify(id, answerForm.getContent());
        return "redirect:/question/detail/" + answer.getQuestionId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("delete/{id}")
    public String deleteAnswer(@PathVariable("id") Long id, AnswerForm answerForm, Principal principal) {
        AnswerDTO answer = answerService.getAnswerDTOById(id);
        if(!answer.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        answerService.delete(id);
        return "redirect:/question/detail/" + answer.getQuestionId();
    }

}
