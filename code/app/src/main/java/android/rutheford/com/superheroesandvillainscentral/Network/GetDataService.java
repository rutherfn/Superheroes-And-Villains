package android.rutheford.com.superheroesandvillainscentral.Network;

import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Models.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by NickR.
 */

public interface GetDataService
{
    // get calls, in order to take in a url for my retro fit calls.
    @GET
    Call<Id> getById (@Url String url);

    @GET
    Call<Results> getByResults(@Url String url);
}
