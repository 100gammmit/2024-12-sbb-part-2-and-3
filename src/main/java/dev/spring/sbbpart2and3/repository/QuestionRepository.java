package dev.spring.sbbpart2and3.repository;

import dev.spring.sbbpart2and3.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
