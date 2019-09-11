package android.rutheford.com.superheroesandvillainscentral.Network;

import android.rutheford.com.superheroesandvillainscentral.Models.Appearance;
import android.rutheford.com.superheroesandvillainscentral.Models.Biography;
import android.rutheford.com.superheroesandvillainscentral.Models.Connections;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Models.Image;
import android.rutheford.com.superheroesandvillainscentral.Models.PowerStats;
import android.rutheford.com.superheroesandvillainscentral.Models.Results;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
import android.rutheford.com.superheroesandvillainscentral.Models.Work;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDataService
{
    @GET
    Call<PowerStats> getPowerStats(@Url String url);
    @GET
    Call<Biography> getBiography(@Url String url);
    @GET
    Call<Appearance> getApperance(@Url String url);
    @GET
    Call<Work> getWork(@Url String url);
    @GET
    Call<Connections> getConnections(@Url String url);
    @GET
    Call<Image> getImage (@Url String url);

    @GET
    Call<Id> getById (@Url String url);

    @GET
    Call<Results> getByResults(@Url String url);

    @GET
    Call<SearchName> getSearchByName(@Url String url);

}
