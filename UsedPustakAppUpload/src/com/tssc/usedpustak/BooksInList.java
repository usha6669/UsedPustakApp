package com.tssc.usedpustak;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class BooksInList extends Activity {
	
	  ListView lv;
	  TextView tv;
		
		ArrayList<String> al = new ArrayList<String>();
		String sclass,sbranch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_in_list);
		
       lv = (ListView) findViewById(R.id.listView1);
       tv = (TextView) findViewById(R.id.textView2);
       
       Bundle bb=getIntent().getExtras();
		 sclass=bb.getString("class");
		 sbranch=bb.getString("branch");
		 final String  sname = bb.getString("name1");
		 tv.setText(sname);
		 
		lv = (ListView) findViewById(R.id.listView1);
		
			InsertionClass ref = new InsertionClass();
			ref.createDomain();
			
		List<Others1> list = ref.getnames(sclass, sbranch);

		 int len = list.size();

			for (int i = 0; i < len; i++) {

				Others1 oo = list.get(i);
			String	SSname = oo.getName();
				al.add(SSname);
				
				ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
				lv.setAdapter(adp);
				
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						String sitem=lv.getItemAtPosition(arg2).toString();


						Intent it=new Intent(BooksInList.this,BooksDetails.class);
						it.putExtra("data",sitem);
						it.putExtra("name1", sname);
						startActivity(it);
					}
				});
			}
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_books_in_list, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(BooksInList.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			 Intent it4 = new Intent(BooksInList.this,QueriesPage.class);
		     startActivity(it4);
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(BooksInList.this,MainActivity.class);
			     startActivity(i);
			
			break;
					

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
