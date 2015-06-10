package zenmobile.agent.vo;
 
import android.graphics.drawable.Drawable;
 
public class AppVo {
   private static String TAG = "APP INFO";
   private String appName;
   private String appStatus;
   private String appType;   
   private String pname;
   private String versionName;
   private int versionCode = 0;
   private Drawable icon;
   private boolean selected;
   private String category;
   
    public AppVo() {
       
    }
 
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getAppStatus() {
        return appStatus;
    }
    
    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
    
    public String getAppType() {
        return appType;
    }
    
    public void setAppType(String appType) {
        this.appType = appType;
    }
 
 
    public String getPname() {
        return pname;
    }
 
    public void setPname(String pname) {
        this.pname = pname;
    }
 
    public String getVersionName() {
        return versionName;
    }
 
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
 
    public int getVersionCode() {
        return versionCode;
    }
 
    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
 
    public Drawable getIcon() {
        return icon;
    }
 
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
 
    public boolean isSelected() {
        return selected;
    }
 
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
   
}