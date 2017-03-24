package com.api.Smoke;


import com.ApiUtils.*;
import com.Config.*;
import com.model.*;
import org.testng.annotations.*;
import java.util.*;


import static org.aeonbits.owner.ConfigFactory.create;

public class TaxAPI {

    ApiManager apiManager = new ApiManager();
    UrlConfig config = create(UrlConfig.class);

    @Test
    public void addTax() throws  Exception{

        TaxAccount taxAccount = new TaxAccount("vat");
        List<TaxDetails> taxDetail= new ArrayList<>();
        taxDetail.add(new TaxDetails("01-01-2014", 10));
        TaxInput taxInput = new TaxInput(taxDetail,"123456", "vat", "YEARLY", 01, taxAccount);
        String body = JsonUtil.toJsonAsString(taxInput);


    }
}
