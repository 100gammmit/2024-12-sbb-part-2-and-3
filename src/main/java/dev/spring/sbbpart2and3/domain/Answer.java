package dev.spring.sbbpart2and3.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToMany
    private Set<SiteUser> voter = new HashSet<>();

    public Answer(String content, SiteUser author) {
        this.content = content;
        this.author = author;
    }

    public void addVoter(SiteUser voter) {
        this.voter.add(voter);
    }
}
