package com.api.smoke;


import com.apiUtils.*;
import com.config.*;
import com.controller.AccountCreate;
import com.controller.GroupCreate;
import com.model.*;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;


import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class TaxAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    GroupCreate groupCreate = new GroupCreate();
    AccountCreate accountCreate = new AccountCreate();

    public static String Tax_UniqueName;


    @BeforeClass
    public void TaxSetup() throws Exception{

        SmartResponse response= groupCreate.GroupCreate(config.createGroup(),"Duties & Taxes", "duties_&_taxes", "currentliabilities");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(response.getStatusCode());
            System.out.println(response.getJson());
        }

        else {
            HelperMethods.setAnsiGreen("Group Created Successfully for TAX");
        }

        SmartResponse resp= accountCreate.AccountCreate(config.createTaxAccount(),"vat", "vat");
        if (response.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(resp.getStatusCode());
            System.out.println(resp.getJson());
        }
        else {
            HelperMethods.setAnsiGreen("Account Created Successfully for TAX");
        }
    }

    @Test
    public void addTax() throws  Exception{
        HelperMethods.setAnsiGreen("Started :- Create Tax");

        TaxAccount taxAccount = new TaxAccount("vat");
        List<TaxDetails> taxDetail= new ArrayList<>();
        taxDetail.add(new TaxDetails("01-04-2017", 10));
        TaxInput taxInput = new TaxInput(taxDetail,"123456", "vat", "YEARLY", 01, taxAccount);
        String body = JsonUtil.toJsonAsString(taxInput);

        /**
         * Main test and api call initiated
         */
        SmartResponse resp = methodManager.postAPI_with_Assert_Statuscode(null, null,config.createTax(), body);
        if (resp.getStatusCode() != HttpStatus.SC_CREATED){
            System.out.println(resp.getStatusCode());
            System.out.println(resp.getJson());
        }
        else {
            Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
            Tax_UniqueName = jp.get("body.uniqueName");
            Assert.assertNotNull(Tax_UniqueName);
            HelperMethods.setAnsiGreen("Tax created Successfully");
        }
    }
}
