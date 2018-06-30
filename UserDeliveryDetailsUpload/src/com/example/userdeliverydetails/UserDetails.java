package com.example.userdeliverydetails;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends Activity {

	TextView tv2,tv4,tv6,tv8,tv10,tv12,tv16;
	EditText ed1;
	
	Button bt;
	AmazonSimpleDBClient sdbClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		
		
		tv2 = (TextView) findViewById(R.id.textView2);
		tv4 = (TextView) findViewById(R.id.textView4);
		tv6 = (TextView) findViewById(R.id.textView6);
		tv8 = (TextView) findViewById(R.id.textView8);
		tv10 = (TextView) findViewById(R.id.textView10);
		tv12 = (TextView) findViewById(R.id.textView12);
		tv16 = (TextView) findViewById(R.id.textView16);
		
		ed1 = (EditText) findViewById(R.id.editText1);
		 bt = (Button) findViewById(R.id.button1);
		
		Bundle bd = getIntent().getExtras();
		String sname = bd.getString("data").trim();
		tv16.setText(sname);
		
		AWSCredentials credentials = new BasicAWSCredentials(
				Constants.ACCESS_KEY_ID, Constants.SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		DeliverClass ref= new DeliverClass();
		ref.createDomain();

		List<Others1> list = ref.getDetails(sname);
		 		  
		 int len = list.size();
			System.out.println("" + list);
			String data1 = list.toString();
			String[] a1 = data1.split(",");

		//	String name= a1[9];
			String address = a1[19];
			String bookname = a1[14];
			String price = a1[4];
			String quantity = a1[9];
			String email = a1[29];
			String mobile = a1[34];

			System.out.println("" + address + bookname + price+ quantity + email + mobile);
			//String[] s1 = name.split(":");
			
			String[] s2 = address.split(":");
			String[] s3 = bookname.split(":");
			String[] s4 = price.split(":");
			String[] s5 = quantity.split(":");
			String[] s6 = email.split(":");
			String[] s7 = mobile.split(":");

			//final String name1 = s1[1];
			final String address1 = s2[1];
			final String bookname1 = s3[1];
			final String price1 = s4[1];
			final String quantity1 = s5[1];
			final String email1 = s6[1];
			final String mobile1 = s7[1];

			
			tv2.setText(address1);
			tv4.setText(bookname1);
			tv6.setText(price1);
			tv8.setText(quantity1);
			tv10.setText(email1);
			tv12.setText(mobile1);
			
			
			bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					String status = ed1.getText().toString().trim();
					String uname = tv16.getText().toString().trim();
					String mobile = tv12.getText().toString().trim();
					   if(status.equals(""))
					   {
						   Toast.makeText(getApplicationContext(), "Please enter status", Toast.LENGTH_LONG).show();
					   }
					 
					   else
					   {
						    
						   DeliverClass ref= new DeliverClass();
						   
						   ref.updateTable(uname,status);
						    
						   SmsManager sms = SmsManager.getDefault();
						   sms.sendTextMessage(mobile, null,"From: UsedPustak \n Your Order has been delivered \n Thank You!!!", null,null);
						   
						   Toast.makeText(getApplicationContext(), "Thank You..", Toast.LENGTH_SHORT).show();
					   }
				}
			});
			  

			}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_details, menu);
		return true;
	}

}
