package com.dhawana.ctm.ccvalidator.web;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import com.dhawana.ctm.ccvalidator.business.service.CreditCardValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/validateCreditCard")
public class CreditCardValidatorWebServiceController {

    @Autowired
    public CreditCardValidatorService creditCardValidatorService;

    @GetMapping
    public String validateCreditCard(@RequestParam(value="cardNumber", required = true) String cardNumber) {
        String cardType = null;
        boolean isCardValid = Boolean.FALSE;

        //Check if the card is non-numeric - return Invalid
        try {
            Long.parseLong(cardNumber);
        } catch (NumberFormatException ne) {
            cardType = Constants.UNKNOWN_CARD_TYPE;
            isCardValid = Boolean.FALSE;
        }

        if(!Constants.UNKNOWN_CARD_TYPE.equalsIgnoreCase(cardType)) {
            cardType = this.creditCardValidatorService.getCardtype(cardNumber);

            if(!cardType.equalsIgnoreCase(Constants.UNKNOWN_CARD_TYPE) && !cardType.contains(Constants.INVALID_CARD)) {
                isCardValid = this.creditCardValidatorService.validateCardNumber(cardNumber);
            } else {
                //Invalid Card Length
                if (cardType.contains(Constants.INVALID_CARD)) {
                    String[] invalidLengthCard = cardType.split(Constants.SEPARATOR);
                    if (invalidLengthCard.length == 2) {
                        cardType = invalidLengthCard[0];
                        isCardValid = false;
                    } else {
                        cardType = Constants.UNKNOWN_CARD_TYPE;
                        isCardValid = Boolean.FALSE;
                    }
                }
            }
        }
        cardType = cardType + Constants.SEPARATOR;
        String returnString = String.join(Constants.SPACE,cardType,cardNumber, isCardValid ? Constants.VALID_CARD : Constants.INVALID_CARD);
        return returnString;

    }
}
