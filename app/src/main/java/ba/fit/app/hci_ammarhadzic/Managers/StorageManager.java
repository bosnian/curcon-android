package ba.fit.app.hci_ammarhadzic.Managers;

import android.content.Context;

import java.util.GregorianCalendar;

import ba.fit.app.hci_ammarhadzic.Models.Realm.CurrencyDate;
import ba.fit.app.hci_ammarhadzic.Utils.DateHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ammar on 4/6/16.
 */
public class StorageManager {
    private Realm realm;
    public StorageManager(Context context){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        realm = Realm.getInstance(realmConfig);
    }

    /**
     * Get data for given day
     *
     * @param date for database search
     * @return CurrencyDate
     */
    public CurrencyDate getDataForDay(GregorianCalendar date){

        String key = DateHelper.getKeyForDate(date);
        return  realm.where(CurrencyDate.class).equalTo("key",key).findFirst();
    }

    /**
     * Save data for given day
     *
     * @param data  Data to save in database
     * @param date  Date for given data
     */
    public void saveDataForDay(CurrencyDate data, GregorianCalendar date){

        data.key = DateHelper.getKeyForDate(date);
        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();
    }
}
