package com.example.userdeliverydetails;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UsersInList extends Activity {
	
	ListView lv;
	
	ArrayList<String> al = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users_in_list);
			
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		
		lv = (ListView) findViewById(R.id.listView1);
		
		 final DeliverClass ref  = new DeliverClass();
		 ref.createDomain();
		
		 List<Others1> list = ref.getnames();
		 
		 int len = list.size();

			for (int i = 0; i < len; i++) {

				Others1 oo = list.get(i);
			String	SSname = oo.getName();
				al.add(SSname);
			System.out.println(" names       " + oo.getName()
						+ "===========");
				ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
				lv.setAdapter(adp);
			}	
			
			
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					String sitem=lv.getItemAtPosition(arg2).toString();


						Intent it=new Intent(UsersInList.this,UserDetails.class);
						it.putExtra("data",sitem);
						startActivity(it);
						
					//Toast.makeText(getApplicationContext(), sitem,Toast.LENGTH_SHORT).show();
					}
				
			});
		}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_users_in_list, menu);
		return true;
	}

}
