package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchName
{
    @SerializedName("response")
    private String response;
    @SerializedName("results-for")
    private String resultsFor;
    @SerializedName("results")
    private ArrayList<Results> results;

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getResultsFor()
    {
        return resultsFor;
    }

    public void setResultsFor(String resultsFor)
    {
        this.resultsFor = resultsFor;
    }

    public ArrayList<Results> getResults()
    {
        return results;
    }

    public void setResults(ArrayList<Results> results)
    {
        this.results = results;
    }
}
