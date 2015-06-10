package zenmobile.agent.helper;



import java.lang.reflect.Field;

import java.util.List;



import android.content.Context;

import android.content.pm.PackageManager;

import android.os.Build;

import android.telephony.TelephonyManager;

import zenmobile.agent.util.Constants;

import zenmobile.agent.vo.AppVo;

import zenmobile.agent.vo.DeviceInfoVO;



//fetch info for the device

public class DeviceInfoHelper {

   

    public static DeviceInfoVO fetchDeviceInfo() {

        DeviceInfoVO deviceInfo = new DeviceInfoVO();

             deviceInfo.setDeviceName(Build.PRODUCT);

             deviceInfo.setOs("ANDORID");

             deviceInfo.setModel(Build.MODEL);

             deviceInfo.setDeviceName(Build.DEVICE);

             deviceInfo.setSdk(Build.VERSION.SDK);

             deviceInfo.setUserName(Build.USER);

             return deviceInfo;

    }

    

    public static DeviceInfoVO fetchDeviceInfo(Context appContext) {

        DeviceInfoVO deviceInfo = new DeviceInfoVO();

             deviceInfo.setDeviceName(Build.PRODUCT);

             deviceInfo.setOs("ANDORID");

             deviceInfo.setModel(Build.MODEL);

             deviceInfo.setDeviceName(Build.DEVICE);

             deviceInfo.setSdk(Build.VERSION.SDK);

             deviceInfo.setUserName(Build.USER);

             deviceInfo.setManufacturer(Build.MANUFACTURER);

             

        TelephonyManager telemamanger = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);

             deviceInfo.setPhoneNumber(telemamanger.getLine1Number());

             deviceInfo.setNetwork(telemamanger.getNetworkOperator());

             deviceInfo.setSimNumber(telemamanger.getSimSerialNumber());

             deviceInfo.setCarrier(telemamanger.getNetworkOperatorName());

                     



             return deviceInfo;

    }

    



    

    public static String getOSVersion() {

        StringBuilder builder = new StringBuilder();

        builder.append("android : ").append(Build.VERSION.RELEASE);



        Field[] fields = Build.VERSION_CODES.class.getFields();

        for (Field field : fields) {

            String fieldName = field.getName();

            int fieldValue = -1;



            try {

                fieldValue = field.getInt(new Object());

            } catch (IllegalArgumentException e) {

                e.printStackTrace();

            } catch (IllegalAccessException e) {

                e.printStackTrace();

            } catch (NullPointerException e) {

                e.printStackTrace();

            }



            if (fieldValue == Build.VERSION.SDK_INT) {

                builder.append(" : ").append(fieldName).append(" : ");

                builder.append("sdk=").append(fieldValue);

            }

        }

        

        return builder.toString();

    }

    

    public static String fetchDeviceInfoAsXMLString(Context ctx) {

       DeviceInfoVO dvo = fetchDeviceInfo(ctx);

       String xmlString = "<device>";

              xmlString = xmlString + "<name>" + dvo.getDeviceName() + "</name>";

              xmlString = xmlString + "<os>" + dvo.getOs() + "</os>";

              xmlString = xmlString + "<model>" + dvo.getModel() + "</model>";

              xmlString = xmlString + "<carrier>" + dvo.getCarrier() + "</carrier>";

              xmlString = xmlString + "<user>" + dvo.getUserName() + "</user>";

              xmlString = xmlString + "<user_id>" + Constants.DEFAULT_MDM_USER_ID + "</user_id>";

              xmlString = xmlString + "<device_type>" + dvo.getDeviceType() + "</device_type>";

              xmlString = xmlString + "<manufacturer>" + dvo.getManufacturer() + "</manufacturer>";

              xmlString = xmlString + "<network>" + dvo.getNetwork() + "</network>";

              xmlString = xmlString + "<phoneNum>" + dvo.getPhoneNumber() + "</phoneNum>";

              xmlString = xmlString + "<sdk>" + dvo.getSdk() + "</sdk>";

              xmlString = xmlString + "<simNumber>" + dvo.getSimNumber() + "</simNumber>";

              xmlString = xmlString + "<status>" + "Y" + "</status>";

              xmlString = xmlString + "</device>";

              

            return xmlString;  

    }


    

    public static String buildFullDeviceInfo(Context ctx,PackageManager pkgMgr,boolean getSysPackages) {

        List<AppVo> installedApps = AppHelper.fetchInstalledAppsViaPkgMgr(pkgMgr, getSysPackages);

        String appString = AppHelper.fetchInstalledAppInfoAsXML(installedApps);

        String deviceString = fetchDeviceInfoAsXMLString(ctx);

        String devInfoStr = "<device_info>";

               devInfoStr = devInfoStr +  deviceString + appString + "</device_info>";

        return devInfoStr;

    }

    

    

}