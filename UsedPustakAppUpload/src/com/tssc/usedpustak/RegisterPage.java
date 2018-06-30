package com.tssc.usedpustak;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends Activity {
	
	EditText ed1;
	EditText ed2;
	EditText ed3;
	EditText ed4;
	EditText ed5;
	EditText ed6;
	
	Button breset,bsubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_page);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 =(EditText) findViewById(R.id.editText2);
		ed3 = (EditText) findViewById(R.id.editText3);
		ed4 = (EditText) findViewById(R.id.editText4);
		ed5 = (EditText) findViewById(R.id.editText5);
		ed6 =  (EditText) findViewById(R.id.editText6);
		
		bsubmit=(Button) findViewById(R.id.button1);
		
		bsubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name = ed1.getText().toString().trim();
				String address = ed2.getText().toString().trim();
				String mobile = ed3.getText().toString().trim();
				String email = ed4.getText().toString().trim();
				String pwd = ed5.getText().toString().trim();
				String cpwd = ed6.getText().toString().trim();
				
				
				if(name.equals("")|| address.equals("") || mobile.equals("") || email.equals("") || pwd.equals("")|| cpwd.equals(""))
				{
					Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
				}
				  else if(mobile.length()!=10)
				  {
					  Toast.makeText(getApplicationContext(), "Mobile No should be of 10 digits", Toast.LENGTH_SHORT).show();
				  }
				 
				  else if(mobile.startsWith ("0") || mobile.startsWith("1") || mobile.startsWith("2") || mobile.startsWith("3") || mobile.startsWith("4") || mobile.startsWith("5"))
				  {
					  Toast.makeText(getApplicationContext(), "Enter a valid mobileno", Toast.LENGTH_SHORT).show();
				  }
				 else if(email.length()==0)
				  {
					 ed4.setError("Please enter email");
				  }
				  else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))      
				  {
					  Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
				  }
				 
				  else if(pwd.length()<8)
				  {
					  Toast.makeText(getApplicationContext(), "Password should of atleast 8 characters", Toast.LENGTH_SHORT).show();
				  }
				
				  else if (!pwd.equals(cpwd))
				  {
					  Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
				  }
				  
				  else
				  {
					  
					  RegistrationClass ref = new  RegistrationClass();
					  /*int ver = ref.registerVerify(name,email,mobile);
						if (ver!=0) {
							Toast.makeText(getApplicationContext(), "User already exists with the given name,email,mobileno", Toast.LENGTH_LONG).show();
						}
						else
						{*/
							ref.createDomain();
							ref.AddToTable(name,address,mobile,email,pwd);
					  
							Toast.makeText(getApplicationContext(), "Successfully Registered ", Toast.LENGTH_LONG).show();
							Intent it = new Intent(RegisterPage.this,LoginPage.class);
							startActivity(it);
						}
					
				  }
				
				
			 
				
		});
		
		
		breset =(Button) findViewById(R.id.button2);
		breset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed1.setText("");
				ed2.setText("");
				ed3.setText("");
				ed4.setText("");
				ed5.setText("");
				ed6.setText("");
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_register_page, menu);
		return true;
	}

}
