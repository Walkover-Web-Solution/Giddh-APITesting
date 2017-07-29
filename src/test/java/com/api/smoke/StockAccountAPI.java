package com.api.smoke;

import com.apiUtils.*;
import com.config.UrlConfig;
import com.controller.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import java.math.BigDecimal;

import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.aeonbits.owner.ConfigFactory.create;


public class StockAccountAPI {

    private MethodManager methodManager = new MethodManager();
    private StockCreate stockCreate = new StockCreate();
    private UrlConfig config = create(UrlConfig.class);

    public static String stock_UniqueName = "bulksms";

    @DataProvider
    public Object[][] stock(){
        Object[][] createStock = new  Object[1][18];
        createStock [0][0]= null;
        createStock [0][1]= null;
        createStock [0][2]= config.createStock();
        createStock [0][3]= "nos";
        createStock [0][4]= BigDecimal.valueOf(1);
        createStock [0][5]= "nos";
        createStock [0][6]= BigDecimal.valueOf(1);
        createStock [0][7]= "sales";
        createStock [0][8]= "purchases";
        createStock [0][9]= stock_UniqueName;
        createStock [0][10]= "nos";
        createStock [0][11]= BigDecimal.ZERO;
        createStock [0][12]= BigDecimal.ZERO;
        createStock [0][13]= BigDecimal.valueOf(1);
        createStock [0][14]= "nos";
        createStock [0][15]= stock_UniqueName;
        createStock [0][16]= BigDecimal.valueOf(1);
        createStock [0][17]= "nos";
        return  createStock;
    }

    @DataProvider
    public Object[][] updateStock(){
        Object[][] updateStock = new  Object[1][18];
        updateStock [0][0]= null;
        updateStock [0][1]= null;
        updateStock [0][2]= config.createStock()+ stock_UniqueName;
        updateStock [0][3]= "nos";
        updateStock [0][4]= BigDecimal.valueOf(2);
        updateStock [0][5]= "nos";
        updateStock [0][6]= BigDecimal.valueOf(2);
        updateStock [0][7]= "sales";
        updateStock [0][8]= "purchases";
        updateStock [0][9]= stock_UniqueName;
        updateStock [0][10]= "nos";
        updateStock [0][11]= BigDecimal.TEN;
        updateStock [0][12]= BigDecimal.TEN;
        updateStock [0][13]= BigDecimal.valueOf(1);
        updateStock [0][14]= "nos";
        updateStock [0][15]= stock_UniqueName;
        updateStock [0][16]= BigDecimal.valueOf(1);
        updateStock [0][17]= "nos";
        return  updateStock;
    }

    @Test(dataProvider = "stock")
    public void createStock(String auth, String type, String URL, String salesStockUnitCode, BigDecimal saleValue,String purchaseStockUnitCode,
                            BigDecimal purchaseValue, String salesAccountUniqueName, String purchaseAccountUniqueName, String stockName,
                            String stockUniqueCode, BigDecimal openingAmount, BigDecimal openingQty, BigDecimal manufacturingQuantity,
                            String manufacturingUnitCode,String stockUniqueName, BigDecimal quantity,String stockUnitCode) throws JsonProcessingException {

        HelperMethods.setAnsiGreen("Started :- Create Stock ");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = stockCreate.StockCreate(auth, type, URL, salesStockUnitCode, saleValue, purchaseStockUnitCode,
                purchaseValue,  salesAccountUniqueName, purchaseAccountUniqueName,  stockName,  stockUniqueCode,  openingAmount,
                openingQty, manufacturingQuantity, manufacturingUnitCode, stockUniqueName, quantity, stockUnitCode);

        HelperMethods.assertCode("Create Stock", response.getStatusCode(), HttpStatus.SC_CREATED, response.getJson());
    }


    @Test(dependsOnMethods = {"createStock"})
    public void getStock()  {
        HelperMethods.setAnsiGreen("Started :- Get Stock ");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.getAPI_with_Assert_Statuscode(null, null, config.createStock() + stock_UniqueName);
        HelperMethods.assertCode("Get Stock", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());

    }

    @Test(dependsOnMethods = {"createStock"}, dataProvider = "updateStock")
    public void updateStock(String auth, String type, String URL, String salesStockUnitCode, BigDecimal saleValue,String purchaseStockUnitCode,
                            BigDecimal purchaseValue, String salesAccountUniqueName, String purchaseAccountUniqueName, String stockName,
                            String stockUniqueCode, BigDecimal openingAmount, BigDecimal openingQty, BigDecimal manufacturingQuantity,
                            String manufacturingUnitCode,String stockUniqueName, BigDecimal quantity,String stockUnitCode) throws JsonProcessingException {

        HelperMethods.setAnsiGreen("Started :- Update Stock ");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = stockCreate.StockUpdate(auth, type, URL, salesStockUnitCode, saleValue, purchaseStockUnitCode,
                purchaseValue,  salesAccountUniqueName, purchaseAccountUniqueName,  stockName,  stockUniqueCode,  openingAmount,
                openingQty, manufacturingQuantity, manufacturingUnitCode, stockUniqueName, quantity, stockUnitCode);

        HelperMethods.assertCode("Update Stock", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());
    }


    public void deleteStock()  {
        HelperMethods.setAnsiGreen("Started :- Delete Stock ");

        /**
         * Main test and api call initiated
         */

        SmartResponse response = methodManager.deleteAPI_with_Assert_Statuscode(null, null, config.deleteStock() + stock_UniqueName);
        HelperMethods.assertCode("Delete Stock", response.getStatusCode(), HttpStatus.SC_OK, response.getJson());

    }

    @AfterMethod
    public void setup(){
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().reuseHttpClientInstance());
        //RestAssured.reset();
    }
}
