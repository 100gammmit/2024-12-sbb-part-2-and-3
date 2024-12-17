package dev.spring.sbbpart2and3.dto;

import dev.spring.sbbpart2and3.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private String content;
    private String author;
    private Long QuestionId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static AnswerDTO toDto(Answer answer) {
        return new AnswerDTO(answer.getId(),
                answer.getContent(),
                answer.getAuthor() != null? answer.getAuthor().getUsername() : null,
                answer.getQuestion().getId(),
                answer.getCreatedDate(),
                answer.getLastModifiedDate());
    }
}
