package com.thomasaleknovic.workestimateapi.models;

import lombok.Setter;

import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.PaymentMethodDTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PaymentMethod {

  
    public enum PaymentTitle {
        PIX, BOLETO, TRANSFERENCIA_BANCARIA, CREDITO, A_VISTA
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private PaymentTitle paymentTitle;
    private String paymentDescription;

    public PaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentTitle = paymentMethod.paymentTitle();
        this.paymentDescription = paymentMethod.paymentDescription();
    }
}

