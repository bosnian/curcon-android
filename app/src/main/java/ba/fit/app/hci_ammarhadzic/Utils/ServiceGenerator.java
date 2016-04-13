package ba.fit.app.hci_ammarhadzic.Utils;

import java.io.IOException;

import ba.fit.app.hci_ammarhadzic.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ammar on 4/6/16.
 */
public class ServiceGenerator {
    private static final String TAG = ServiceGenerator.class.getName();
    private static final String API_BASE_URL = "http://apilayer.net/";
    private static final String API_PARAM_NAME = "access_key";
    private static final String API_ACCESS_KEY = BuildConfig.API_ACCESS_KEY;
    
    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    /**
     *  Generate service
     *
     * @param serviceClass  Class for service mapping
     * @param <S> Return instance type
     * @return Service
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createServiceBase(serviceClass, false);
    }

    /**
     *  Generate service with auth
     *
     * @param serviceClass  Class for service mapping
     * @param <S> Return instance type
     * @return Service
     */
    public static <S> S createServiceAuth(Class<S> serviceClass) {
        return createServiceBase(serviceClass, true);
    }

    /**
     * Generate service depending on auth
     *
     * @param serviceClass  Class for service mapping
     * @param auth  Add auth
     * @param <S> Return instance type
     * @return Service
     */
    private static <S> S createServiceBase(Class<S> serviceClass, boolean auth) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (auth) {
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter(API_PARAM_NAME, API_ACCESS_KEY).build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}