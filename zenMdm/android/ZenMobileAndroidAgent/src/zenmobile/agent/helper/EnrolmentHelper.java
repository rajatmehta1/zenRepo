package zenmobile.agent.helper;



import java.util.ArrayList;

import java.util.List;



import org.apache.http.NameValuePair;

import org.apache.http.message.BasicNameValuePair;



import android.content.Context;

import android.content.pm.PackageManager;



import zenmobile.agent.util.Constants;

import zenmobile.agent.vo.AppVo;

import zenmobile.agent.vo.DeviceInfoVO;



public class EnrolmentHelper {

   

    public boolean enrolDevice(DeviceInfoVO deviceVO) {

        ServerConnectionHelper sconn = new ServerConnectionHelper();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("foo", "12345"));

         sconn.postDataToServer("http://rajatxp2.msad.ms.com:8080/jsp-examples/jsp2/el/functions.jsp", nameValuePairs);

         return true;

    }

    

    

    public static String buildEnrolmentInfo(Context ctx,PackageManager pkgMgr,boolean getSysPackages) {

        String deviceString = fetchEnrolmentInfoAsXMLString(ctx);

        return deviceString;

    }

    

    public static String fetchEnrolmentInfoAsXMLString(Context ctx) {

        DeviceInfoVO dvo = DeviceInfoHelper.fetchDeviceInfo(ctx);

        String xmlString = "<enrollment>";

               xmlString = xmlString + "<name>" + dvo.getDeviceName() + "</name>";

               xmlString = xmlString + "<os>" + dvo.getOs() + "</os>";

               xmlString = xmlString + "<phoneNum>" + dvo.getPhoneNumber() + "</phoneNum>";               

               xmlString = xmlString + "<model>" + dvo.getModel() + "</model>";

               xmlString = xmlString + "<type>" + dvo.getDeviceType() + "</type>";               

               xmlString = xmlString + "<os_version>" + dvo.getOs() + "</os_version>";

               xmlString = xmlString + "<user>" + dvo.getUserName() + "</user>";

               xmlString = xmlString + "<user_id>" + Constants.DEFAULT_MDM_USER_ID + "</user_id>";

               xmlString = xmlString + "</enrollment>";

               

             return xmlString;  

     }

    



}
