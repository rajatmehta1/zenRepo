package zenmobile.agent.activity;



import java.util.Date;



import zenmobile.agent.dao.MainDAO;

import zenmobile.agent.helper.EnrolmentHelper;

import zenmobile.agent.helper.MDMHelper;

import zenmobile.agent.helper.ServerConnectionHelper;

import zenmobile.agent.receiver.ZMDeviceAdminReceiver;

import zenmobile.agent.util.Constants;

import zenmobile.agent.vo.DeviceInfoVO;

import zenmobile.agent.vo.GeneralInfoVO;

import android.app.Activity;

import android.app.ListActivity;

import android.app.admin.DevicePolicyManager;

import android.content.ComponentName;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;

import android.widget.CompoundButton;

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;


public class MainActivity extends ListActivity implements OnItemClickListener {

    static final String TAG = "MainActivity";

    static final int ACTIVATION_REQUEST = 47; // identifies our request id

    ComponentName demoDeviceAdmin;

    

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        ServerConnectionHelper connHelper = new ServerConnectionHelper();



            checkAndUpdateGeneralInfo();

        

//        if(connHelper.isDeviceEnrolled()) { //checked through server

          if(this.isDeviceEnrolled()) { //checked through local client           

                String[] zen_options = getResources().getStringArray(R.array.zenmobile_options);

                    this.setListAdapter(new ArrayAdapter<String>(this, R.layout.zen_option_item, R.id.label, zen_options));

                ListView lv = getListView();

                    lv.setOnItemClickListener(this);

        } else {

            //call device admin

            demoDeviceAdmin = new ComponentName(this, ZMDeviceAdminReceiver.class);

            enableAdmin();

        }

    }



    @Override

    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

        String viewName = ((TextView) view).getText().toString();

        if("General Info".equals(viewName)) {

            Intent i = new Intent(getApplicationContext(), GeneralInfoActivity.class);

                startActivity(i);

        }

        else if("Device Info".equals(viewName)) {

            Intent i = new Intent(getApplicationContext(), DeviceInfoActivity.class);

                startActivity(i);

        }

        else if("Unit Testing".equals(viewName)) {

            Intent i = new Intent(getApplicationContext(), TestActivity.class);

                startActivity(i);

        }  

        else if("Test AdminActivity".equals(viewName)) {

            Intent i = new Intent(getApplicationContext(), AdminActivity.class);

                startActivity(i);

        }        

        else {

           Toast.makeText(getApplicationContext(),

                  "clicked -  " + viewName, Toast.LENGTH_LONG)

                  .show();

        }

    }

    

    

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case ACTIVATION_REQUEST:

                if (resultCode == Activity.RESULT_OK) {

                    Log.i(TAG, "Administration enabled!");

                    //Enroll device only after admin enabled

                    MDMHelper helper = new MDMHelper();

//                              helper.enrolDeviceToServer(this,getPackageManager(),false);

//                              helper.postDeviceInfoToServer(this,getPackageManager(),false);

                    helper.enrolDeviceAndPostDeviceInfoToServer(this,getPackageManager(),false);

                    MainDAO mainDAO = new MainDAO(this);

                            mainDAO.insertEnrolmentInDB();

                    Intent i = new Intent(getApplicationContext(), ShowMainScreenActivity.class);

                    startActivity(i);                    

                } else {

                    Log.i(TAG, "Administration enable FAILED!");

                    Toast.makeText(this, R.string.device_admin_failed,

                            Toast.LENGTH_LONG).show();                    

                }

                return;

        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    

    

    public void checkAndUpdateGeneralInfo() {

        MainDAO mainDAO = new MainDAO(this);

        if(!mainDAO.Exists(Constants.DEFAULT_AGENT_ID)) {

            GeneralInfoVO gvo = new GeneralInfoVO();

               gvo.setAgentId(Constants.DEFAULT_AGENT_ID);

               gvo.setAgentInstallDate(new Date().toString());

               gvo.setAgentVersion(Constants.AGENT_VERSION);

               gvo.setAppliedPolicy(Constants.DEFAULT_POLICY);

               gvo.setLastRecordedDate(new Date().toString());

               mainDAO.addGeneralInfo(gvo);

        }

    }

    

    /**

     * Enable Admin if not already enabled

     */

    public void enableAdmin() {

        // Activate device administration

            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

                   intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,demoDeviceAdmin);

                   intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"ZenMobile Mobile Device Admin");

                   startActivityForResult(intent, ACTIVATION_REQUEST);

        Log.d(TAG, "Enabled Admin");

    }

    

    //checks device enrollment in the local database

    public boolean isDeviceEnrolled() {

        MainDAO mainDAO = new MainDAO(this);

        return mainDAO.isEnrolled();

    }

    

    

}
