package dev.spring.sbbpart2and3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class Answer extends TimeSetAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT") @Setter
    private String content;

    @ManyToOne @Setter
    private Question question;

    @ManyToOne
    private SiteUser author;

    protected Answer() {}

    public Answer(String content, SiteUser author) {
        this.content = content;
        this.author = author;
    }
}
