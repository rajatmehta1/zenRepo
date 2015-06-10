package zenmobile.agent.activity;



import zenmobile.agent.dao.MainDAO;

import zenmobile.agent.helper.DeviceInfoHelper;

import zenmobile.agent.util.Constants;

import zenmobile.agent.vo.DeviceInfoVO;

import zenmobile.agent.vo.GeneralInfoVO;

import android.app.Activity;

import android.os.Bundle;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;



public class GeneralInfoActivity extends Activity{

    

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.general_info);

        MainDAO mainDAO = new MainDAO(this);  

        GeneralInfoVO gvo = mainDAO.getGeneralInfo(Constants.DEFAULT_AGENT_ID);

        

            TextView agentVersionText = (TextView)findViewById(R.id.agentVersion);

            agentVersionText.setText(gvo.getAgentVersion());

            

            TextView agentInstallDate = (TextView)findViewById(R.id.agentInstallDate);

            agentInstallDate.setText(gvo.getAgentInstallDate());

            

            TextView agentPolicy = (TextView)findViewById(R.id.agentPolicy);

            agentPolicy.setText(gvo.getAppliedPolicy());

            

            TextView lastRecorded = (TextView)findViewById(R.id.lastRecorded);

            lastRecorded.setText(gvo.getLastRecordedDate());            



    }

    



}