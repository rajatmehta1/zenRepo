package zenmobile.agent.helper;



import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;

import java.util.Set;



import zenmobile.agent.vo.AppVo;



import android.content.pm.ApplicationInfo;

import android.content.pm.PackageInfo;

import android.content.pm.PackageManager;



//Pulls the Installed Application at Startup

public class AppHelper {



    //via application ifno

    public static List<AppVo> fetchInstalledApps(PackageManager pkgMgr,boolean getSysPackages) {

        List<AppVo> appsList = new ArrayList<AppVo>();       

        AppVo appVoObj = null;

        List<ApplicationInfo> apps = pkgMgr.getInstalledApplications(0);

        for(int i=0;i<apps.size();i++) {

            ApplicationInfo a = apps.get(i);            

            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1)

            {

                //This is System application

            }

            else

            {

                appVoObj = new AppVo();

                appVoObj.setPname(a.packageName);

                appVoObj.setAppName(a.loadLabel(pkgMgr).toString());

                appVoObj.setIcon(a.loadIcon(pkgMgr));

                appsList.add(appVoObj);

            }



        }

        return appsList; 

    }

    

    public static List<AppVo> fetchInstalledAppsViaPkgMgr(PackageManager pkgMgr,boolean getSysPackages) {

        List<AppVo> appsList = new ArrayList<AppVo>();       

        AppVo appVoObj = null;

        List<PackageInfo> pkgs = pkgMgr.getInstalledPackages(0);

        for(int i=0;i<pkgs.size();i++) {

            PackageInfo pinfo = pkgs.get(i);            

            if ((pinfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)

            {

                //This is System application

            }

            else

            {

                appVoObj = new AppVo();

                appVoObj.setPname(pinfo.applicationInfo.packageName);

                appVoObj.setAppName(pinfo.applicationInfo.loadLabel(pkgMgr).toString());

                appVoObj.setIcon(pinfo.applicationInfo.loadIcon(pkgMgr));

                appVoObj.setVersionName(pinfo.versionName);

                appVoObj.setVersionCode(pinfo.versionCode);

                appVoObj.setAppStatus(new Boolean(pinfo.applicationInfo.enabled).toString());

                CharSequence description = pinfo.applicationInfo.loadDescription(pkgMgr); //error here?

                appVoObj.setDescription(description != null ? description.toString() : "");

                appsList.add(appVoObj);

            }



        }

        return appsList; 

    }



    public static CharSequence[] fetchInstalledAppsCharSeq(PackageManager pkgMgr,boolean getSysPackages) {

        List<CharSequence> charList = new ArrayList<CharSequence>();       

        AppVo AppVo = null;

        List<ApplicationInfo> apps = pkgMgr.getInstalledApplications(0);

        for(int i=0;i<apps.size();i++) {

            ApplicationInfo a = apps.get(i);            

            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1)

            {

                //This is System application

            }

            else

            {

                charList.add(a.loadLabel(pkgMgr).toString());

            }



        }

        return (CharSequence[]) charList.toArray(new CharSequence[charList.size()]); 

    }    

    

    //Considering each AppName as Unique returning a Set of AppNames

    public static Set<String> fetchInstalledAppsAsSet(PackageManager pkgMgr,boolean getSysPackages) {

        Set<String> appNames = new HashSet<String>();

        AppVo appVo = null;

        List<ApplicationInfo> apps = pkgMgr.getInstalledApplications(0);

        for(int i=0;i<apps.size();i++) {

            ApplicationInfo a = apps.get(i);            

            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1)

            {

                //This is System application

            }

            else

            {

                appNames.add(a.loadLabel(pkgMgr).toString());

            }



        }

        return appNames; 

    }



    public static String fetchInstalledAppInfoAsXML(List<AppVo> prmAppList) {

        String xmlString = "<apps>";

        for (AppVo appVo : prmAppList) {

            xmlString += "<app>";

            xmlString = xmlString +  "<name>" + appVo.getAppName() + "</name>";

            xmlString = xmlString +  "<status>" + appVo.getAppStatus() + "</status>";

            xmlString = xmlString +  "<description>" + appVo.getDescription() + "</description>";

            xmlString = xmlString +  "<version_name>" + appVo.getVersionName() + "</version_name>";

            xmlString = xmlString +  "<version_code>" + appVo.getVersionCode() + "</version_code>";

            xmlString = xmlString +  "<pkg>" + appVo.getPname() + "</pkg>";

            xmlString += "</app>";

        }

        xmlString = xmlString + "</apps>";

        return xmlString;

    }

    

    

}
