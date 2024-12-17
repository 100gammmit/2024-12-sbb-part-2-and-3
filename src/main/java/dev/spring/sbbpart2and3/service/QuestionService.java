package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.dto.QuestionDTO;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.dto.QuestionListDTO;
import dev.spring.sbbpart2and3.exception.NoDataFoundException;
import dev.spring.sbbpart2and3.repository.QuestionRepository;
import dev.spring.sbbpart2and3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static dev.spring.sbbpart2and3.dto.QuestionDTO.toQuestionDto;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public void save(String subject, String content, SiteUser siteUser) {
        questionRepository.save(new Question(subject, content, siteUser));
    }

    public void modify(Long id, String subject, String content) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("게시글이 존재하지 않습니다."));
        question.setSubject(subject);
        question.setContent(content);
        questionRepository.save(question);
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    public QuestionDTO getQuestionDTOById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("게시글이 존재하지 않습니다."));
        return toQuestionDto(question);
    }

    public Question getQuestionById(Long id) {
        return  questionRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("게시글이 존재하지 않습니다."));
    }

    public Page<QuestionListDTO> getPagedQuestionDTOs(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, 10, sort);
        Page<Question> questions = questionRepository.findAll(pageable);
        return questions.map(QuestionListDTO::toQuestionListDTO);
    }

    public void vote(Long questionId, String username) {
        Question question = getQuestionById(questionId);
        SiteUser siteUser = userRepository.findByUsername(username).orElseThrow(() ->
                new NoDataFoundException("사용자가 존재하지 않습니다."));
        question.addVoter(siteUser);
        questionRepository.save(question);
    }

}
