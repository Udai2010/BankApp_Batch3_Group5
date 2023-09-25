package com.bankapp.bankapp.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.model.Withdraw;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerService customerService;

    /**
     * Method under test: {@link AccountController#withdrawAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testWithdrawAccount() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        AccountController accountController = new AccountController();
        accountController.withdrawAccount(new Withdraw(1L, 10.0d));
    }

    /**
     * Method under test: {@link AccountController#withdrawAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testWithdrawAccount2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        (new AccountController()).withdrawAccount(null);
    }

    /**
     * Method under test: {@link AccountController#withdrawAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testWithdrawAccount3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        AccountController accountController = new AccountController();
        Withdraw withdraw = mock(Withdraw.class);
        when(withdraw.getAmount()).thenReturn(10.0d);
        when(withdraw.getAccount_id()).thenReturn(1L);
        accountController.withdrawAccount(withdraw);
    }

    /**
     * Method under test: {@link AccountController#Balance(Long)}
     */
    @Test
    void testBalance() throws Exception {
        when(accountService.getBalance(Mockito.<Long>any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/balance/{accId}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link AccountController#depositAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDepositAccount() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        AccountController accountController = new AccountController();
        accountController.depositAccount(new Withdraw(1L, 10.0d));
    }

    /**
     * Method under test: {@link AccountController#depositAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDepositAccount2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        (new AccountController()).depositAccount(null);
    }

    /**
     * Method under test: {@link AccountController#depositAccount(Withdraw)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDepositAccount3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.Withdraw]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.Withdraw` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        AccountController accountController = new AccountController();
        Withdraw deposit = mock(Withdraw.class);
        when(deposit.getAmount()).thenReturn(10.0d);
        when(deposit.getAccount_id()).thenReturn(1L);
        accountController.depositAccount(deposit);
    }

    /**
     * Method under test: {@link AccountController#fundTransfer(FundTransfer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFundTransfer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.FundTransfer]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.FundTransfer]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.FundTransfer` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        AccountController accountController = new AccountController();
        accountController.fundTransfer(new FundTransfer(3L, 3L, 10.0d));
    }

    /**
     * Method under test: {@link AccountController#fundTransfer(FundTransfer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFundTransfer2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.FundTransfer]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.bankapp.bankapp.model.FundTransfer]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.bankapp.bankapp.model.FundTransfer` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1915)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:414)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1360)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1424)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        (new AccountController()).fundTransfer(mock(FundTransfer.class));
    }

    /**
     * Method under test: {@link AccountController#createNewAccount(Account, Long)}
     */
    @Test
    void testCreateNewAccount() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccount_id(1L);
        account.setAccount_type("3");
        account.setAnnual_income(10.0d);
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDebit_card("Debit card");
        account.setIFSC("I FSC");
        account.setIncome_source("Income source");
        account.setNet_banking("Net banking");
        account.setOccupation_type("Occupation type");
        account.setStatus("Status");
        when(accountService.createAccount(Mockito.<Account>any(), Mockito.<Long>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setCustomer_Id(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPan_number("42");
        customer2.setPassword("iloveyou");

        Account account2 = new Account();
        account2.setAccount_id(1L);
        account2.setAccount_type("3");
        account2.setAnnual_income(10.0d);
        account2.setBalance(10.0d);
        account2.setBranch("janedoe/featurebranch");
        account2.setCustomer(customer2);
        account2.setDebit_card("Debit card");
        account2.setIFSC("I FSC");
        account2.setIncome_source("Income source");
        account2.setNet_banking("Net banking");
        account2.setOccupation_type("Occupation type");
        account2.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(account2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createAccount/{uid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Your new Account ID is 1\"]"));
    }

    /**
     * Method under test: {@link AccountController#createNewAccount(Account, Long)}
     */
    @Test
    void testCreateNewAccount2() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccount_id(1L);
        account.setAccount_type("3");
        account.setAnnual_income(10.0d);
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDebit_card("Debit card");
        account.setIFSC("I FSC");
        account.setIncome_source("Income source");
        account.setNet_banking("Net banking");
        account.setOccupation_type("Occupation type");
        account.setStatus("Status");
        when(accountService.createAccount(Mockito.<Account>any(), Mockito.<Long>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setCustomer_Id(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPan_number("42");
        customer2.setPassword("iloveyou");

        Account account2 = new Account();
        account2.setAccount_id(1L);
        account2.setAccount_type(null);
        account2.setAnnual_income(10.0d);
        account2.setBalance(10.0d);
        account2.setBranch("janedoe/featurebranch");
        account2.setCustomer(customer2);
        account2.setDebit_card("Debit card");
        account2.setIFSC("I FSC");
        account2.setIncome_source("Income source");
        account2.setNet_banking("Net banking");
        account2.setOccupation_type("Occupation type");
        account2.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(account2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createAccount/{uid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Account type can not be blank.\"]"));
    }

    /**
     * Method under test: {@link AccountController#getAccount(Long)}
     */
    @Test
    void testGetAccount() throws Exception {
        when(accountService.getAllAccounts(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/{id}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

