package com.api.Smoke;


import com.ApiUtils.*;
import com.Config.*;
import com.model.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import java.util.*;


import static org.aeonbits.owner.ConfigFactory.create;

public class TaxAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    GroupCreate groupCreate = new GroupCreate();
    AccountCreate accountCreate = new AccountCreate();



    public void TaxSetup() throws Exception{

        SmartResponse response= groupCreate.GroupCreate(config.createGroup(),"Duties & Taxes", "duties_&_taxes", "currentliabilities");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }

        else {
            HelperMethods.setAnsiGreen("Group Created Successfully for TAX ");
        }

        SmartResponse resp= accountCreate.AccountCreate(config.createTaxAccount(),"vat", "vat");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(resp.getStatusCode());
            System.out.println(resp.getJson());
        }

        else {
            HelperMethods.setAnsiGreen(" Account Created Successfully for TAX ");
        }
    }

    @Test
    public void addTax() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Tax ");
        TaxSetup();

        TaxAccount taxAccount = new TaxAccount("vat");
        List<TaxDetails> taxDetail= new ArrayList<>();
        taxDetail.add(new TaxDetails("01-04-2017", 10));
        TaxInput taxInput = new TaxInput(taxDetail,"123456", "vat", "YEARLY", 01, taxAccount);
        String body = JsonUtil.toJsonAsString(taxInput);

        System.out.println(body);

        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(config.createTax(), body);

        if (resp.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(resp.getStatusCode());
            System.out.println(resp.getJson());
        }

        else {
            HelperMethods.setAnsiGreen(" Tax created Successfully ");
        }


    }
}
