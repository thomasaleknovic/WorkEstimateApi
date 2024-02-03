package com.thomasaleknovic.workestimateapi.utils;


import static com.thomasaleknovic.workestimateapi.utils.MockEstimate.mockEstimateEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;

public class MockJobDetails {
    
    public static UUID ID = UUID.fromString("64188f14-95d4-484d-b8ef-b7b1a6730afe");
    public static String TITLE = "Item teste";
    public static String DESCRIPTION = "Descrição do item";
    public static Short QUANTITY = 1;
    public static BigDecimal UNIT_PRICE = new BigDecimal("2000");
    

    public static JobDetailsDTO mockJobDetailsDTO () {
        return new JobDetailsDTO(ID, DESCRIPTION, QUANTITY, UNIT_PRICE);
    }

      public static List<JobDetailsDTO> mockJobDetailsList (){
        List<JobDetailsDTO> jobDetails = new ArrayList<>();
        jobDetails.add(mockJobDetailsDTO());

        return jobDetails;

    }

}
