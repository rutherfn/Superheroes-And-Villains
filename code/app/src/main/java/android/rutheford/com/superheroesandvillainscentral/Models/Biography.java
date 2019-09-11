package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Biography
{
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("alterEgos")
    private String alterEgos;
    @SerializedName("aliases")
    private List<String> aliases;
    @SerializedName("placeOfBirth")
    private String placeOfBirth;
    @SerializedName("firstAppearance")
    private String firstApperance;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("alignment")
    private String alignment;

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
