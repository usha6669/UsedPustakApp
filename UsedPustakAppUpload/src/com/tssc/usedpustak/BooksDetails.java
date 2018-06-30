package com.tssc.usedpustak;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BooksDetails extends Activity {

	TextView tv1,tv3,tv5,tv7,tv9,tv11,tv13,tv14,tv15,tv17,tv18;
	AmazonSimpleDBClient sdbClient;
  Button add,remove,buy;
	
	int quantity;
  int avail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_details);
		
		tv1 = (TextView) findViewById(R.id.textView1);
		tv7 = (TextView) findViewById(R.id.textView7);
		tv3 = (TextView) findViewById(R.id.textView3);
		tv9 = (TextView) findViewById(R.id.textView9);
		tv5 = (TextView) findViewById(R.id.textView5);
		tv11 = (TextView) findViewById(R.id.textView11);
		tv13 = (TextView) findViewById(R.id.textView13);
		tv14 = (TextView) findViewById(R.id.textView14);
		
		tv15 = (TextView) findViewById(R.id.textView15);
		tv17 = (TextView) findViewById(R.id.textView17);
		tv18 = (TextView) findViewById(R.id.textView18);
		
		 Bundle bd1 = getIntent().getExtras();
			final String uname = bd1.getString("name1");
			tv15.setText(uname);
		
		add = (Button) findViewById(R.id.button1);
		remove = (Button) findViewById(R.id.button2);
		buy=(Button) findViewById(R.id.button3);
		
	
		
		
		
		
		Bundle bd = getIntent().getExtras();
		String sname = bd.getString("data");
		tv1.setText(sname);
		
		AWSCredentials credentials = new BasicAWSCredentials(
				Constants.ACCESS_KEY_ID, Constants.SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		InsertionClass ref= new InsertionClass();
		ref.createDomain();

		List<Others1> list = ref.getDetails(sname);
		 		  
		 int len = list.size();
			System.out.println("" + list);
			String data1 = list.toString();
			String[] a1 = data1.split(",");

		
				//String name= a1[9];
				String standard = a1[34];
				String price = a1[14];
				String pages= a1[9];
				String language=a1[4];
				String condition = a1[24];
				String availability = a1[44];
				String branch = a1[39];
				String sid = a1[19];

				System.out.println(""+ standard + price + pages + language + condition );
				//String[] s1 = name.split(":");
				String[] s2 = standard.split(":");
				String[] s3 = price.split(":");
				String[] s4 = pages.split(":");
				String[] s5 = language.split(":");
				String[] s6 = condition.split(":");
				String[] s7 = availability.split(":");
				String[] s8 = branch.split(":");
				String[] s9 = sid.split(":");
				
				//final String name1 = s1[1];
				final String standard1 = s2[1];
				final String price1 = s3[1];
				final String pages1 = s4[1];
				final String language1 = s5[1];
				final String condition1 = s6[1];
				final String availability1 = s7[1];
				final String branch1 = s8[1];
				final String sid1 = s9[1];
				
				tv3.setText(standard1);
				tv5.setText(price1);
				tv7.setText(pages1);
				tv9.setText(language1);
				tv11.setText(condition1);	
				tv13.setText(availability1);
				tv17.setText(branch1);
				tv18.setText(sid1);
				
				
				buy.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						 int quantity = Integer.parseInt(tv14.getText().toString());
						if(quantity==0)
						{
							Toast.makeText(getApplicationContext(), "Minimum quantity should be atleast 1", Toast.LENGTH_SHORT).show();
						}
						
						
						else
						{
							float price1 = Float.parseFloat(tv5.getText().toString());
							int quantity1= Integer.parseInt(tv14.getText().toString());
							//calculating price

							float price = quantity1*price1;

							
							Intent it = new Intent(BooksDetails.this,ConfirmationPage.class);
							
							 String sname = tv1.getText().toString().trim();
							 
							 String price2 = Float.toString(price);
							 
							 String quantity2 = Integer.toString(quantity1);
							 String avail = tv13.getText().toString().trim();
								String branch = tv17.getText().toString().trim();
								String uname = tv15.getText().toString().trim();
								String class1 = tv3.getText().toString().trim();
								String sid = tv18.getText().toString().trim();
							 
						    it.putExtra("sname", sname);
							it.putExtra("class",class1);
							it.putExtra("branch", branch);
							it.putExtra("sid1", sid);
							it.putExtra("price", price2);
							it.putExtra("qty",quantity2);
							it.putExtra("avail1",avail );
							it.putExtra("uname", uname);
							startActivity(it);
						}
					
						
					}
				});
				
				add.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String svalue=tv13.getText().toString().trim();
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           "+svalue);
						int avail = Integer.parseInt(svalue);
						int quantity = Integer.parseInt(tv14.getText().toString().trim());
						
							quantity = quantity+1;
							avail = avail-1;
					
					  if(avail<=0)
						{
						  String savv=Integer.toString(avail).trim();
						  tv13.setText(savv);
						  String sqty = Integer.toString(quantity);
						  tv14.setText(""+sqty);
							Toast.makeText(getApplicationContext(),"There are no available books", Toast.LENGTH_SHORT).show();
						}
						else
						{
						//     display(quantity,avail);
							String savv=Integer.toString(avail).trim();
							String sqty = Integer.toString(quantity);
							tv13.setText(savv);
							tv14.setText(""+sqty);
						}
						
					

					}
					
				});
				
				remove.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						int avail = Integer.parseInt(tv13.getText().toString().trim());
		                int quantity = Integer.parseInt(tv14.getText().toString().trim());
						
		                avail = avail+1;
						quantity = quantity-1;
					    	if( quantity<=0)
					    	{
					    		String savv=Integer.toString(avail).trim();
					    		String sqty = Integer.toString(quantity);
								tv13.setText(savv);
								
								tv14.setText(""+sqty);
					    		Toast.makeText(getApplicationContext(), "There are no items in the cart", Toast.LENGTH_SHORT).show();
					    	}
					    	else
					    	{
					    		//display(quantity,avail);
					    		String savv=Integer.toString(avail).trim();
								String sqty = Integer.toString(quantity);
								tv13.setText(savv);
								
								tv14.setText(""+sqty);
					    	}
						
					}
				});	
				
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_books_details, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(BooksDetails.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			 Intent it4 = new Intent(BooksDetails.this,QueriesPage.class);
		     startActivity(it4);
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(BooksDetails.this,MainActivity.class);
			     startActivity(i);
			
			break;
					

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
