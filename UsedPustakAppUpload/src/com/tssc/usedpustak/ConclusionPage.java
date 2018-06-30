package com.tssc.usedpustak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ConclusionPage extends Activity {

	ImageButton ib;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conclusion_page);
		
		ib = (ImageButton) findViewById(R.id.imageButton1);
		ib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ConclusionPage.this,QueriesPage.class);
				startActivity(it);
			}
		});
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.item1:
			  Intent it = new Intent(ConclusionPage.this,MainActivity.class);
			     startActivity(it);
			break;
			
		
		
					
		case R.id.item7:
			 Intent it4 = new Intent(ConclusionPage.this,QueriesPage.class);
		     startActivity(it4);
		     
			break;
		case R.id.item8:
		     
			     Intent i = new Intent(ConclusionPage.this,MainActivity.class);
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
		getMenuInflater().inflate(R.menu.activity_conclusion_page, menu);
		return true;
	}

}
