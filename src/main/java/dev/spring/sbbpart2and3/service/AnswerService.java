package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.Answer;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.dto.AnswerDTO;
import dev.spring.sbbpart2and3.exception.NoDataFoundException;
import dev.spring.sbbpart2and3.repository.AnswerRepository;
import dev.spring.sbbpart2and3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.spring.sbbpart2and3.dto.AnswerDTO.toDto;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    public AnswerDTO save(Answer answer) {
        answerRepository.save(answer);
        return toDto(answer);
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

    public AnswerDTO vote(Long answerId, String username) {
        Answer answer = getAnswerById(answerId);
        SiteUser siteUser = userRepository.findByUsername(username).orElseThrow(() ->
                new NoDataFoundException("사용자가 존재하지 않습니다."));
        answer.addVoter(siteUser);
        answerRepository.save(answer);
        return toDto(answer);
    }

}
