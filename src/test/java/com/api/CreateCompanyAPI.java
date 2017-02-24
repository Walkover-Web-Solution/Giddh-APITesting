package com.api;

import com.model.ManageURL;
import org.testng.annotations.Test;


public class CreateCompanyAPI {

    ManageURL baseURL = new ManageURL();



    @Test
    public  void sam(){
        baseURL.setURL();
        String URL = baseURL.getURL();
        System.out.println(URL);
    }

}
