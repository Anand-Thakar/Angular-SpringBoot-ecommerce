package com.example.job.entity;

import jakarta.persistence.*;
import org.openapitools.model.JobPostCustomeQuestionListDTO.AnswereTypeEnum;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "job_post_custom_question_list")
public class JobPostCustomQuestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;




    @Enumerated(EnumType.STRING)
    @Column(name = "answere_type_enum")
    private AnswereTypeEnum answereType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @ElementCollection
    @Column(name = "list_of_question")
    @CollectionTable(name = "job_post_custom_question_list_listOfQuestion", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<String> listOfQuestion = new LinkedHashSet<>();

}