package com.example.uas_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mountainInfo, equipment, prepare, mountainMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mountainInfo=(Button) findViewById(R.id.mountainInfo);
		equipment=(Button) findViewById(R.id.equipment);
		prepare=(Button) findViewById(R.id.prepare);
		mountainMap=(Button) findViewById(R.id.mountainMap);
		
		mountainInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent(getApplicationContext(), MountainInfoActivity.class);
				  startActivity(in);
			}
		});
		
		mountainMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent(getApplicationContext(), MountainMapActivity.class);
				  startActivity(in);
			}
		});
		
		equipment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent(getApplicationContext(), EquipmentActivity.class);
				  startActivity(in);
			}
		});
		
		prepare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent(getApplicationContext(), PrepareActivity.class);
				  startActivity(in);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
