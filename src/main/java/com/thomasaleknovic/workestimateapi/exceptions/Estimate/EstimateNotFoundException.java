package com.thomasaleknovic.workestimateapi.exceptions.Estimate;

public class EstimateNotFoundException extends RuntimeException {

    public EstimateNotFoundException() {
        super("Não foi possível encontrar o orçamento.");
    }
}
