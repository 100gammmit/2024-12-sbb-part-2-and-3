package dev.spring.sbbpart2and3.dto;

import dev.spring.sbbpart2and3.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String subject;
    private String content;
    private List<AnswerDTO> answerList;
    private LocalDateTime createdDate;
    private LocalDateTime LastModifiedDate;

    public static QuestionDTO toQuestionDto(Question question) {
        return new QuestionDTO(question.getId(),
                question.getSubject(),
                question.getContent(),
                question.getAnswerList().stream().map(AnswerDTO::toDto).toList(),
                question.getCreatedDate(),
                question.getLastModifiedDate());
    }
}
