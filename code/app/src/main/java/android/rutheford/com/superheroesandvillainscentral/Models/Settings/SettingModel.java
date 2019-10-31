package android.rutheford.com.superheroesandvillainscentral.Models.Settings;

/**
 * Created by Nick R.
 */

public class SettingModel
{
    // declarations
    private String title;
    private String desc;
    private int imageSetting;

    public SettingModel(String title, String desc, int imageSetting)
    {
        this.title = title;
        this.desc = desc;
        this.imageSetting = imageSetting;
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

    public int getImageSetting()
    {
        return imageSetting;
    }

    public void setImageSetting(int imageSetting)
    {
        this.imageSetting = imageSetting;
    }
}
