package com.thomasaleknovic.workestimateapi.services;

import static com.thomasaleknovic.workestimateapi.utils.MockEstimate.*;
import static com.thomasaleknovic.workestimateapi.utils.MockJobDetails.mockJobDetailsDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.models.JobDetails;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;


@ActiveProfiles("test")
public class EstimateServiceTest {


    @Mock
    EstimateRepository estimateRepository;

    @InjectMocks
    @Autowired
    EstimateService estimateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Estimates List on FindAllEstimates")
    void testFindAllEstimatesSuccess() {
    

       when(estimateRepository.findAll()).thenReturn(mockEstimateList());

       List<Estimate> result = estimateService.findAllEstimates();

       assertNotNull(result);
       assertEquals(result.get(0).getEstimateName() , ESTIMATE_NAME);
    }

    @Test
    @DisplayName("Should return a Estimates searching by it's id.")
    void testFindEstimateSuccess() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.of(mockEstimateEntity()));

        Estimate result = estimateService.findEstimate(mockEstimateEntity().getEstimateId());

        assertNotNull(result);
        assertEquals(result.getEstimateName() , ESTIMATE_NAME);


    }

    @Test
    @DisplayName("Should throw an exception when Estimate don't exists.")
    void testFindEstimateFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());


        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.findEstimate(mockEstimateEntity().getEstimateId());
        });

    }

    @Test
    @DisplayName("Should create a new Estimate.")
    void testCreateEstimateSuccess() {
        when(estimateRepository.save(any())).thenReturn(mockEstimateEntity());

        Estimate result = estimateService.createEstimate(mockEstimateDTO());

        assertNotNull(result);
        assertEquals(result.getEstimateName(), ESTIMATE_NAME);
    }

    // @Test
    // @DisplayName("Should throw a exception when trying to save a new invalid Estimate.")
    // void testCreateEstimateFail() {
    //     when(estimateRepository.save(any())).thenReturn(mockEstimateEntity());

    //     Estimate result = estimateService.createEstimate(mockEstimateDTO());

    //     assertThrows(ResponseStatusException.class, () -> {
    //         estimateService.findEstimate(mockEstimateEntity().getEstimateId());
    //     });
    // }

    @Test
    @DisplayName("Should delete an Estimate by it's Id successfully")
    void testDeleteEstimateSuccess() {
        Estimate estimate = mockEstimateEntity();
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.of(estimate));

        estimateService.deleteEstimate(mockEstimateEntity().getEstimateId());

        Mockito.verify(estimateRepository, times(1)).delete(estimate);

    }

    @Test
    @DisplayName("Should throw an exception trying to delete a Estimate by it's Id")
    void testDeleteEstimateFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());

       
        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.deleteEstimate(mockEstimateEntity().getEstimateId());
        });


    }

    @Test
    @DisplayName("Should update Estimate's name successfully")
    void testUpdateEstimateInfoSuccess() {
        Estimate estimate = mockEstimateEntity();
        EstimateDTO updateInfo = updatedMockEstimateDTO();
        Estimate resultEstimate = mockEstimateEntity();
        resultEstimate.setEstimateName(updateInfo.estimateName());
    
        when(estimateRepository.findById(estimate.getEstimateId())).thenReturn(Optional.of(estimate));
        when(estimateRepository.save(any())).thenReturn(resultEstimate);


        Estimate result = estimateService.updateEstimateInfo(estimate.getEstimateId(), updateInfo);

        assertNotNull(result);
        assertEquals(result.getEstimateName(), updatedMockEstimateDTO().estimateName());

    }

    @Test
    @DisplayName("Should throw an exception trying to update a Estimate by it's Id")
    void testUpdateEstimateFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());

       
        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.updateEstimateInfo(mockEstimateEntity().getEstimateId(), mockEstimateDTO());
        });


    }



    @Test
    @DisplayName("Should add job detail successfully on a Estimate.")
    void testAddJobDetail() {

        Estimate estimate = mockEstimateEntity();
        JobDetailsDTO jobDetails = mockJobDetailsDTO();
        estimate.getJobDetails().add(new JobDetails(jobDetails));

        when(estimateRepository.findById(estimate.getEstimateId())).thenReturn(Optional.of(estimate));
        when(estimateRepository.save(any())).thenReturn(estimate);
        
        Estimate result = estimateService.addJobDetail(estimate.getEstimateId(), jobDetails);

        assertNotNull(result);

    }

    
    @Test
    @DisplayName("Should throw an exception trying to add a job detail")
    void testAddJobDetailFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());

       
        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.addJobDetail(mockEstimateEntity().getEstimateId(), mockJobDetailsDTO());
        });


    }

   

    @Test
    @DisplayName("Should update a job detail successfully by it's id")
    void testUpdateJobDetailInfoSuccess() {
        Estimate estimate = mockEstimateEntity();
        estimate.getJobDetails().add(new JobDetails(mockJobDetailsDTO()));
        estimate.getJobDetails().get(0).setId(mockJobDetailsDTO().id());

        when(estimateRepository.findById(estimate.getEstimateId())).thenReturn(Optional.of(estimate));
        when(estimateRepository.save(any())).thenReturn(estimate);

        
        Estimate result = estimateService.updateJobDetailInfo(estimate.getEstimateId(), mockJobDetailsDTO());

        
        assertNotNull(result);

    }

    @Test
    @DisplayName("Should throw an exception trying to update a job detail")
    void testUpdateJobDetailInfoFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());

       
        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.updateJobDetailInfo(mockEstimateEntity().getEstimateId(), mockJobDetailsDTO());
        });


    }

    @Test
    @DisplayName("Should delete a job detail successfully by it's id")
    void testDeleteJobDetailInfoSuccess() {
        Estimate estimate = mockEstimateEntity();
        estimate.getJobDetails().add(new JobDetails(mockJobDetailsDTO()));
        estimate.getJobDetails().get(0).setId(mockJobDetailsDTO().id());

        when(estimateRepository.findById(estimate.getEstimateId())).thenReturn(Optional.of(estimate));
        when(estimateRepository.save(any())).thenReturn(estimate);
        
        Estimate result = estimateService.deleteJobDetailInfo(estimate.getEstimateId(), mockJobDetailsDTO().id());

        
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw an exception trying to update a job detail")
    void testDeleteJobDetailInfoFail() {
        when(estimateRepository.findById(mockEstimateEntity().getEstimateId())).thenReturn(Optional.empty());

       
        assertThrows(EstimateNotFoundException.class, () -> {
            estimateService.deleteJobDetailInfo(mockEstimateEntity().getEstimateId(), mockJobDetailsDTO().id());
        });


    }
}
