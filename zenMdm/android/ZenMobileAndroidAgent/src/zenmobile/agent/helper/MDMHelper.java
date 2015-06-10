package zenmobile.agent.helper;



import android.content.Context;

import android.content.pm.PackageManager;



/*

* Class for sending info data to the server

*/

public class MDMHelper {

   static final String TAG = "ServerConnectionHelper";

    

   public void postMDMInfoToServer(Context ctx) {

       String xmlDeviceInfo = DeviceInfoHelper.fetchDeviceInfoAsXMLString(ctx);

       ServerConnectionHelper sconn = new ServerConnectionHelper();

           sconn.postXmlToServer("http://rajatxp2.msad.ms.com:8080/ZenMobileCore/mdm?test=abcd", xmlDeviceInfo);

   }

    

//   public void postDeviceInfoToServer(PackageManager pkgMgr,boolean getSysPackages) {

//       String xmlDeviceInfo = DeviceInfoHelper.buildFullDeviceInfo(pkgMgr, getSysPackages);

//       ServerConnectionHelper sconn = new ServerConnectionHelper();

//           sconn.postXmlToServer(xmlDeviceInfo);

//   }    

   

   public void postDeviceInfoToServer(Context ctx,PackageManager pkgMgr,boolean getSysPackages) {

       String xmlDeviceInfo = DeviceInfoHelper.buildFullDeviceInfo(ctx,pkgMgr, getSysPackages);

       ServerConnectionHelper sconn = new ServerConnectionHelper();

           sconn.postXmlToServer(xmlDeviceInfo);

   }

   

   

   public void enrolDeviceToServer(Context ctx,PackageManager pkgMgr,boolean getSysPackages) {

       String xmlDeviceInfo = EnrolmentHelper.buildEnrolmentInfo(ctx, pkgMgr, getSysPackages);

       ServerConnectionHelper sconn = new ServerConnectionHelper();

           sconn.postXmlToServer(xmlDeviceInfo);

   }

   

   public void enrolDeviceAndPostDeviceInfoToServer(Context ctx,PackageManager pkgMgr,boolean getSysPackages) {

       String xmlEnrolInfo = EnrolmentHelper.buildEnrolmentInfo(ctx, pkgMgr, getSysPackages);

       String xmlDeviceInfo = DeviceInfoHelper.buildFullDeviceInfo(ctx,pkgMgr, getSysPackages);

       String output = xmlEnrolInfo + "#####" + xmlDeviceInfo;

       ServerConnectionHelper sconn = new ServerConnectionHelper();

           sconn.postXmlToServer(output);

   }

   

   

}
