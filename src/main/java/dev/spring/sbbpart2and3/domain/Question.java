package dev.spring.sbbpart2and3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
public class Question extends TimeSetAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200) @Setter
    private String subject;
    @Column(columnDefinition = "TEXT") @Setter
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    private SiteUser author;

    protected Question() {}

    public Question(String subject, String content, SiteUser author) {
        this.subject = subject;
        this.content = content;
        this.author = author;
    }

    public void addAnswer(Answer answer) {
        answerList.add(answer);
        answer.setQuestion(this);
    }
}
