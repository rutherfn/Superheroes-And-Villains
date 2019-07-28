package android.rutheford.com.superheroesandvillainscentral.Models.Settings;

public class SettingModel
{
    private String title;
    private String desc;
    private String imageSetting;

    public SettingModel(String title, String desc, String imageSetting)
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

    public String getImageSetting()
    {
        return imageSetting;
    }

    public void setImageSetting(String imageSetting)
    {
        this.imageSetting = imageSetting;
    }
}
