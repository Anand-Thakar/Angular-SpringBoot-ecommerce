package com.example.job.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
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

    @OneToOne(mappedBy = "jobPost", orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private JobPostHowToApply jobPostHowToApply;

    @OneToOne(mappedBy = "jobPostSalaryRange", orphanRemoval = true, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private JobPostSalaryRange jobPostSalaryRange;

    @Column(name = "job_city")
    private String jobCity;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<JobPostCustomQuestionList> jobPostCustomQuestionLists = new java.util.LinkedHashSet<>();

    //    @OneToOne(mappedBy = "jobPost", orphanRemoval = true)
//    private JobPostCustomQuestionList jobPostCustomQuestionList;


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
