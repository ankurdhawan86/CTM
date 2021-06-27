package com.dhawana.ctm.ccvalidator.business.validator;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import org.springframework.stereotype.Component;

@Component
public class AmexCreditCardValidator implements CreditCardValidator {
    private static final int AMEX_CARD_LENGTH = 15;
    @Override
    public String validateLength(String cardNumber) {
        String returnValue = null;
        if(cardNumber.startsWith(Constants.AMEX_START_STRING_1)
            || cardNumber.startsWith(Constants.AMEX_START_STRING_2)) {
            if(cardNumber.length() == AMEX_CARD_LENGTH) {
                returnValue = Constants.AMEX_CARD_TYPE;
            } else {
                returnValue = Constants.AMEX_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD;
            }
        }
        return returnValue;
    }
}
