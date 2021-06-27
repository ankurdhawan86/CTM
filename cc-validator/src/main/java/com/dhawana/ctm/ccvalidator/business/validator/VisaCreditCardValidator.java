package com.dhawana.ctm.ccvalidator.business.validator;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import org.springframework.stereotype.Component;

@Component
public class VisaCreditCardValidator implements CreditCardValidator{
    private static final int VISA_CARD_LENGTH_1 = 13;
    private static final int VISA_CARD_LENGTH_2 = 16;
    @Override
    public String validateLength(String cardNumber) {
        String returnValue = null;
        if(cardNumber.startsWith(Constants.VISA_START_STRING_1)) {
            if(cardNumber.length() == VISA_CARD_LENGTH_1 || cardNumber.length() == VISA_CARD_LENGTH_2) {
                returnValue = Constants.VISA_CARD_TYPE;
            } else {
                returnValue = Constants.VISA_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD;
            }
        }
        return returnValue;
    }
}
