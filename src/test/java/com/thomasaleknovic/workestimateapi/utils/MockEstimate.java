package com.thomasaleknovic.workestimateapi.utils;

import java.util.ArrayList;
import java.util.List;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;

public class MockEstimate {
    
    static String ESTIMATENAME;
    static String CUSTOMERNAME;
    static Long CPF;
    static String ADDRESS;
    static Long PHONE;

    static public EstimateDTO mockEstimateDTO () {
        return new EstimateDTO(ESTIMATENAME, CUSTOMERNAME, CPF, ADDRESS, PHONE, null);
    }

    static public Estimate mockEstimateEntity (EstimateDTO data){
       return new Estimate(data);
    }


    static public List<Estimate> mockEstimateList (){
        Estimate estimate = new Estimate();
        List<Estimate> listEstimates = new ArrayList<>();
        listEstimates.add(estimate);

        return listEstimates;

    }
    
}
