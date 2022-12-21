package com.example.job.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.openapitools.model.JobPostDTO.JobPostStatusEnum;
import org.openapitools.model.JobPostDTO.JobTypeEnum;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "job_post")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
    @Column(name = "job_type", nullable = false)
    private JobTypeEnum jobType;

    @FutureOrPresent(message = "yyyy-mm-dd")
    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "required_qualification", nullable = false)
    private String requiredQualification;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_post_status_enum")
    private JobPostStatusEnum jobPostStatus;

//    @OneToOne(mappedBy = "jobPost", orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_post_how_to_apply_id",referencedColumnName = "id")
    private JobPostHowToApply howToApply;

//    @OneToOne(mappedBy = "jobPostSalaryRange", orphanRemoval = true, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "job_post_salary_range_id",referencedColumnName = "id")
    private JobPostSalaryRange salaryRange;

    @Column(name = "job_city")
    private String jobCity;

//    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = "job_post_id", referencedColumnName = "id")
    private Set<JobPostCustomQuestionList> customQuestionList = new java.util.LinkedHashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobPost jobPost = (JobPost) o;
        return id != null && Objects.equals(id, jobPost.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
