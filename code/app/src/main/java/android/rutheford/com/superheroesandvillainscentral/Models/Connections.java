package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

public class Connections
{
    @SerializedName("response")
    private String response;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("group-affiliation")
    private String groupAffiliation;
    @SerializedName("relatives")
    private String relatives;

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

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
