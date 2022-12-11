package com.example.job.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.openapitools.model.JobPostCustomeQuestionListDTO.AnswereTypeEnum;
import java.util.Objects;


@Entity
@Table(name = "job_post_custom_question_list")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class JobPostCustomQuestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question")
    private String question;

    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Column(name = "answere_type_enum")
    private AnswereTypeEnum answereType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

//    @ManyToOne
//    @JoinColumn(name = "job_post_id")
//    private JobPost jobPost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobPostCustomQuestionList that = (JobPostCustomQuestionList) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}