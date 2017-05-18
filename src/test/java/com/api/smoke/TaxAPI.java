package com.api.smoke;


import com.apiUtils.*;
import com.config.*;
import com.controller.*;
import com.controller.GroupCreate;
import com.model.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;


import static io.restassured.config.HttpClientConfig.httpClientConfig;
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
        HelperMethods.assertCode("Group Created for TAX", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());

        SmartResponse resp= accountCreate.AccountCreate(config.createTaxAccount(),"vat", "vat");
        HelperMethods.assertCode("Account Created for TAX", resp.getStatusCode(), HttpStatus.SC_CREATED, resp.getJson());
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
            assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
        }
        else {
            assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
            String json = resp.getJson();
            JsonPath jp = new JsonPath(json);
            Tax_UniqueName = jp.get("body.uniqueName");
            Assert.assertNotNull(Tax_UniqueName);
            HelperMethods.setAnsiGreen("Tax created Successfully");
        }
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
