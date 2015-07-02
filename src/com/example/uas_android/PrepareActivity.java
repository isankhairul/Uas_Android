package com.example.uas_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PrepareActivity extends Activity {

	TextView foo;
	Button submit;
	EditText jp, lm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepare);
		
		submit = (Button)findViewById(R.id.button1);
		foo = (TextView)findViewById(R.id.textView1);
		foo.setText(Html.fromHtml(getString(R.string.prepare_instruction)));
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				jp = (EditText)findViewById(R.id.jmlPendaki);
				lm = (EditText)findViewById(R.id.jmlMenginap);
				
				if( jp.getText().toString().length() == 0 ){
					jp.setError( "required!" ); return;}
				if( lm.getText().toString().length() == 0 ){
					lm.setError( "required!" ); return;}
				
				Intent in = new Intent(getApplicationContext(), PrepareDetailActivity.class);
	            Bundle bun = new Bundle();					
	            bun.putString("jp", jp.getText().toString());
	            bun.putString("lm", lm.getText().toString());
	            in.putExtras(bun);
				
				// mulai activity baru dan dapatkan respon dengan result kode 100
	            startActivityForResult(in, 100);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prepare, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
