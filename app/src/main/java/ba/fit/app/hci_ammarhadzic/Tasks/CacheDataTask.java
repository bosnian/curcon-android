package ba.fit.app.hci_ammarhadzic.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.Managers.StorageManager;
import ba.fit.app.hci_ammarhadzic.Models.Realm.CurrencyDate;
import ba.fit.app.hci_ammarhadzic.Utils.DateHelper;

/**
 * Created by ammar on 4/8/16.
 */
public class CacheDataTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = CacheDataTask.class.getName();
    private StorageManager db = null;

    @Override
    protected Void doInBackground(Void... params) {
        db = new StorageManager();
        prepareData();
        return null;
    }

    void prepareData() {
        GregorianCalendar today = new GregorianCalendar();

        for (int i = 0; i < 7; i++) {
            this.gedDataForDay(today, i);
            today.add(Calendar.DAY_OF_YEAR, -1); // Previous day
        }
    }

    private void gedDataForDay(GregorianCalendar date, int i)  {
        CurrencyDate dataForDay = db.getDataForDay(date);

        if (dataForDay == null) {
            CurrencyDate tmp = new CurrencyDate(Repository.getInstance().getDataForDay(i));
            tmp.key = DateHelper.getKeyForDate(date);
            db.setDataForDay(tmp,date);
            Log.d(TAG, "Data cached fot day "+i);
        } else {
            Log.d(TAG, "Data already exists for day "+i);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(Repository.getInstance().mAppContext,"Data saved for offline use.",Toast.LENGTH_LONG).show();
    }
}
