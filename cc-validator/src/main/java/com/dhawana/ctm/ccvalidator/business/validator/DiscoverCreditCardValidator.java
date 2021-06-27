package com.dhawana.ctm.ccvalidator.business.validator;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import org.springframework.stereotype.Component;

@Component
public class DiscoverCreditCardValidator implements CreditCardValidator {
    private static final int DISCOVER_CARD_LENGTH = 16;
    @Override
    public String validateLength(String cardNumber) {
        String returnValue = null;
        if(cardNumber.startsWith(Constants.DISCOVER_START_STRING_1)) {
            if(cardNumber.length() == DISCOVER_CARD_LENGTH) {
                returnValue = Constants.DISCOVER_CARD_TYPE;
            } else {
                returnValue = Constants.DISCOVER_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD;
            }
        }
        return returnValue;
    }
}
