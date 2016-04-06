package ba.fit.app.hci_ammarhadzic.Models.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ammar on 4/4/16.
 */
public class CurrencyTypeList {
    public boolean success;
    public String terms;
    public String privacy;
    public Map<String, String> currencies;

    public List<CurrencyType> getList() {
        List<CurrencyType> tmp = new ArrayList<CurrencyType>();

        for (String key : currencies.keySet()) {
            CurrencyType t = new CurrencyType();
            t.code = key;
            t.name = currencies.get(key);
            tmp.add(t);
        }
        return tmp;
    }
}