package ba.fit.app.hci_ammarhadzic.Models.Interface;

import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyRateList;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyTypeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ammar on 4/5/16.
 */
public interface CurrencyAPI {

    @GET("api/list")
    Call<CurrencyTypeList> getAllCurrencies();

    @GET("api/live?access_key=f03dec294859f01224625812f7b963e5")
    Call<CurrencyRateList> getAllQuotes();

    @GET("api/live")
    Call<CurrencyRateList> getLiveQuotes();

    @GET("api/historical?access_key=f03dec294859f01224625812f7b963e5")
    Call<CurrencyRateList> getQuotesForDate(@Query("date") String date);
}
