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

@Mapper(componentModel = "spring")
public interface JobPostMapper {

    JobPostMapper INSTANCE = Mappers.getMapper(JobPostMapper.class);

    @Mapping(source = "jobTypeEnum", target = "jobType")
    //there is spelling mistake here. Not same. same variable can be ignored
    @Mapping(source = "requiredQualififcation", target = "requiredQualification")
    @Mapping(source = "jobPostStatusEnum", target = "jobPostStatus")
    @Mapping(source = "jobPostHowToApply", target = "howToApply")
    @Mapping(source = "jobPostSalaryRange", target = "salaryRange")
    @Mapping(source = "jobPostCustomQuestionLists", target = "customeQuestionList")
    JobPostDTO entityToDto(JobPost jobPost);


    @Mapping(source = "jobType", target = "jobTypeEnum")
    @Mapping(source = "jobPostStatus", target = "jobPostStatusEnum")
    @Mapping(source = "howToApply", target = "jobPostHowToApply")
    @Mapping(source = "salaryRange", target = "jobPostSalaryRange")
    @Mapping(source = "customeQuestionList", target = "jobPostCustomQuestionLists")
    //there is spelling mistake here. Not same. same variable can be ignored
    @Mapping(source = "requiredQualification", target = "requiredQualififcation")
    JobPost dtoToEntity(JobPostDTO jobPostDTO);


    @Mapping(source = "applicationMethodEnum", target = "applicationMethod")
    JobPostHowToApplyDTO entityToDto(JobPostHowToApply jobPostHowToApply);

    @Mapping(source = "applicationMethod", target = "applicationMethodEnum")
    JobPostHowToApply dtoToEntity(JobPostHowToApplyDTO jobPostHowToApplyDTO);


    @Mapping(source = "salaryTypeEnum", target = "salaryType")
    JobPostSalaryRangeDTO entityToDto(JobPostSalaryRange jobPostSalaryRange);

    @Mapping(source = "salaryType", target = "salaryTypeEnum")
    JobPostSalaryRange dtoToEntity(JobPostSalaryRangeDTO jobPostSalaryRangeDTO);

    JobPostCustomeQuestionListDTO entityToDto(JobPostCustomQuestionList jobPostCustomQuestionList);
    JobPostCustomQuestionList dtoToEntity(JobPostCustomeQuestionListDTO jobPostCustomeQuestionListDTO);


}

