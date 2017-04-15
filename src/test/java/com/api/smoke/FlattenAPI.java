package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.testng.Assert.assertEquals;

public class FlattenAPI {


    MethodManager methodManager = new MethodManager();
    UrlConfig config = create(UrlConfig.class);

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[1][0];
        return  data;
    }

    @DataProvider
    public Object[][] getSearchValue(){
        Object[][] searchValue = new  Object[2][1];
        searchValue[0][0]= "";
        searchValue[1][0]= "cash";
        return searchValue;
    }

    @Test( parameters = "paramName")
    public void flatten_Group_with_Accounts(String testvariable){

        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts API");
        HelperMethods.setAnsiRed(testvariable);

        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Group_With_Accounts(), null, null, null, true);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (response.getStatusCode() == HttpStatus.SC_OK){
            assertEquals(jp.get("body.page"), 1);
            assertEquals(jp.get("body.count"), 5);
            assertEquals(jp.get("body.totalPages"), 2);
            assertEquals(jp.get("body.totalItems"), 7);
            HelperMethods.setAnsiGreen("Get flatten group-with-Accounts Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed(response.getJson());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiRed("Get flatten group-with-Accounts Functionality Fails");
        }
    }


    @Test(dataProvider = "getData", dependsOnMethods={"flatten_Group_with_Accounts"})
    public void flatten_Group_with_Accounts_with_Cash_Search(){
        HelperMethods.setAnsiGreen("Started :- Get flatten group-with-accounts with_Cash_Search");
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Group_With_Accounts(), null, null, "cash", true);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (response.getStatusCode() == HttpStatus.SC_OK){
            assertEquals(jp.get("body.results[0].groupUniqueName"), "cash");
            assertEquals(jp.get("body.page"), 1);
            assertEquals(jp.get("body.count"), 5);
            HelperMethods.setAnsiGreen("Get flatten group-with-accounts with_Cash_Search functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed(response.getJson());
            HelperMethods.setAnsiRed(response.getJson());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            HelperMethods.setAnsiRed("Get flatten group-with-accounts with_Cash_Search Functionality Fails");
        }
    }


    @Test(dataProvider = "getSearchValue")
    public void flatten_With_Accounts(String searchValue){
        HelperMethods.setAnsiGreen("Started :- Get flatten_with-Accounts");
        /**
         * Main test and api call initiated
         */
        SmartResponse response = methodManager.getAPI_With_Params(null, null, config.get_Flatten_Accounts(), null, null, searchValue, true);
        String json = response.getJson();
        JsonPath jp = new JsonPath(json);
        if (searchValue == ""){
            if (response.getStatusCode() == HttpStatus.SC_OK){
                assertEquals(jp.get("body.results[1].uniqueName"), "giddh");
                assertEquals(jp.get("body.results[0].stock"), null);
                assertEquals(jp.get("body.page"), 1);
                assertEquals(jp.get("body.count"), 7);
                HelperMethods.setAnsiGreen("Get flatten accounts Completed Successfully");
            }
            else {
                HelperMethods.setAnsiRed(response.getJson());
                HelperMethods.setAnsiRed(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
                HelperMethods.setAnsiRed("Get flatten accounts Functionality Fails");
            }
        }

        if (searchValue == "cash"){
            if (response.getStatusCode() == HttpStatus.SC_OK){
                assertEquals(jp.get("body.results[0].uniqueName"), "cash");
                assertEquals(jp.get("body.results[0].stock"), null);
                assertEquals(jp.get("body.page"), 1);
                assertEquals(jp.get("body.count"), 1);
                HelperMethods.setAnsiGreen("Get flatten accounts Completed Successfully");
            }
            else {
                HelperMethods.setAnsiRed(response.getJson());
                HelperMethods.setAnsiRed(response.getJson());
                Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
                HelperMethods.setAnsiRed("Get flatten accounts Functionality Fails");
            }
        }
    }
}
