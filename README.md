# zenRepo
Repository of my open source projects

The following projects are currently checked in

1. <strong>ZenMdm</strong> - . 
   This is a Mobile Device Management opensource project (catering to BYOD). The first version of this project only deals with handling Android devices.
   Aim is to provide a solution where a device health can be continuously monitored and the user gets exclusive remote excess to monitor the 
   health of his android device.
   
   This project has 3 sub projects
     a) Android - This is the android app that needs to be installed on the device. This would internally keep on sending the health data 
        to the backend service. It also provides a UI to show general health (battery life) etc to the users.
        
      b) Core project - handles the core middleware code. This part can be deployed on the cloud also AWS etc. Currently the datastore
         used is mysql but the DAO's can be configured to the talk to an Amazon SimpleDB etc instance.
         
      c) UI app - this is the desktop version of hte app. This has slightly old code on Adobe flex. If time permits i will por the code 
         to angularjs.
        
        Currently the code is very much in progress. I work on it as and when i get the time :). 

2. <Strong>Heart Disease Detection using Machine Learning and Big Data</Strong>
   This is the source code of the project for heart disease detection on the UCI Heart disease dataset (cleveland one), via machine learning and big data usage. More details can be found here http://indigineering.blogspot.com/2016/07/heart-disease-detection-using-big-data_24.html.
  
