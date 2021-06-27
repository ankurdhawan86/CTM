package com.dhawana.ctm.ccvalidator.business.service;

import com.dhawana.ctm.ccvalidator.business.validator.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CreditCardValidatorService {

    @Autowired
    List<CreditCardValidator> validators;

    public String getCardtype(String cardNumber){
        String cardType = null;
        for(CreditCardValidator validator : validators) {
            cardType = validator.validateLength(cardNumber);
            if(!Objects.isNull(cardType)) {
                break;
            }
        }
        if (Objects.isNull(cardType))
            cardType = Constants.UNKNOWN_CARD_TYPE;

        return cardType;
    }

    public boolean validateCardNumber(String cardNumber) {
        boolean result = Boolean.FALSE;
        int i = 0;
        int[] arr = new int[cardNumber.length()];

        for (char c : cardNumber.toCharArray()){
            arr[i++] = c - 48;
        }

        for (i = arr.length - 2; i >= 0; i = i - 2) {
            int j = arr[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            arr[i] = j;
        }
        int sum = Arrays.stream(arr).sum();

        if (sum % 10 == 0) {
            result = Boolean.TRUE;
        }
        return result;
    }
}
