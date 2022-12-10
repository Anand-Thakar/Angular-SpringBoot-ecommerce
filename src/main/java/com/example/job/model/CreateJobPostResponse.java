package com.example.job.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.model.CreateJob200ResponseDTO;
import  org.openapitools.model.JobPostDTO;


@AllArgsConstructor
@NoArgsConstructor
public class CreateJobPostResponse implements CreateJob200ResponseDTO {
     private JobPostDTO jobPostDTO;
}
