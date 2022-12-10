package com.example.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.model.JobPostHowToApplyDTO.ApplicationMethodEnum;

@Entity
@Table(name = "job_post_how_to_apply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostHowToApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}