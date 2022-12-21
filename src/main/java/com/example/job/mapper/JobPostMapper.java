package com.example.job.mapper;
import com.example.job.entity.JobPost;
import com.example.job.entity.JobPostCustomQuestionList;
import com.example.job.entity.JobPostHowToApply;
import com.example.job.entity.JobPostSalaryRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.JobPostCustomQuestionListDTO;
import org.openapitools.model.JobPostDTO;
import org.openapitools.model.JobPostHowToApplyDTO;
import org.openapitools.model.JobPostSalaryRangeDTO;

@Mapper(componentModel = "spring")
public interface JobPostMapper {
    JobPostMapper INSTANCE = Mappers.getMapper(JobPostMapper.class);


    JobPostDTO entityToDto(JobPost jobPost);

    JobPost dtoToEntity(JobPostDTO jobPostDTO);

    JobPostHowToApplyDTO entityToDto(JobPostHowToApply jobPostHowToApply);

    JobPostHowToApply dtoToEntity(JobPostHowToApplyDTO jobPostHowToApplyDTO);

    JobPostSalaryRangeDTO entityToDto(JobPostSalaryRange jobPostSalaryRange);

    JobPostSalaryRange dtoToEntity(JobPostSalaryRangeDTO jobPostSalaryRangeDTO);

    JobPostCustomQuestionListDTO entityToDto(JobPostCustomQuestionList jobPostCustomQuestionList);

    JobPostCustomQuestionList dtoToEntity(JobPostCustomQuestionListDTO jobPostCustomQuestionListDTO);

}

