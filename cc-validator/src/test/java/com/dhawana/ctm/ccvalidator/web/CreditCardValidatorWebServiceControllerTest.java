package com.dhawana.ctm.ccvalidator.web;

import com.dhawana.ctm.ccvalidator.business.service.Constants;
import com.dhawana.ctm.ccvalidator.business.service.CreditCardValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardValidatorWebServiceController.class)
public class CreditCardValidatorWebServiceControllerTest {
    @MockBean
    private CreditCardValidatorService creditCardValidatorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void alphanumericCCNumberTest() throws Exception {
        String ccNumber = null;

        //Non-numeric
        ccNumber = "37828224631ABC";
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.UNKNOWN_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }

    @Test
    public void amexCCInvalidLengthTest() throws Exception {
        String ccNumber = null;

        //AMEX invalid length
        ccNumber = "37828224631000";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.AMEX_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.AMEX_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void amexCCInvalidNumberTest() throws Exception {
        String ccNumber = null;
        //AMEX valid length, invalid number
        ccNumber = "378282246310006";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.AMEX_CARD_TYPE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.AMEX_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void amexCCValidTest() throws Exception {
        String ccNumber = null;
        //AMEX valid
        ccNumber = "378282246310005";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.AMEX_CARD_TYPE);
        given(creditCardValidatorService.validateCardNumber(ccNumber)).willReturn(Boolean.TRUE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.AMEX_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.VALID_CARD));
    }

    @Test
    public void discoverCCInvalidLengthTest() throws Exception {
        String ccNumber = null;
        //Discover invalid length
        ccNumber = "601111111111111";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.DISCOVER_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.DISCOVER_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void discoverCCInvalidNumberTest() throws Exception {
        String ccNumber = null;

        //Discover valid length, invalid number
        ccNumber = "6011111111111116";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.DISCOVER_CARD_TYPE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.DISCOVER_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void discoverCCValidTest() throws Exception {
        String ccNumber = null;

        //Discover valid
        ccNumber = "6011111111111117";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.DISCOVER_CARD_TYPE);
        given(creditCardValidatorService.validateCardNumber(ccNumber)).willReturn(Boolean.TRUE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.DISCOVER_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.VALID_CARD));
    }

    @Test
    public void mastercardCCInvalidLengthTest() throws Exception {
        String ccNumber = null;
        //Mastercard invalid length
        ccNumber = "510510510510510";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.MASTERCARD_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.MASTERCARD_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void mastercardCCInvalidNumberTest() throws Exception {
        String ccNumber = null;

        //Mastercard valid length, invalid number
        ccNumber = "5105105105105106";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.MASTERCARD_CARD_TYPE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.MASTERCARD_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void mastercardCCValidTest() throws Exception {
        String ccNumber = null;

        //Mastercard valid
        ccNumber = "5105105105105100";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.MASTERCARD_CARD_TYPE);
        given(creditCardValidatorService.validateCardNumber(ccNumber)).willReturn(Boolean.TRUE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.MASTERCARD_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.VALID_CARD));
    }

    @Test
    public void visaCCInvalidLengthTest() throws Exception {
        String ccNumber = null;
        //VISA invalid length
        ccNumber = "41111111111111";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.VISA_CARD_TYPE + Constants.SEPARATOR + Constants.INVALID_CARD);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.VISA_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }
    @Test
    public void visaCCInvalidNumberTest() throws Exception {
        String ccNumber = null;
        //VISA valid length, invalid number
        ccNumber = "4111111111111";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.VISA_CARD_TYPE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.VISA_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));

    }
    @Test
    public void visaCCValidTest() throws Exception {
        String ccNumber = null;
        //VISA valid
        ccNumber = "4111111111111111";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.VISA_CARD_TYPE);
        given(creditCardValidatorService.validateCardNumber(ccNumber)).willReturn(Boolean.TRUE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.VISA_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.VALID_CARD));
    }

    @Test
    public void unknownCCTest() throws Exception {
        String ccNumber = null;
        //Unknown
        ccNumber = "9111111111111111";
        given(creditCardValidatorService.getCardtype(ccNumber)).willReturn(Constants.UNKNOWN_CARD_TYPE);
        given(creditCardValidatorService.validateCardNumber(ccNumber)).willReturn(Boolean.FALSE);
        this.mockMvc.perform(get("/api/validateCreditCard?cardNumber=" + ccNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.UNKNOWN_CARD_TYPE + Constants.SEPARATOR + Constants.SPACE + ccNumber + Constants.SPACE + Constants.INVALID_CARD));
    }

}
