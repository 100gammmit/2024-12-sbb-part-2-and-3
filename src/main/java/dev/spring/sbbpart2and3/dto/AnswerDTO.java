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
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static AnswerDTO toDto(Answer answer) {
        return new AnswerDTO(answer.getId(),
                answer.getContent(),
                answer.getCreatedDate(),
                answer.getLastModifiedDate());
    }
}
