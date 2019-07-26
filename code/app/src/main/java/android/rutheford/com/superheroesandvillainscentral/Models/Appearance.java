package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appearance
{
    @SerializedName("response")
    private String response;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("race")
    private String race;
    @SerializedName("height")
    private List<String> height;
    @SerializedName("weight")
    private List<String> weight;
    @SerializedName("eye-color")
    private String eyeColor;
    @SerializedName("hair-color")
    private String hairColor;

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

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getRace()
    {
        return race;
    }

    public void setRace(String race)
    {
        this.race = race;
    }

    public List<String> getHeight()
    {
        return height;
    }

    public void setHeight(List<String> height)
    {
        this.height = height;
    }

    public List<String> getWeight()
    {
        return weight;
    }

    public void setWeight(List<String> weight)
    {
        this.weight = weight;
    }

    public String getEyeColor()
    {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    public String getHairColor()
    {
        return hairColor;
    }

    public void setHairColor(String hairColor)
    {
        this.hairColor = hairColor;
    }
}
