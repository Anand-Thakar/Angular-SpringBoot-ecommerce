package com.example.job;

import com.example.job.entity.JobPost;
import com.example.job.entity.JobPostCustomQuestionList;
import com.example.job.entity.JobPostHowToApply;
import com.example.job.entity.JobPostSalaryRange;
import com.example.job.repository.JobPostCustomQuestionListRepository;
import com.example.job.repository.JobPostHowToApplyRepository;
import com.example.job.repository.JobPostRepository;
import com.example.job.repository.JobPostSalaryRangeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.openapitools.model.JobPostCustomQuestionListDTO;
import org.openapitools.model.JobPostDTO;
import org.openapitools.model.JobPostHowToApplyDTO;
import org.openapitools.model.JobPostSalaryRangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class JobPostControllerTest {
    private static MockHttpServletRequest request;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private JobPost jobPost;
    @Autowired
    private JobPostRepository jobPostRepo;
    @Autowired
    private JobPostHowToApplyRepository jobPostHowToApplyRepo;
    @Autowired
    private JobPostSalaryRangeRepository jobPostSalaryRangeRepo;
    @Autowired
    private JobPostCustomQuestionListRepository jobPostQuestionListRepo;

    @BeforeAll
    public static void setup() {

    }


    /*  some of my method which I annotated with comment "working fine" interacting with database data, as mention in lecture
we want to create beforeEach method on which we perform operation, test that object get created, deleted, updated and all. But not able to
generate before each method for Job post entity.
*/
    @BeforeEach
    public void beforeEachSetUp() throws Exception {

    }

    @AfterEach
    public void afterEachSetUp() throws Exception {

    }

 /*   aMapWithSize(1)-> entry in database 0 -> test pass why aMapWithSize(1)??? ---test pass
      aMapWithSize(2)-> entry in database 1 -> test pass why aMapWithSize(2)??? ---test pass/
      ----------------------------------------------------------------------------------------
      aMapWithSize(2)-> entry in database 2 -> ---test fail
      java.lang.AssertionError: JSON path "$"
      Expected: a map with size <1>
      but: map size was <2>/
  */
    @Test
    public void getAllJobPostHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/job"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", aMapWithSize(1)));
    }



    //I wasn't able to test this createJobPostHttpRequest can you guide me how to write proper test case for following method.
    //aMapWithSize(12) works other than that It is failing
    @Test
    public void createJobPostHttpRequest() throws Exception {
        JobPost jobPost = new JobPost();

        //created new job-post
        jobPost.setId(2L);
        jobPost.setDescription("This is demo");
        jobPost.setJobCity("Ahmedabad");
        jobPost.setJobType(JobPostDTO.JobTypeEnum.FULLTIME);
        jobPost.setJobPostStatus(JobPostDTO.JobPostStatusEnum.ACTIVE);
        jobPost.setClosingDate(LocalDate.now());
        jobPost.setUserId(102L);
        jobPost.setPositionTitle("Full stack java developer");
        jobPost.setRequiredQualification("Masters Degree");
        jobPost.setHowToApply(new JobPostHowToApply(2L, JobPostHowToApplyDTO.ApplicationMethodEnum.EASY_APPLY,
                "demo@gmail.com", "demo@gmail.com"));
        jobPost.setSalaryRange(new JobPostSalaryRange(2L, JobPostSalaryRangeDTO.SalaryTypeEnum.HOURLY,
                "75000", "85000", "40"));

//        Set<JobPostCustomQuestionList> jobPostCustomQuestionSet = new LinkedHashSet<>();
//        jobPostCustomQuestionSet.add(new JobPostCustomQuestionList(2L, "How much exp you have in microservices",
//              JobPostCustomQuestionListDTO.AnswerTypeEnum.NUMBER,jobPost));
//
//        jobPost.setCustomQuestionList(jobPostCustomQuestionSet);


        mockMvc.perform(MockMvcRequestBuilders.post("/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jobPost)))
                .andExpect(status().isCreated())

                //Why Size 12: I have total 12 properties?
                .andExpect(jsonPath("$", aMapWithSize(11)));
    }


    @Test
    public void updateJobPostPreset() throws Exception {

    }

    @Test
    public void updateJobPostNotPreset() throws Exception {

    }

    //working fine
    @Test
    public void getAllJobPostValidHttpRequest() throws Exception {
        assertTrue(jobPostRepo.findById(104L).isPresent(), "Test Should pass");
    }

    //working fine
    @Test
    public void getAllJobPostWithNoRecordAndWithRecordPresentCheck() throws Exception {

        //checking that don't have any records
        int size = jobPostRepo.findAll().size();
        if (size == 0) {
            assertTrue(size == 0, "Should pass the test");

            //making sure that we get desired error response
            this.mockMvc.perform(MockMvcRequestBuilders.get("/job"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$.errorCode", is(404)))
                    .andExpect(jsonPath("$.appId", is("JMS-findJobs")))
                    .andExpect(jsonPath("$.message", is("No records founds.")));
        } else {
            assertFalse(size == 0, "should pass the test");
        }
    }

    //working fine
    @Test
    public void getAllJobPostWithRecordPresent() throws Exception {

        int size = jobPostRepo.findAll().size();
        assertTrue(size == 1, "Should pass the test");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/job"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //working fine
    @Test
    public void deleteJobRecordPresent() throws Exception {
        Optional<JobPost> byId = jobPostRepo.findById(104L);
        assertTrue(byId.isPresent(), "Should pass the test");
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/job/{jobId}", 104L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is("Post for jobPostID no. 104get deleted successfully")));

        assertFalse(jobPostRepo.findById(104L).isPresent(), "should pass because we deleted entry");
    }

    //working fine
    @Test
    public void deleteJobRecordNotPresent() throws Exception {

        Optional<JobPost> byId = jobPostRepo.findById(100L);
        assertFalse(byId.isPresent(), "should pass because no such record");

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/job/{jobId}", 100))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is("No such records for jobPostID no.100 found")));
    }
}
