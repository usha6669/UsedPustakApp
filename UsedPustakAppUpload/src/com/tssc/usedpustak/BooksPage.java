package com.tssc.usedpustak;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BooksPage extends Activity implements OnItemSelectedListener {

	Spinner classes,branches;

	String sclass,sbranch;
	AmazonSimpleDBClient sdbClient;
	Button bt;
	static TextView tv;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_page);
		
		tv = (TextView) findViewById(R.id.textView4);
		
		putName();

		  Bundle bd = getIntent().getExtras();
			String semail = bd.getString("email");
		  
		RegistrationClass ref = new RegistrationClass();
			ref.createDomain();
			List<Others1> list = ref.getDetails(semail);
			  
			 int len = list.size();
				System.out.println("" + list);
				String data1 = list.toString();
				String[] a1 = data1.split(",");

			final String name = a1[14];
			String[] s1 = name.split(":");
			final String name1 = s1[1];
			tv.setText(name1);
			
			
		classes = (Spinner) findViewById(R.id.spinner1);
		branches= (Spinner) findViewById(R.id.spinner2);
		
		classes.setOnItemSelectedListener(this);
            
	  bt = (Button) findViewById(R.id.button1);
	  
		
	  
	  bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		    String  sclass =  classes.getSelectedItem().toString();
			String  sbranch =  branches.getSelectedItem().toString();


			if(sclass.contentEquals("...Select..."))
			{
				Toast.makeText(getApplicationContext(), "Please select Standard", Toast.LENGTH_SHORT).show();
			}
			
			else if(sbranch.contentEquals("...Select..."))
			{
				Toast.makeText(getApplicationContext(), "Please select branch", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				 
					Intent it = new Intent(BooksPage.this,BooksInList.class);
					it.putExtra("class",sclass);
					it.putExtra("branch", sbranch);
					it.putExtra("name1", name1);
						  startActivity(it); 
					
			}
			
		}
	});
	  
	   
	}

	
	
		public static String putName()
		{
			String uname1 = tv.getText().toString().trim();
			return uname1;
		}
	
	
	
	    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
			// TODO Auto-generated method stub
			String sclass =  String.valueOf(classes.getSelectedItem());
			
			if(sclass.contentEquals("6thClass") || sclass.contentEquals("7thClass") || sclass.contentEquals("8thClass")|| sclass.contentEquals("9thClass") || sclass.contentEquals("10thClass"))
			{
				//Toast.makeText(getApplicationContext(), "Please click on submit", Toast.LENGTH_SHORT).show();
				List<String> list = new ArrayList<String>();
				  list.add("...Select...");
				  list.add("StateBoard");
				  list.add("CBSE");
				  list.add("ICSE");
				  ArrayAdapter<String> adp =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
				  
				  adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				  adp.notifyDataSetChanged();
				  branches.setAdapter(adp);
				  
				  
			}
			if(sclass.contentEquals("Intermediate"))
			{
			  List<String> list = new ArrayList<String>();
			  list.add("...Select...");
			  list.add("MPC");
			  list.add("BiPC");
			  ArrayAdapter<String> adp =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
			  
			  adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			  adp.notifyDataSetChanged();
			  branches.setAdapter(adp);
			}
			
			if(sclass.contentEquals("Engineering"))
			{
			  List<String> list = new ArrayList<String>();
			  list.add("...Select...");
			  list.add("CSE");
			  list.add("ECE");
			  list.add("EEE");
			  list.add("MECHANICAL");
			  list.add("CIVIL");
			  list.add("IT");
			 
			  ArrayAdapter<String> adp2 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
			  
			  adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			  adp2.notifyDataSetChanged();
			  branches.setAdapter(adp2);
			}
		
			
		}
		
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		   	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_books_page, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(BooksPage.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			Intent it4 = new Intent(BooksPage.this,QueriesPage.class);
		     startActivity(it4);
			
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(BooksPage.this,MainActivity.class);
			     startActivity(i);
			
			break;
					

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
