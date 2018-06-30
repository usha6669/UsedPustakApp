package com.tssc.usedpustak;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Activity {

	protected static final int String = 0;
	TextView tv;
	Button blogin;
	EditText ed1,ed2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		tv=(TextView) findViewById(R.id.textView2);
		blogin = (Button) findViewById(R.id.button1);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		  
		blogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String email = ed1.getText().toString();
				String pwd = ed2.getText().toString();
				
				if(email.length()==0 )
				{
					ed1.setError("Please enter email");
				}
				else if(pwd.length()==0)
				{
					ed2.setError("Please enter password");
				}
				
				else
				{
					
					RegistrationClass ref = new  RegistrationClass();
						int ver = ref.loginVerify(email,pwd);
						if (ver == 0) {
							Toast.makeText(getApplicationContext(), "InvalidCredentials ,Register if not yet registered", Toast.LENGTH_SHORT).show();
						}else {

							Toast.makeText(getApplicationContext(), "SuccessfullyLoggedIn", Toast.LENGTH_SHORT).show();
							Intent it = new Intent(LoginPage.this,BooksPage.class);
							it.putExtra("email", email);
							startActivity(it);

						}
				
				}
			}
		});
		
		
		String text = "Create new";
		SpannableString  ss  = new SpannableString(text);
		
		ClickableSpan clickspan = new ClickableSpan() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(LoginPage.this,RegisterPage.class);
				startActivity(it);
			}
			
			@Override
			public void updateDrawState(TextPaint ds) {
				super.updateDrawState(ds);
				ds.setColor(Color.BLUE);
				ds.setUnderlineText(true);
			}
		
		};
		ss.setSpan(clickspan,0,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
		tv.setText(ss);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login_page, menu);
		return true;
	}

}
