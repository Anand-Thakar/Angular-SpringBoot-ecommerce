package com.example.job.controller;

import com.example.job.JobApi;
import com.example.job.entity.JobPost;
import com.example.job.entity.JobPostCustomQuestionList;
import com.example.job.mapper.JobPostMapper;
import com.example.job.repository.JobPostRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@RestController
public class JobController implements JobApi {

    private JobPostRepository jobPostRepository;

    private JobPostMapper jobPostMapper;

//    private ModelMapper modelMapper;
//
//
//    public JobController(JobPostRepository jobPostRepository, JobPostMapper jobPostMapper, ModelMapper modelMapper) {
//        this.jobPostRepository = jobPostRepository;
//        this.jobPostMapper = jobPostMapper;
//        this.modelMapper = modelMapper;
//    }

    public JobController(JobPostRepository jobPostRepository, JobPostMapper jobPostMapper) {
        this.jobPostRepository = jobPostRepository;
        this.jobPostMapper = jobPostMapper;

    }


    @Override
    public ResponseEntity<String> deleteJobById(Long jobId) {
        if (jobPostRepository.findById(jobId).isPresent()) {
            jobPostRepository.deleteById(jobId);
            return new ResponseEntity<>("Post for" + jobId + "get deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such record for " + jobId + " found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<FindJobById200ResponseDTO> findJobById(Long jobId) {

        System.out.println();
        if (jobPostRepository.findById(jobId).isPresent()) {
            JobPost jobPost = jobPostRepository.findById(jobId).get();
            JobPostDTO responseDto = jobPostMapper.entityToDto(jobPost);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {

            ErrorResponseDTO errorResponseDTO = errorResponseDTO(404, "JMS-findByID", "No records found of " + jobId);
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

//    @Override
//    public ResponseEntity<FindJobById200ResponseDTO> findJobById(Long jobId) {
//        if (jobPostRepository.findById(jobId).isPresent()) {
//
//
//            JobPost jobPost = jobPostRepository.findById(jobId).get();
//            JobPostDTO responseDto = entityToDtoByModelMapper(jobPost);
//
//
///*//            for madel mapper to scan through the set creating Type Token
//            TypeToken<Set<JobPostCustomQuestionList>> typeToken = new TypeToken<Set<JobPostCustomQuestionList>>() {
//            };
//            Set<JobPostCustomQuestionList> set = new LinkedHashSet<>();
////            getting those question from the post and converting them
//            jobPost.getJobPostCustomQuestionListSet().forEach(entry -> set.add(modelMapper.map(set,JobPostCustomQuestionList.class)));
//            modelMapper.map(jobPost.getJobPostCustomQuestionListSet(), typeToken.getType());
//            return new ResponseEntity<>(responseDto, HttpStatus.OK);*/
//
//        } else {
//
//            ErrorResponseDTO errorResponseDTO = errorResponseDTO(404, "JMS-findByID", "No records found of " + jobId + "delete");
//            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
//        }
//    }

/*    @Override
    public ResponseEntity<FindJobs200ResponseDTO> findJobs() {
        List<JobPost> all = jobPostRepository.findAll();
        List<JobPostDTO> dtoList = all.stream().map(job -> jobPostMapper.entityToDto(job)).collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<CreateJob200ResponseDTO> updateJobPost(JobPostDTO jobPostDTO) {
        return JobApi.super.updateJobPost(jobPostDTO);
    }*/

    private ErrorResponseDTO errorResponseDTO(int errorCode, String appId, String message) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(errorCode);
        errorResponseDTO.setAppId(appId);
        errorResponseDTO.setDateTime(LocalDate.now() + "");
        errorResponseDTO.setMessage(message);
        return errorResponseDTO;
    }

    @Override
    public ResponseEntity<CreateJob200ResponseDTO> createJob(JobPostDTO jobPostDTO) {
        try {
            JobPost jobPost = jobPostMapper.dtoToEntity(jobPostDTO);
            JobPost savedJobPost = jobPostRepository.save(jobPost);
            JobPostDTO jobPostDTO1 = jobPostMapper.entityToDto(savedJobPost);
            return new ResponseEntity<>(jobPostDTO1, HttpStatus.CREATED);
        } catch (Exception ex) {

            ErrorResponseDTO errorResponseDTO = errorResponseDTO(500, "JMS-createJob", "Failed to createJob");
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
/*    private JobPost dtoToEntityByModelMapper(JobPostDTO jobPostDTO) {
        JobPost jobPost = modelMapper.map(jobPostDTO, JobPost.class);
        return jobPost;
    }

    private JobPostDTO entityToDtoByModelMapper(JobPost jobPost) {
        JobPostDTO jobPostDto = modelMapper.map(jobPost, JobPostDTO.class);
        return jobPostDto;
    }*/
}
