package android.rutheford.com.superheroesandvillainscentral.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NickR.
 */
public class RetroFitInstance
{
    // declarations
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) { // creating a instance of retro fit, in order to make calls to API.
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
