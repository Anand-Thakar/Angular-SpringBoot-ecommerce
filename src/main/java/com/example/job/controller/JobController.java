package com.example.job.controller;
import com.example.job.JobApi;
import com.example.job.entity.JobPost;
import com.example.job.mapper.JobPostMapper;
import com.example.job.model.GetAllJobPostResponse;
import com.example.job.repository.JobPostRepository;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@RestController
public class JobController implements JobApi {
    private JobPostRepository jobPostRepository;
    private JobPostMapper jobPostMapper;

    public JobController(JobPostRepository jobPostRepository, JobPostMapper jobPostMapper) {
        this.jobPostRepository = jobPostRepository;
        this.jobPostMapper = jobPostMapper;
    }

    @Override
    public ResponseEntity<CreateJob200ResponseDTO> createJob(JobPostDTO jobPostDTO) {
        try {
            JobPost jobPost = jobPostMapper.dtoToEntity(jobPostDTO);
            JobPost savedJobPost = jobPostRepository.save(jobPost);
            JobPostDTO jobPostDTO1 = jobPostMapper.entityToDto(savedJobPost);
            return new ResponseEntity<>(jobPostDTO1, HttpStatus.CREATED);
        } catch (Exception ex) {

            ErrorResponseDTO errorResponseDTO = errorResponseDTO(500, "JMS-createJob", "Failed to createJob because " + ex.getMessage() + ex.getStackTrace());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FindJobById200ResponseDTO> findJobById(Long jobId) {

        if (jobPostRepository.findById(jobId).isPresent()) {
            JobPost jobPost = jobPostRepository.findById(jobId).get();
            JobPostDTO responseDto = jobPostMapper.entityToDto(jobPost);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            ErrorResponseDTO errorResponseDTO = errorResponseDTO(404, "JMS-findByID", "No records found of " + jobId);
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteJobById(Long jobId) {

        if (jobPostRepository.findById(jobId).isPresent()) {
            jobPostRepository.deleteById(jobId);
            return new ResponseEntity<>("Post for jobPostID no. " + jobId + "get deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such records for jobPostID no." + jobId + " found", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<FindJobs200ResponseDTO> findJobs() {

        boolean bo = jobPostRepository.findAll().size() == 0;
        if (bo) {
            ErrorResponseDTO errorResponseDTO = errorResponseDTO(404, "JMS-findJobs", "No records founds.");
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        }
        GetAllJobPostResponse getAllJobPostResponse = new GetAllJobPostResponse();
        List<JobPost> all = jobPostRepository.findAll();
        List<JobPostDTO> dtoList = all.stream().map(entry -> jobPostMapper.entityToDto(entry)).collect(Collectors.toList());

        //adding list in other class
        getAllJobPostResponse.setJobPostDTOList(dtoList);
        return new ResponseEntity<>(getAllJobPostResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CreateJob200ResponseDTO> updateJobPost(Long jobId, JobPostDTO jobPostDTO) {
        boolean present = jobPostRepository.findById(jobId).isPresent();
        if (present) {
            JobPost jobPost = jobPostMapper.dtoToEntity(jobPostDTO);
            JobPost save = jobPostRepository.save(jobPost);
            System.out.println(save);
            JobPostDTO responseDto = jobPostMapper.entityToDto(save);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        } else {
            ErrorResponseDTO errorResponseDTO = errorResponseDTO(404,
                    "JMS-updateJobPost", "Failed to update Job Post for JobPost Id " + jobId);
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    public ErrorResponseDTO errorResponseDTO(int errorCode, String appId, String message) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(errorCode);
        errorResponseDTO.setAppId(appId);
        errorResponseDTO.setDateTime(LocalDate.now() + "");
        errorResponseDTO.setMessage(message);
        return errorResponseDTO;
    }
}
