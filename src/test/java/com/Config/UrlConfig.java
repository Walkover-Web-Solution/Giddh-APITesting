package com.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/URL.${api.environment}.properties"})
public interface UrlConfig extends  Config{

    @Key("url")
    String baseURL();

    @Key("baseURL")
    String mainURL();

    @Key("get.Company")
    String getCompany();

    @Key("share.Company")
    String shareCompany();

    @Key("unshare.Company")
    String unshareCompany();

    @Key("delete.Company")
    String deleteCompany();

    @Key("group.Create")
    String createGroup();

    @Key("group.Move")
    String moveGroup();

    @Key("group.Update")
    String updateGroup();

    @Key("group.Get")
    String getGroup();

    @Key("group.Share")
    String shareGroup();

    @Key("group.unShare")
    String unshareGroup();

    @Key("group.Delete")
    String deleteGroup();

    @Key("account.Create")
    String createAccount();

    @Key("account.Update")
    String updateAccount();

    @Key("account.Get")
    String getAccount();

    @Key("account.Share")
    String shareAccount();

    @Key("account.unShare")
    String unshareAccount();

    @Key("account.Delete")
    String deleteAccount();

    @Key("ledger.Create")
    String createLedger();

    @Key("ledger.Get")
    String getLedger();

    @Key("ledger.Update")
    String updateLedger();

    @Key("ledger.Delete")
    String deleteLedger();

    @Key("invoice.Create")
    String createInvoice();

    @Key("invoice.Get")
    String getAllInvoice();

    @Key("invoice.Delete")
    String deleteInvoice();

    @Key("taxAccount.Create")
    String createTaxAccount();

    @Key("tax.Create")
    String createTax();


}
