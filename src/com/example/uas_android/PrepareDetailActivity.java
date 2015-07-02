package com.example.uas_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class PrepareDetailActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepare_detail);
		
		TextView jml1 = (TextView)findViewById(R.id.jml1);
		TextView jml2 = (TextView)findViewById(R.id.jml2);
		TextView jml3 = (TextView)findViewById(R.id.jml3);
		TextView jml4 = (TextView)findViewById(R.id.jml4);
		TextView jml5 = (TextView)findViewById(R.id.jml5);
		TextView jml6 = (TextView)findViewById(R.id.jml6);
		TextView jml7 = (TextView)findViewById(R.id.jml7);
		TextView jml8 = (TextView)findViewById(R.id.jml8);
		TextView jml9 = (TextView)findViewById(R.id.jml9);
		TextView jml10 = (TextView)findViewById(R.id.jml10);
		TextView jml11 = (TextView)findViewById(R.id.jml11);
		TextView jml12 = (TextView)findViewById(R.id.jml12);
		
		Bundle extras = getIntent().getExtras();
		int jmlPendaki = Integer.parseInt( extras.getString("jp") );
		int jmlMenginap = Integer.parseInt( extras.getString("lm") );
		
		int tenda, beras, airMatang, airMentah;
		
		tenda = (int)Math.floor((double)((1 + jmlPendaki) / 2));
		beras = jmlPendaki * jmlMenginap;
		airMatang = 3 * jmlPendaki * jmlMenginap;
		airMentah = 2 * jmlPendaki * jmlMenginap;
		
		jml1.setText(String.valueOf(tenda) +" buah");
		jml2.setText(String.valueOf(jmlPendaki) +" buah");
		jml3.setText(String.valueOf(jmlPendaki) +" buah");
		jml4.setText(String.valueOf(beras) +" liter");
		jml5.setText(String.valueOf(jmlPendaki) +" set");
		jml6.setText(String.valueOf(jmlPendaki) +" buah");
		jml7.setText(String.valueOf(airMatang) +" liter");
		jml8.setText("1 buah");
		jml9.setText(String.valueOf(jmlPendaki) +" buah");
		jml10.setText(String.valueOf(airMentah) +" liter");
		jml11.setText(String.valueOf(jmlPendaki) +" buah");
		jml12.setText(String.valueOf(jmlPendaki) +" buah");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prepare_detail, menu);
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
