package dev.spring.sbbpart2and3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Getter
public class Question extends TimeSetAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    protected Question() {}

    public Question(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public void addAnswer(Answer answer) {
        answerList.add(answer);
        answer.setQuestion(this);
    }
}
