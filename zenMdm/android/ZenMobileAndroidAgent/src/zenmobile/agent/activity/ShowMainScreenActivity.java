package zenmobile.agent.activity;





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

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;



/*

* Just a copy of the main screen for displaying

*/

public class ShowMainScreenActivity extends ListActivity implements OnItemClickListener {

    static final String TAG = "MainActivity";

    static final int ACTIVATION_REQUEST = 47; // identifies our request id

    ComponentName demoDeviceAdmin;

    

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

                String[] zen_options = getResources().getStringArray(R.array.zenmobile_options);

                    this.setListAdapter(new ArrayAdapter<String>(this, R.layout.zen_option_item, R.id.label, zen_options));

                ListView lv = getListView();

                    lv.setOnItemClickListener(this);

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

        else {

           Toast.makeText(getApplicationContext(),

                  "clicked -  " + viewName, Toast.LENGTH_LONG)

                  .show();

        }

    }

    

    



}