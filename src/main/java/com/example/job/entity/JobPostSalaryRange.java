package com.example.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.model.JobPostSalaryRangeDTO.SalaryTypeEnum;

@Entity
@Table(name = "job_post_salary_range")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostSalaryRange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type_enum")
    private SalaryTypeEnum salaryTypeEnum;

    @Column(name = "salary_minimum", nullable = false)
    private String salaryMinimum;

    @Column(name = "salary_maximum")
    private String salaryMaximum;

    @Column(name = "wage")
    private String wage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

}