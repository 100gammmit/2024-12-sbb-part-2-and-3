package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.Answer;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.dto.AnswerDTO;
import dev.spring.sbbpart2and3.exception.NoDataFoundException;
import dev.spring.sbbpart2and3.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.spring.sbbpart2and3.dto.AnswerDTO.toDto;

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

    public void modify(Long id, String content) {
        Answer answer = answerRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("답변을 찾을 수 없습니다."));
        answer.setContent(content);
        answerRepository.save(answer);
    }

    public void delete(Long id) {
        answerRepository.deleteById(id);
    }

    public String getContentById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("답변을 찾을 수 없습니다."));
        return answer.getContent();
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("답변을 찾을 수 없습니다."));
    }

    public AnswerDTO getAnswerDTOById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("답변을 찾을 수 없습니다."));
        return toDto(answer);
    }


}
