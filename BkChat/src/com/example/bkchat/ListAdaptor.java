package com.example.bkchat;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListAdaptor extends BaseAdapter{
   
	private Context context;
	private ArrayList<MesgVO> listValues;
	
	public ListAdaptor(Context context, ArrayList<MesgVO> listValues) {
		this.context = context;
		this.listValues = listValues;
	}
	
	@Override
	public int getCount() {
		return listValues.size();
	}


	@Override
	public Object getItem(int position) {
		return listValues.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(MesgVO mesgVO) {
		listValues.add(mesgVO);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	   LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View listView = null;
	   TextView textView = null;
	   
	      if(convertView == null) {
	    	  listView = inflater.inflate(R.layout.list_item, parent, false);	    	  	 	    	  	    	  		
	      }
          else {
	    	  listView = (View) convertView;
	      }
	  	MesgVO mesg = (MesgVO)this.getItem(position);
	  	textView = (TextView) listView.findViewById(R.id.mesgTextID);
	  	
	  	RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textView.getLayoutParams();
	  		
	  	if("left".equals(mesg.getLocation())) {
	  		textView.setText(mesg.getMessage());
	  		textView.setBackgroundColor(Color.WHITE);
	  			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	  		textView.setLayoutParams(params);	  		
	  	} else {
	  		textView.setText(mesg.getMessage());
	  		textView.setBackgroundColor(Color.GREEN);
 			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
 		textView.setLayoutParams(params);	
		
	  	}
	  	return listView;
	}

}

