package com.thomasaleknovic.workestimateapi.utils;


import static com.thomasaleknovic.workestimateapi.utils.MockEstimate.mockEstimateEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.id.uuid.UuidGenerator;

import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;

public class MockJobDetails {
    
    public static UUID ID = UUID.fromString("64188f14-95d4-484d-b8ef-b7b1a6730afe");
    public static String TITLE = "Item teste";
    public static String DESCRIPTION = "Descrição do item";
    public static BigDecimal PRICE = new BigDecimal("2000");
    

    public static JobDetailsDTO mockJobDetailsDTO () {
        return new JobDetailsDTO(ID, TITLE, DESCRIPTION, PRICE);
    }

      public static List<JobDetailsDTO> mockJobDetailsList (){
        List<JobDetailsDTO> jobDetails = new ArrayList<>();
        jobDetails.add(mockJobDetailsDTO());

        return jobDetails;

    }

}
