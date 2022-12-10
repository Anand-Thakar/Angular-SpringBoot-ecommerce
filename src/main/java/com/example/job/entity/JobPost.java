package com.example.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

import org.openapitools.model.JobPostDTO.JobTypeEnum;
import org.openapitools.model.JobPostDTO.JobPostStatusEnum;


@Entity
@Table(name = "job_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "position_title", nullable = false)
    private String positionTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type_enum", nullable = false)
    private JobTypeEnum jobTypeEnum;

    @FutureOrPresent(message = "yyyy-mm-dd")
    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "required_qualififcation", nullable = false)
    private String requiredQualififcation;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_post_status_enum")
    private JobPostStatusEnum jobPostStatusEnum;

    @OneToOne(mappedBy = "jobPost", orphanRemoval = true)
    private JobPostHowToApply jobPostHowToApply;

    @OneToOne(mappedBy = "jobPost", orphanRemoval = true)
    private JobPostCustomQuestionList jobPostCustomQuestionList;

    @OneToOne(mappedBy = "jobPostSalaryRange", orphanRemoval = true)
    private JobPostSalaryRange jobPostSalaryRange;

    @Column(name = "job_city")
    private String jobCity;

    //    @ElementCollection
//    @Column(name = "job_city")
//    @CollectionTable(name = "job_post_jobCity", joinColumns = @JoinColumn(name = "owner_id"))
//    private Set<String> jobCity = new LinkedHashSet<>();

}
