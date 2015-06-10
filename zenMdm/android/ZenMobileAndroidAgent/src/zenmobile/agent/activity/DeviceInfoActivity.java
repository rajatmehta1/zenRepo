package zenmobile.agent.activity;



import zenmobile.agent.helper.DeviceInfoHelper;

import zenmobile.agent.vo.DeviceInfoVO;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;

import android.telephony.TelephonyManager;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;



public class DeviceInfoActivity extends Activity{

    

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.device_info);

        DeviceInfoVO deviceVO = DeviceInfoHelper.fetchDeviceInfo(this);  

        

        TextView deviceNameView = (TextView)findViewById(R.id.deviceName);

            deviceNameView.setText(deviceVO.getDeviceName());

            

        TextView deviceOsView = (TextView)findViewById(R.id.deviceOS);

            deviceOsView.setText(deviceVO.getOs());

                    

        TextView deviceModelView = (TextView)findViewById(R.id.deviceModel);

            deviceModelView.setText(deviceVO.getModel());

        

        TextView deviceCarrierView = (TextView)findViewById(R.id.deviceCarrier);

        deviceOsView.setText(deviceVO.getCarrier());

        

        

        TextView phoneNumView = (TextView)findViewById(R.id.phoneNumber);

        phoneNumView.setText(deviceVO.getPhoneNumber());

        

        TextView networkView = (TextView)findViewById(R.id.deviceNetwork);

        networkView.setText(deviceVO.getNetwork());



        

        TextView carrierView = (TextView)findViewById(R.id.deviceCarrier);

        carrierView.setText(deviceVO.getCarrier());



        TextView simView = (TextView)findViewById(R.id.deviceSIMNumber);

        simView.setText(deviceVO.getSimNumber());

        

        TextView manufacturerView = (TextView)findViewById(R.id.deviceManufacturer);

        manufacturerView.setText(deviceVO.getManufacturer());        

               

    }

    

    



    

}
