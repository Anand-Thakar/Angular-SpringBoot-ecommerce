package com.example.job.mapper;

import com.example.job.entity.JobPost;
import com.example.job.entity.JobPostCustomQuestionList;
import com.example.job.entity.JobPostHowToApply;
import com.example.job.entity.JobPostSalaryRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.JobPostCustomeQuestionListDTO;
import org.openapitools.model.JobPostDTO;
import org.openapitools.model.JobPostHowToApplyDTO;
import org.openapitools.model.JobPostSalaryRangeDTO;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface JobPostMapper {

    JobPostMapper INSTANCE = Mappers.getMapper(JobPostMapper.class);

    @Mapping(source = "jobTypeEnum", target = "jobType")
    @Mapping(source = "requiredQualififcation", target = "requiredQualification")
    @Mapping(source = "jobPostStatusEnum", target = "jobPostStatus")
    @Mapping(source = "jobPostHowToApply", target = "howToApply")
    @Mapping(source = "jobPostSalaryRange",target = "salaryRange")
//    @Mapping(source = "customeQuestionList", target = "customeQuestionList")
    JobPostDTO entityToDto(JobPost jobPost);

    JobPost dtoToEntity(JobPostDTO jobPostDTO);

    @Mapping(source = "applicationMethodEnum",target = "applicationMethod")
    JobPostHowToApplyDTO entityToDto(JobPostHowToApply jobPostHowToApply);
    JobPostHowToApply dtoToEntity(JobPostHowToApplyDTO jobPostHowToApplyDTO);


    JobPostCustomeQuestionListDTO entityToDto(JobPostCustomQuestionList jobPostCustomQuestionList);
    JobPostCustomQuestionList dtoToEntity(JobPostCustomeQuestionListDTO jobPostCustomeQuestionListDTO);


    @Mapping(source = "salaryTypeEnum",target = "salaryType")
    JobPostSalaryRangeDTO entityToDto(JobPostSalaryRange jobPostSalaryRange);

    JobPostSalaryRange dtoToEntity(JobPostSalaryRangeDTO jobPostSalaryRangeDTO);

}

