package com.thomasaleknovic.workestimateapi.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;

public class EstimateServiceTest {


    @Mock
    EstimateRepository estimateRepository;

    @InjectMocks
    @Autowired
    EstimateService estimateService;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("EstimateService should return a Estimates List on FindAllEstimates")
    void testFindAllEstimatesSuccess() {
    

//        when(estimateRepository.findAll()).thenReturn();
    }

    @Test
    void testAddJobDetail() {

    }

    @Test
    void testCreateEstimate() {

    }

    @Test
    void testDeleteEstimate() {

    }

    @Test
    void testDeleteJobDetailInfo() {

    }


    @Test
    void testFindEstimate() {

    }

    @Test
    void testUpdateEstimateInfo() {

    }

    @Test
    void testUpdateJobDetailInfo() {

    }
}
