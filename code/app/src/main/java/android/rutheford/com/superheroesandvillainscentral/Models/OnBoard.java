package android.rutheford.com.superheroesandvillainscentral.Models;

/**
 * Created by Nick R.
 */

public class OnBoard
{
    // declarations
    private String mainAttentionTitle;
    private String title;
    private String desc;
    private int headerImage;

    public OnBoard(String mainAttentionTitle, String title, String desc, int headerImage)
    {
        this.mainAttentionTitle = mainAttentionTitle;
        this.title = title;
        this.desc = desc;
        this.headerImage = headerImage;
    }

    public String getMainAttentionTitle()
    {
        return mainAttentionTitle;
    }

    public void setMainAttentionTitle(String mainAttentionTitle)
    {
        this.mainAttentionTitle = mainAttentionTitle;
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
