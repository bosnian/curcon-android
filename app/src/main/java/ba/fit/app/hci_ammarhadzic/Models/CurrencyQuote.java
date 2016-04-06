package ba.fit.app.hci_ammarhadzic.Models;

import io.realm.RealmObject;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyQuote extends RealmObject {
    public String code;
    public String name;
    public double quote; // Based on USD
}
