package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Biography
{
    @SerializedName("response")
    private String response;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("full-name")
    private String fullName;
    @SerializedName("alter-egos")
    private String alterEgos;
    @SerializedName("aliases")
    private List<String> aliases;
    @SerializedName("place-of-birth")
    private String placeOfBirth;
    @SerializedName("first-apperance")
    private String firstApperance;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("alignment")
    private String alignment;

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

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getAlterEgos()
    {
        return alterEgos;
    }

    public void setAlterEgos(String alterEgos)
    {
        this.alterEgos = alterEgos;
    }

    public List<String> getAliases()
    {
        return aliases;
    }

    public void setAliases(List<String> aliases)
    {
        this.aliases = aliases;
    }

    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstApperance()
    {
        return firstApperance;
    }

    public void setFirstApperance(String firstApperance)
    {
        this.firstApperance = firstApperance;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getAlignment()
    {
        return alignment;
    }

    public void setAlignment(String alignment)
    {
        this.alignment = alignment;
    }
}
