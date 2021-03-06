package ba.fit.app.hci_ammarhadzic.Models;

import io.realm.RealmObject;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyQuote extends RealmObject {
    public int ID;
    public String code;
    public String name;
    public double quote; // Based on USD

    public CurrencyQuote(){

    }
    public CurrencyQuote(CurrencyQuote t){
        code = t.code;
        name = t.name;
        quote = t.quote;
    }
}
