package com.example.bkchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.format.Time;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener  {

	private ListView listView;
	private ArrayList<MesgVO> arrList = new ArrayList<MesgVO>();
	private ListAdaptor listAdp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner dropdown = (Spinner)findViewById(R.id.acctTypes);
		String[] items = new String[]{"Saving Account", "Checkin Account", "Credit Card"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
		dropdown.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		final Button sendButton = (Button) findViewById(R.id.chatSendBtnID);
			sendButton.setOnClickListener(this);
			
		final Button cancelButton = (Button) findViewById(R.id.transCancelBtnID);
			cancelButton.setOnClickListener(this);
			
		final Button submitButton = (Button) findViewById(R.id.transSubmitBtnID);
			submitButton.setOnClickListener(this);
			
		if(null == listAdp) {
			this.updateMessageValues();
			listAdp = new ListAdaptor(this, arrList);
			listView = (ListView)findViewById(R.id.mesgListID);
			listView.setAdapter(listAdp);
			listView.setDivider(null);
		} else {
			listAdp.notifyDataSetChanged();
		}
		
		return true;
	}

	public void updateChat(String value) {
			//arrList.clear();
		//this.updateMessageValues();
			//arrList.clear();
			//this.updateMessageValues();
			//arrList.add(new MesgVO(value, "left"));
		listAdp.addItem(new MesgVO(value, "left"));
		listAdp.notifyDataSetChanged();
	}
	public void updateMessageValues() {
			arrList.add(new MesgVO("Welcome to Chase Talk!","left"));
			arrList.add(new MesgVO("Your personal chat assistant!","left"));
//			arrList.add(new MesgVO("Hello How are you","right"));
//			arrList.add(new MesgVO("Me good","left"));
//			arrList.add(new MesgVO("Me good too","right"));
//			arrList.add(new MesgVO("ok bye","left"));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.chatSendBtnID) {
			final TextView chatBox = (TextView) findViewById(R.id.chatTextID);
				
			Toast toast = Toast.makeText(this, chatBox.getText().toString(), Toast.LENGTH_LONG);
				toast.show();
				String recvMesg = chatBox.getText().toString();
				this.updateChat(recvMesg);
				if(recvMesg.toLowerCase().contains(Constants.BILL) && recvMesg.toLowerCase().contains(Constants.PAY)) {
					ListView listView = (ListView)findViewById(R.id.mesgListID);
					 listView.setVisibility(ListView.INVISIBLE);					
					TableLayout tableLayout = (TableLayout) findViewById(R.id.payBillForm);
						tableLayout.setVisibility(TableLayout.VISIBLE);
				} else {
					this.invokeBkService(recvMesg);
				}
		}		
		else if(v.getId() == R.id.transCancelBtnID) {
			Toast toast = Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_LONG);
				toast.show();
			TableLayout tableLayout = (TableLayout) findViewById(R.id.payBillForm);
				tableLayout.setVisibility(TableLayout.INVISIBLE);
			ListView listView = (ListView)findViewById(R.id.mesgListID);
				 listView.setVisibility(ListView.VISIBLE);					
		}		
		else if(v.getId() == R.id.transSubmitBtnID) {
			Toast toast = Toast.makeText(this, "Transaction Submitted", Toast.LENGTH_LONG);
			toast.show();
			TableLayout tableLayout = (TableLayout) findViewById(R.id.payBillForm);
			tableLayout.setVisibility(TableLayout.INVISIBLE);	
			ListView listView = (ListView)findViewById(R.id.mesgListID);
			 listView.setVisibility(ListView.VISIBLE);			
		}		
	}

	private void invokeBkService(String msg_) {		
		//BkServiceHelper bkh = new BkServiceHelper(); f......ddddd not working
		//	bkh.getResponse(msg_);
		String result = "Service might be down, please try later";
		String mainResult = "";
			BkServiceHelper bkh = new BkServiceHelper();
				bkh.execute(msg_);
				
				try {
					result = bkh.get(3, TimeUnit.SECONDS);
					mainResult = Html.fromHtml(result).toString();
				} catch (InterruptedException | ExecutionException
						| TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		this.updateChatResponse(mainResult);
	}
	
	public void updateChatResponse(String value) {
		listAdp.addItem(new MesgVO(value, "right"));
		listAdp.notifyDataSetChanged();
		final TextView chatBox = (TextView) findViewById(R.id.chatTextID);
		chatBox.setText("");
		
	}
	
}
