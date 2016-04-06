package ba.fit.app.hci_ammarhadzic.Managers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ba.fit.app.hci_ammarhadzic.Models.CurrencyQuote;

/**
 * Created by ammar on 4/5/16.
 */
public class Repository {
    private static final String TAG = Repository.class.getName();
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
    }
    public Context mAppContext = null;

    public double USDQuote = 0;
    public int USDPosition = 0;
    public double baseValue = 1.0;


    private List<CurrencyQuote> lastList = null;
    private int lastID = -1;

    public List<CurrencyQuote> getQuotes(int id){

        if ( lastID == id ){
            return lastList;
        }

        List<CurrencyQuote> tmp = new ArrayList<>();

        tmp.addAll(this.getDataForToday());
        CurrencyQuote x = tmp.get(id);
        tmp.remove(id);

        double usdq = baseValue / x.quote;
        Log.d(TAG,"USD quote " + String.valueOf(baseValue) + " / " + x.code + String.valueOf(x.quote) + " = "+String.valueOf(usdq));
        for (int i = 0; i < tmp.size(); i++) {
            double t = tmp.get(i).quote;
            t = t * usdq;
            tmp.get(i).quote = t;
            if (tmp.get(i).code.equals("EUR")) {
                Log.d(TAG,"EUD quote " + tmp.get(i).quote * usdq);

            }
        }

        lastID = id;
        lastList = tmp;
        return tmp;
    }

    public List<List<CurrencyQuote>> data = null;


    public List<CurrencyQuote> getDataForToday(){
        return getDataForDay(0);
    }

    public List<CurrencyQuote> getDataForDay(int i){
        return data.get(i);
    }
}
