package ba.fit.app.hci_ammarhadzic.Models.Realm;

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

}
