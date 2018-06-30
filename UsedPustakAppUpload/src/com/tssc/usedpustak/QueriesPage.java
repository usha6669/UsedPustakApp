package com.tssc.usedpustak;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class QueriesPage extends Activity {
	
	
	TextView tv2;

	EditText email;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queries_page);
		
		tv2 = (TextView) findViewById(R.id.textView2);
		email =(EditText) findViewById(R.id.editText1);
		
		String text = "Click Here";
		SpannableString  ss  = new SpannableString(text);
		
		ClickableSpan clickspan = new ClickableSpan() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendEmail();
			}
			
		@Override
			public void updateDrawState(TextPaint ds) {
				super.updateDrawState(ds);
				ds.setColor(Color.BLUE);
				ds.setUnderlineText(true);
			}
		
		};
		ss.setSpan(clickspan,0,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
		tv2.setText(ss);
		tv2.setMovementMethod(LinkMovementMethod.getInstance());
	
	}

	protected void sendEmail() {
		// TODO Auto-generated method stub
		
			 String FROM = email.getText().toString();
			 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			 
	       if(FROM.length()==0){
				alertDialogBuilder.setMessage("email should not be empty!");
	   		AlertDialog alertDialogd = alertDialogBuilder.create();
	   		alertDialogd.show();
				//Toast.makeText(getApplicationContext(), "email should not be empty!", Toast.LENGTH_SHORT).show();
				
			}else if(!FROM.matches(emailPattern)){
				alertDialogBuilder.setMessage("invalid email!");
	   		AlertDialog alertDialoge = alertDialogBuilder.create();
	   		alertDialoge.show();
				//Toast.makeText(getApplicationContext(), "invalid email!", Toast.LENGTH_SHORT).show();
				
			}else {
	       
	       Log.i("Send email", "");
	       String[] TO = {"ninishaballa@gmail.com","prava24598@gmail.com","reddyusha651@gmail.com"};
	       Intent emailIntent = new Intent(Intent.ACTION_SEND,Uri.parse("user email: "+FROM));
	       
	       //emailIntent.setData(Uri.parse(FROM));
	       emailIntent.setType("text/plain");
	       emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	       //emailIntent.putExtra(Intent.EXTRA_EMAIL, FROM);
	       //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "email send by "+uname);
	       emailIntent.putExtra(Intent.EXTRA_TEXT,"Enter your query..." );
	       
	          startActivity(Intent.createChooser(emailIntent,"choose a client :"));
	          finish();
	          Log.i("Finished sending email...", "");
	          //Toast.makeText(QueriesPage.this, "your query has been set successfully "+uname+" , you will get the reply soon", Toast.LENGTH_LONG).show();
			}
		}

		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(QueriesPage.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			 Intent it4 = new Intent(QueriesPage.this,QueriesPage.class);
		     startActivity(it4);
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(QueriesPage.this,MainActivity.class);
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
		getMenuInflater().inflate(R.menu.activity_queries_page, menu);
		return true;
		
		
	}	
		

}
