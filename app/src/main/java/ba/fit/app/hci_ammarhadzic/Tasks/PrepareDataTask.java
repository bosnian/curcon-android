package ba.fit.app.hci_ammarhadzic.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ba.fit.app.hci_ammarhadzic.Managers.NetworkManager;
import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.Managers.StorageManager;
import ba.fit.app.hci_ammarhadzic.Models.CurrencyQuote;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyRateList;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyTypeList;
import ba.fit.app.hci_ammarhadzic.Models.Realm.CurrencyDate;

public class PrepareDataTask extends AsyncTask<Void, Void, List<List<CurrencyQuote>>> {
    private static final String TAG = PrepareDataTask.class.getName();
    public PrepareDataTaskInterface delegate = null;
    public IOException exception = null;

    private NetworkManager n = new NetworkManager();
    private StorageManager db = null;
    private CurrencyTypeList currencyTypes = null;
    private List<List<CurrencyQuote>> data = new ArrayList<>();

    @Override
    protected List<List<CurrencyQuote>> doInBackground(Void... params) {
        db = new StorageManager();
        try {
            prepareData();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(List<List<CurrencyQuote>> data) {
        if (data.size() != 7)
            delegate.prepareDataFinished(false);
        else {
            Repository.getInstance().data = data;
            delegate.prepareDataFinished(true);
        }

    }

    private void prepareData() throws Throwable {
        GregorianCalendar today = new GregorianCalendar();

        for (int i = 0; i < 7; i++) {
            Log.d(TAG, "Getting data for day " + i);
            List<CurrencyQuote> currencyQuotes = this.gedDataForDay(today);
            if (currencyQuotes == null){
                return;
            }
            data.add(currencyQuotes);
            today.add(Calendar.DAY_OF_YEAR,-1); // Previous day
        }
    }

    private List<CurrencyQuote> gedDataForDay(GregorianCalendar date) throws Throwable {
        CurrencyDate dataForDay = db.getDataForDay(date);

        List<CurrencyQuote> today = null;

        if (dataForDay == null) {

            currencyTypes = n.getCurrencyTypes();
            CurrencyRateList dataForDate = n.getDataForDate(date);
            if( dataForDate == null ){
                return null;
            }
            today = this.setNameForCode(dataForDate.getList());

            Log.d(TAG, "Got data from API");
        } else {
            Log.d(TAG,"Got data from DB");

            //Copy data
            today = new ArrayList<>();
            for (int i = 0; i < dataForDay.quotes.size(); i++) {
                CurrencyQuote currencyQuote = new CurrencyQuote();
                currencyQuote.name = dataForDay.quotes.get(i).name;
                currencyQuote.code = dataForDay.quotes.get(i).code;
                currencyQuote.quote = dataForDay.quotes.get(i).quote;
                today.add(currencyQuote);
            }
        }
        return today;
    }

    private List<CurrencyQuote> setNameForCode(List<CurrencyQuote> list) {

        if (currencyTypes != null)
            for (int i = 0; i < list.size(); i++)
                list.get(i).name = currencyTypes.currencies.get(list.get(i).code);

        return list;
    }

}