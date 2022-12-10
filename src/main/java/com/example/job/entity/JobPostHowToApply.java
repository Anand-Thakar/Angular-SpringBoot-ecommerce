package com.example.job.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.openapitools.model.JobPostHowToApplyDTO.ApplicationMethodEnum;

import java.util.Objects;

@Entity
@Table(name = "job_post_how_to_apply")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class JobPostHowToApply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_method_enum")
    private ApplicationMethodEnum applicationMethodEnum;
    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobPostHowToApply that = (JobPostHowToApply) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}