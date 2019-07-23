package android.rutheford.com.superheroesandvillainscentral.Models;

public class OnBoard
{
    private String title;
    private String desc;
    private int headerImage;


    public OnBoard(String title, String desc, int headerImage)
    {
        this.title = title;
        this.desc = desc;
        this.headerImage = headerImage;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public int getHeaderImage()
    {
        return headerImage;
    }

    public void setHeaderImage(int headerImage)
    {
        this.headerImage = headerImage;
    }
}
