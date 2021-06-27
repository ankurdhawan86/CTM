package com.dhawana.ctm.ccvalidator.business.validator;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import org.springframework.stereotype.Component;

@Component
public class MasterCardCreditCardValidator implements CreditCardValidator {
    private static final int MASTERCARD_LENGTH = 16;
    @Override
    public String validateLength(String cardNumber) {
        String returnValue = null;
        if(cardNumber.startsWith(Constants.MASTERCARD_START_STRING_1)
                || cardNumber.startsWith(Constants.MASTERCARD_START_STRING_2)
                || cardNumber.startsWith(Constants.MASTERCARD_START_STRING_3)
                || cardNumber.startsWith(Constants.MASTERCARD_START_STRING_4)
                || cardNumber.startsWith(Constants.MASTERCARD_START_STRING_5)) {
            if(cardNumber.length() == MASTERCARD_LENGTH) {
                returnValue = Constants.MASTERCARD_CARD_TYPE;
            } else {
                returnValue = Constants.MASTERCARD_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD;
            }
        }
        return returnValue;
    }
}
