package com.thomasaleknovic.workestimateapi.utils;

import java.util.ArrayList;
import java.util.List;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;

public class MockEstimate {
    
   public static String ESTIMATE_NAME = "Orçamento teste";
   public static String CUSTOMER_NAME = "Jurandir da silva";
   public static String CPF = "83212359643";
   public static String ADDRESS = "Rua 1 de janeiro";
   public static String PHONE = "(11) 933124133";

    public static EstimateDTO mockEstimateDTO () {
        return new EstimateDTO(ESTIMATE_NAME, CUSTOMER_NAME, CPF, ADDRESS, PHONE, new ArrayList<>());
    
    }

    public static EstimateDTO updatedMockEstimateDTO () {
        return new EstimateDTO("Orçamento atualizado", CUSTOMER_NAME, CPF, ADDRESS, PHONE, new ArrayList<>());
    }

    public static Estimate mockEstimateEntity (){
       return new Estimate(mockEstimateDTO());
    }


    public static List<Estimate> mockEstimateList (){
        Estimate estimate = mockEstimateEntity();
        List<Estimate> listEstimates = new ArrayList<>();
        listEstimates.add(estimate);

        return listEstimates;

    }
    
}
