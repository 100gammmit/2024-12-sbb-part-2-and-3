package dev.spring.sbbpart2and3.repository;

import dev.spring.sbbpart2and3.domain.Question;
import dev.spring.sbbpart2and3.dto.QuestionListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);

    /*@Query("select " +
            "distinct new dev.spring.sbbpart2and3.dto.QuestionListDTO( " +
            "  q.id, q.subject, q.createdDate, q.lastModifiedDate" +
            ") " +
            "from Question q " +
            "left outer join SiteUser u1 on q.author = u1 " +
            "left outer join Answer a on a.question = q " +
            "left outer join SiteUser u2 on a.author = u2 " +
            "where " +
            "q.subject like %:kw% " +
            "or q.content like %:kw% " +
            "or u1.userId like %:kw% " +
            "or a.content like %:kw% " +
            "or u2.userId like %:kw% ")
    Page<QuestionListDTO> QuestionListAsDTO(String kw, Pageable pageable);*/

}
