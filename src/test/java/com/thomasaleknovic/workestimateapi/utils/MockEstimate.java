package com.thomasaleknovic.workestimateapi.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.models.Customer;
import com.thomasaleknovic.workestimateapi.models.Estimate;

import static com.thomasaleknovic.workestimateapi.utils.MockCustomer.mockCustomerEntity;

public class MockEstimate {
    
   public static String ESTIMATE_NAME = "Orçamento teste";
   public static int SERVICE_ORDER = 1000;
   public static String OBSERVATION = "Observação teste";
   public static String PAYMENT_METHOD = "PIX 434123123312";
   public static BigDecimal TOTAL_PRICE = new BigDecimal(1000.00);
   public static UUID CUSTOMER_ID = UUID.fromString("64188f14-95d4-484d-b8ef-b7b1a6730afe");

    public static EstimateDTO mockEstimateDTO () {
        return new EstimateDTO(ESTIMATE_NAME, SERVICE_ORDER, OBSERVATION, PAYMENT_METHOD, TOTAL_PRICE, new ArrayList<>(), CUSTOMER_ID);
    
    }

    public static EstimateDTO updatedMockEstimateDTO () {
        return new EstimateDTO("Orçamento atualizado", SERVICE_ORDER, OBSERVATION, PAYMENT_METHOD, TOTAL_PRICE, new ArrayList<>(), CUSTOMER_ID);
    }

    public static Estimate mockEstimateEntity (){
       return new Estimate(mockCustomerEntity(), mockEstimateDTO());
    }


    public static List<Estimate> mockEstimateList (){
        Estimate estimate = mockEstimateEntity();
        List<Estimate> listEstimates = new ArrayList<>();
        listEstimates.add(estimate);

        return listEstimates;

    }
    
}
