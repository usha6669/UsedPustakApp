package com.tssc.usedpustak;

import java.util.List;

import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationPage extends Activity {

	 TextView tv5,tv7,tv9,tv3,tv11,tv13,tv15,tv17;
	 Button bconfirm;
	 AmazonSimpleDBClient sdbClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation_page);
		
          tv5 = (TextView) findViewById(R.id.textView5);
          tv7 = (TextView) findViewById(R.id.textView7);
          tv9 = (TextView) findViewById(R.id.textView9);
          tv3 = (TextView) findViewById(R.id.textView3);

          tv11 = (TextView) findViewById(R.id.textView11);
          tv13 = (TextView) findViewById(R.id.textView13);
          tv15 = (TextView) findViewById(R.id.textView15);
          tv17 = (TextView) findViewById(R.id.textView17);
          
          bconfirm = (Button) findViewById(R.id.button1);
		
		Bundle bd = getIntent().getExtras();
		
		 String sname = bd.getString("sname").trim();
		String sprice = bd.getString("price").trim();
		String quantity = bd.getString("qty").trim();
		
		tv5.setText(sname);
		tv7.setText(sprice);
		tv9.setText(quantity);
		
		final String uname = bd.getString("uname").trim();
		
		tv3.setText(uname);
		
		RegistrationClass ref = new RegistrationClass();
		ref.createDomain();
		List<Others1> list = ref.getDetails1(uname);
		  
		 int len = list.size();
			System.out.println("" + list);
			String data1 = list.toString();
			String[] a1 = data1.split(",");

			//String name= a1[9];
			String email = a1[24];
			String pwd = a1[4];
			String address= a1[9];
			String mobile=a1[19];
			
			

			System.out.println(""+ email + pwd + address + mobile  );
			//String[] s1 = name.split(":");
			String[] s2 = email.split(":");
			String[] s3 = pwd.split(":");
			String[] s4 = address.split(":");
			String[] s5 = mobile.split(":");
			
			
			//final String name1 = s1[1];
			final String email1 = s2[1];
			final String pwd1 = s3[1];
			final String address1 = s4[1];
			final String mobile1 = s5[1];
			
			
			tv11.setText(email1);
			tv13.setText(pwd1);
			tv15.setText(address1);
			tv17.setText(mobile1);
			
		
		bconfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			Intent it = new Intent(ConfirmationPage.this,ConclusionPage.class);
				Bundle bd = getIntent().getExtras();
				
				//getting availability
				
				final String availability = bd.getString("avail1").trim();
				//getting branch,class,sid1
				final String branch = bd.getString("branch").trim();
				final String class1 = bd.getString("class").trim();
				final String sid = bd.getString("sid1").trim();
				 final   String price = tv7.getText().toString().trim();
				  final  String quantity = tv9.getText().toString().trim();
				final  String mobile = tv17.getText().toString().trim();
				 final  String bname = tv5.getText().toString().trim();

				
				InsertionClass ref = new InsertionClass();
				    ref.updateTable(sid,bname,class1,branch,availability);
				
				 DeliveredClass ref1 = new DeliveredClass();
				    String name = tv3.getText().toString().trim();
				    String email = tv11.getText().toString().trim();
				    String address =  tv15.getText().toString().trim();
				  //  String mobile = tv17.getText().toString().trim();
				  // String price = tv7.getText().toString().trim();
				  //  String quantity = tv9.getText().toString().trim();
				 
				  ref1.Addtotable(name,email,address,mobile,bname,price,quantity);
				  

				  SmsManager sms = SmsManager.getDefault();
			  sms.sendTextMessage(mobile, null, "From: UsedPustak \n You have ordered for" +bname+"with quantity"+quantity+"and your total price is" +price,null, null);
				    
				    Toast.makeText(getApplicationContext(), "UpdatedSuccessfully",Toast.LENGTH_SHORT).show();
				    
				    startActivity(it);

			}
		});
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(ConfirmationPage.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			 Intent it4 = new Intent(ConfirmationPage.this,QueriesPage.class);
		     startActivity(it4);
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(ConfirmationPage.this,MainActivity.class);
			     startActivity(i);
			
			break;
					
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_confirmation_page, menu);
		return true;
	}

}
