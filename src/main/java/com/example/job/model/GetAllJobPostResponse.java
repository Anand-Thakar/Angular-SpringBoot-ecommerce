package com.example.job.model;

import lombok.*;
import org.openapitools.model.FindJobs200ResponseDTO;
import org.openapitools.model.JobPostCustomeQuestionListDTO;
import org.openapitools.model.JobPostDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GetAllJobPostResponse implements FindJobs200ResponseDTO {
    private List<JobPostDTO> jobPostDTOList;
    private Set<JobPostCustomeQuestionListDTO> customeQuestionList= new HashSet<>();



}
