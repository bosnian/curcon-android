package ba.fit.app.hci_ammarhadzic.Models.API;

import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyRateList;
import ba.fit.app.hci_ammarhadzic.Models.Network.CurrencyTypeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ammar on 4/5/16.
 */
public interface CurrencyAPI {

    // FIXME: Remove key from url to builder
    @GET("api/list")
    Call<CurrencyTypeList> getAllCurrencies();

    @GET("api/live")
    Call<CurrencyRateList> getLiveQuotes();

    @GET("api/historical")
    Call<CurrencyRateList> getQuotesForDate(@Query("date") String date);
}
