package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import com.controller.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class FlattenAPI {

    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);
    getFlattenGroupWithAccountsAPI getFlatten =  new getFlattenGroupWithAccountsAPI();

    @DataProvider
    public Object[][] getSearchValue(){
        Object[][] searchValue = new  Object[2][1];
        searchValue[0][0]= "";
        searchValue[1][0]= "cash";
        return searchValue;
    }

    @DataProvider
    public  Object[][] getFlattenData(){
        Object[][] newData = new  Object[4][2];
        newData [0][0]= "";
        newData [0][1]= false;
        newData [1][0]= "cash";
        newData [1][1]= false;
        newData [2][0]= "";
        newData [2][1]= true;
        newData [3][0]= "cash";
        newData [3][1]= true;
        return  newData;
    }


    @Test(dataProvider = "getFlattenData")
    public void flatten_Group_with_Accounts(String searchValue, Boolean refreshValue){
        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts API");

        /**
         * Main test and api call initiated
         */
        SmartResponse response = getFlatten.getFlattenGroupWithAccountsAPI(null, null, searchValue, refreshValue);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (response.getStatusCode() == HttpStatus.SC_OK){
            if (searchValue.equalsIgnoreCase("cash")){
                assertEquals(jp.get("body.totalPages"), 1);
                assertEquals(jp.get("body.totalItems"), 1);
            }
            else {
                assertEquals(jp.get("body.totalPages"), 2);
                assertEquals(jp.get("body.totalItems"), 7);
            }
            assertEquals(jp.get("body.page"), 1);
            assertEquals(jp.get("body.count"), 5);
            HelperMethods.setAnsiGreen("Get flatten group-with-Accounts Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiRed("Get flatten group-with-Accounts Functionality Fails with Status code = " + response.getStatusCode());
        }
    }

    @Test(dataProvider = "getSearchValue")
    public void flatten_With_Accounts(String searchValue){
        HelperMethods.setAnsiGreen("Started :- Get flatten_with-Accounts");
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Accounts(), null, null, searchValue, false);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (searchValue.equalsIgnoreCase("")){
            if (response.getStatusCode() == HttpStatus.SC_OK){
                assertEquals(jp.get("body.results[4].uniqueName"), "taccount1");
                assertEquals(jp.get("body.results[0].stock"), null);
                assertEquals(jp.get("body.page"), 1);
                assertEquals(jp.get("body.count"), 11);
                HelperMethods.setAnsiGreen("Get flatten accounts Completed Successfully");
            }
            else {
                HelperMethods.setAnsiRed(response.getJson());
                HelperMethods.setAnsiRed(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
                HelperMethods.setAnsiRed("Get flatten accounts Functionality Fails");
            }
        }

        if (searchValue.equalsIgnoreCase("cash")){
            if (response.getStatusCode() == HttpStatus.SC_OK){
                assertEquals(jp.get("body.results[0].uniqueName"), "cash");
                assertEquals(jp.get("body.results[0].stock"), null);
                assertEquals(jp.get("body.page"), 1);
                assertEquals(jp.get("body.count"), 1);
                HelperMethods.setAnsiGreen("Get flatten accounts Completed with Cash Account Search Successfully");
            }
            else {
                HelperMethods.setAnsiRed(response.getJson());
                HelperMethods.setAnsiRed(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
                HelperMethods.setAnsiRed("Get flatten accounts with Cash Account Search Functionality Fails");
            }
        }
    }

    @AfterMethod
    public void  setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        RestAssured.reset();
    }
}
