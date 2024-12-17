package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.Answer;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    public void save(String content, Question question, SiteUser siteUser) {
        Answer answer = new Answer(content, siteUser);
        question.addAnswer(answer);
        answerRepository.save(answer);
    }
}
