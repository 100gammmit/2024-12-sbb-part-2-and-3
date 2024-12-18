package dev.spring.sbbpart2and3.dto;

import dev.spring.sbbpart2and3.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class QuestionListDTO {
    private Long id;
    private String subject;
    private LocalDateTime createdDate;
    private int answerCount;
    private String author;

    public static QuestionListDTO toQuestionListDTO(Question question) {
        return new QuestionListDTO(question.getId(),
                question.getSubject(),
                question.getCreatedDate(),
                question.getAnswerList().size(),
                question.getAuthor() != null ? question.getAuthor().getUsername() : null);
    }
}
