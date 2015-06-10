package zenmobile.agent.activity;



import zenmobile.agent.helper.DeviceInfoHelper;

import zenmobile.agent.helper.MDMHelper;

import zenmobile.agent.vo.DeviceInfoVO;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.TextView;

import android.widget.Toast;



//Just a class used for testing various services

public class TestActivity extends Activity implements OnClickListener {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.unit_test);

        

        Button button = (Button)findViewById(R.id.testPostBtn);

        button.setOnClickListener(this);

        

        Button button2 = (Button)findViewById(R.id.testEnrolBtn);

        button.setOnClickListener(this);   

        button2.setOnClickListener(this);        

    }



    @Override

    public void onClick(View view) {

        MDMHelper helper = new MDMHelper();

        if(view.getId() == R.id.testEnrolBtn) {

            helper.enrolDeviceToServer(this,getPackageManager(),false);

        } else {

            helper.postDeviceInfoToServer(this,getPackageManager(),false);    

        }

        

    }

    

}

