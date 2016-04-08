package ba.fit.app.hci_ammarhadzic.Models.Realm;

import java.util.List;

import ba.fit.app.hci_ammarhadzic.Models.CurrencyQuote;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by ammar on 4/6/16.
 */
public class CurrencyDate extends RealmObject {

    @Required
    public String key;
    public RealmList<CurrencyQuote> quotes;

    public CurrencyDate(){ }

    public CurrencyDate(List<CurrencyQuote> data){
        quotes = new RealmList<>();

        for (int i = 0; i < data.size(); i++) {
            quotes.add(new CurrencyQuote(data.get(i)));
        }
    }

}
