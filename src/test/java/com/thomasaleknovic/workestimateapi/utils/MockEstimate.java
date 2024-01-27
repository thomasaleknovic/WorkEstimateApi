package com.thomasaleknovic.workestimateapi.utils;

import java.util.ArrayList;
import java.util.List;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;

public class MockEstimate {
    
    static String estimateName;

    static String customerName;
    static Long cpf;
    static String address;
    static Long phone;

    static public EstimateDTO mockEstimateDTO () {
        return new EstimateDTO(null, null, null, null, null, null)
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
