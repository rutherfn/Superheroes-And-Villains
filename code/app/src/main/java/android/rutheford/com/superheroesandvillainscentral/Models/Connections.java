package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick R.
 */

public class Connections
{
    // declarations
    @SerializedName("groupAffiliation")
    private String groupAffiliation;
    @SerializedName("relatives")
    private String relatives;


    public String getGroupAffiliation()
    {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation)
    {
        this.groupAffiliation = groupAffiliation;
    }

    public String getRelatives()
    {
        return relatives;
    }

    public void setRelatives(String relatives)
    {
        this.relatives = relatives;
    }
}
