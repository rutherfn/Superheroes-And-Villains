package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

public class Work
{
    @SerializedName("occupation")
    private String occupation;
    @SerializedName("base")
    private String base;

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getBase()
    {
        return base;
    }

    public void setBase(String base)
    {
        this.base = base;
    }
}
