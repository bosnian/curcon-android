package ba.fit.app.hci_ammarhadzic.Managers;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;

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

    public double baseValue = 1.0;


    private List<CurrencyQuote> lastList = null;
    private int lastID = -1;

    public List<CurrencyQuote> getQuotes(int id){

        if ( lastID == id ){
            return lastList;
        }

        List<CurrencyQuote> tmp = new ArrayList<>();

        for (int i = 0; i < this.getDataForToday().size(); i++) {
            CurrencyQuote t = new CurrencyQuote(this.getDataForToday().get(i));
            tmp.add(t);
        }

        CurrencyQuote x = tmp.get(id);
        tmp.remove(id);

        double usdq = baseValue / x.quote;

        for (int i = 0; i < tmp.size(); i++) {
            double t = tmp.get(i).quote;
            t = t * usdq;
            tmp.get(i).quote = t;
        }

        lastID = id;
        lastList = tmp;
        return tmp;
    }

    public List<List<CurrencyQuote>> data = null;

    public List<CurrencyQuote> getDataForToday(){ return getDataForDay(0);}

    public List<CurrencyQuote> getDataForDay(int i){ return data.get(i); }

    public ArrayList<Entry> getCurrencyForWeek(int id){
        ArrayList<Entry> data = new ArrayList<>();

        for (int i = 0; i < this.data.size(); i++) {
            data.add(new Entry((float) this.data.get(i).get(id).quote,i));
        }

        return data;
    }

}
