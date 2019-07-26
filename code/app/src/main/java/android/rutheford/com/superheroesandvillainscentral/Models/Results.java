package android.rutheford.com.superheroesandvillainscentral.Models;

import com.google.gson.annotations.SerializedName;

public class Results
{
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("powerstats")
    private PowerStats powerStats;
    @SerializedName("biography")
    private Biography biography;
    @SerializedName("appearance")
    private Appearance appearance;
    @SerializedName("work")
    private Work work;
    @SerializedName("connections")
    private Connections connections;
    @SerializedName("image")
    private Image image;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public PowerStats getPowerStats()
    {
        return powerStats;
    }

    public void setPowerStats(PowerStats powerStats)
    {
        this.powerStats = powerStats;
    }

    public Biography getBiography()
    {
        return biography;
    }

    public void setBiography(Biography biography)
    {
        this.biography = biography;
    }

    public Appearance getAppearance()
    {
        return appearance;
    }

    public void setAppearance(Appearance appearance)
    {
        this.appearance = appearance;
    }

    public Work getWork()
    {
        return work;
    }

    public void setWork(Work work)
    {
        this.work = work;
    }

    public Connections getConnections()
    {
        return connections;
    }

    public void setConnections(Connections connections)
    {
        this.connections = connections;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }
}
