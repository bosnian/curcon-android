package ba.fit.app.hci_ammarhadzic.Managers;

import android.util.Log;

import java.io.IOException;
import java.util.GregorianCalendar;

import ba.fit.app.hci_ammarhadzic.Models.Interface.CurrencyAPI;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyRateList;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyTypeList;
import ba.fit.app.hci_ammarhadzic.Utils.DateHelper;
import ba.fit.app.hci_ammarhadzic.Utils.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by ammar on 4/6/16.
 */
public class NetworkManager {
    private static final String TAG = NetworkManager.class.getName();
    private CurrencyAPI currencyAPI;

    public NetworkManager(){

        currencyAPI = ServiceGenerator.createServiceAuth(CurrencyAPI.class);
    }

    void getAllData() {

        Call<CurrencyTypeList> call = currencyAPI.getAllCurrencies();
        CurrencyTypeList currencyTypeList = this.execute(call);
    }

    public CurrencyRateList getDataForToday() {
        Call<CurrencyRateList> call = currencyAPI.getLiveQuotes();
        return this.execute(call);
    }

    public CurrencyRateList getDataForDate(GregorianCalendar date) {
        // today
        if ( DateHelper.getKeyForDate(date).equals(DateHelper.getKeyForDate(new GregorianCalendar())))
            return getDataForToday();

        Log.d(TAG,"Getting data for day" + DateHelper.getKeyForDate(date));
        Call<CurrencyRateList> call = currencyAPI.getQuotesForDate(DateHelper.getKeyForDate(date));
        return this.execute(call);
    }

    public CurrencyTypeList getCurrencyTypes() throws IOException{

        Call<CurrencyTypeList> call = currencyAPI.getAllCurrencies();
        CurrencyTypeList currencyTypeList = null;
        currencyTypeList = call.execute().body();

        return currencyTypeList;
    }

    private <S>S execute(Call<S> call){
        S result = null;

        try {
           result = call.execute().body();
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
        }
        return  result;
    }

//    public void test(){
//        GregorianCalendar t= new GregorianCalendar();
//        t.add(Calendar.DAY_OF_YEAR,-1);
//        Call<CurrencyRateList> call = currencyAPI.getQuotesForDate(DateHelper.getKeyForDate(t));
//        call.enqueue(new Callback<CurrencyRateList>() {
//            @Override
//            public void onResponse(Call<CurrencyRateList> call, Response<CurrencyRateList> response) {
//
//                Log.d(TAG, call.request().url().toString());
//                Log.d(TAG, response.body().privacy);
//
//            }
//
//            @Override
//            public void onFailure(Call<CurrencyRateList> call, Throwable t) {
//                Log.d(TAG,call.request().url().toString());
//            }
//        });
//    }
}
