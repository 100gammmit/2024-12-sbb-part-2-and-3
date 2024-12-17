package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.dto.QuestionDTO;
import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.dto.QuestionListDTO;
import dev.spring.sbbpart2and3.exception.NoDataFoundException;
import dev.spring.sbbpart2and3.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.spring.sbbpart2and3.dto.QuestionDTO.toQuestionDto;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void save(String subject, String content) {
        questionRepository.save(new Question(subject, content));
    }

    public QuestionDTO findQuestionDtoById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("게시글이 존재하지 않습니다."));
        return toQuestionDto(question);
    }

    public Question findQuestionById(Long id) {
        return  questionRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("게시글이 존재하지 않습니다."));
    }


    public Page<QuestionListDTO> findPagedQuestionsAsDTO(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Question> questions = questionRepository.findAll(pageable);
        return questions.map(QuestionListDTO::toQuestionListDTO);
    }

}
