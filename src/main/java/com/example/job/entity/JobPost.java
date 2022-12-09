package com.example.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

//    @ElementCollection
//    @Column(name = "job_city")
//    @CollectionTable(name = "job_post_jobCity", joinColumns = @JoinColumn(name = "owner_id"))
//    private Set<String> jobCity = new LinkedHashSet<>();



    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "job_post_how_to_apply_id", nullable = false)
    private JobPostHowToApply jobPostHowToApply;


    @Enumerated(EnumType.STRING)
    @Column(name = "job_post_status_enum")
    private JobPostStatusEnum jobPostStatusEnum;




    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_post_salary_range_id")
    private JobPostSalaryRange jobPostSalaryRange;

}
