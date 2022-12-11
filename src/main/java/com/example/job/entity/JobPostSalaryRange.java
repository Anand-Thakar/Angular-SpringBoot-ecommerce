package com.example.job.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.openapitools.model.JobPostSalaryRangeDTO.SalaryTypeEnum;
import java.util.Objects;

@Entity
@Table(name = "job_post_salary_range")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class JobPostSalaryRange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type_enum")
    private SalaryTypeEnum salaryTypeEnum;

    @Column(name = "salary_minimum")
    private String salaryMinimum;

    @Column(name = "salary_maximum")
    private String salaryMaximum;

    @Column(name = "wage")
    private String wage;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "job_post_salary_range_id")
//    private JobPost jobPostSalaryRange;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobPostSalaryRange that = (JobPostSalaryRange) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}