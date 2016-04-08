package ba.fit.app.hci_ammarhadzic.Models.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ba.fit.app.hci_ammarhadzic.Models.CurrencyQuote;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyRateList {

    public Boolean success;
    public String terms;
    public String privacy;
    public long timestamp;
    public String source;
    private Map<String,Double> quotes;

    public List<CurrencyQuote> getList(){
        List<CurrencyQuote> tmp = new ArrayList<>();

        for (String key : quotes.keySet()) {
            CurrencyQuote t = new CurrencyQuote();
            t.code = key.substring(3);
            t.quote = quotes.get(key);
            tmp.add(t);
        }
        return tmp;
    }
}
