package com.example.job.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.openapitools.model.JobPostCustomeQuestionListDTO.AnswereTypeEnum;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "job_post_custom_question_list")
@Data
public class JobPostCustomQuestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "answere_type_enum")
    private AnswereTypeEnum answereType;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;


}