package com.example.uas_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

public class EquipmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipment);
		
		TextView foo = (TextView)findViewById(R.id.textView1);
		foo.setText(Html.fromHtml(getString(R.string.equipment), new ImageGetter(), null));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.equipment, menu);
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
	
	private class ImageGetter implements Html.ImageGetter {

		public Drawable getDrawable(String source) {
			int id;
			if (source.equals("sepatu.png")) {
				id = R.drawable.sepatu;
			}
			else if (source.equals("carrier.png")) {
				id = R.drawable.carrier;
			}
			else if (source.equals("tenda.png")) {
				id = R.drawable.tenda;
			}
			else if (source.equals("jaket.png")) {
				id = R.drawable.jaket;
			}
			else if (source.equals("sb_dan_matras.png")) {
				id = R.drawable.sb_dan_matras;
			}
			else if (source.equals("alat_masak.png")) {
				id = R.drawable.alat_masak;
			}
			else if (source.equals("obat.png")) {
				id = R.drawable.obat;
			}
			else if (source.equals("kompas_n_headlamp.png")) {
				id = R.drawable.kompas_n_headlamp;
			}
			else if (source.equals("kamera_dan_hp.png")) {
				id = R.drawable.kamera_dan_hp;
			}
			else if (source.equals("makanan.png")) {
				id = R.drawable.makanan;
			}
			else if (source.equals("cantigi.png")) {
				id = R.drawable.cantigi;
			}
			else if (source.equals("murbai.png")) {
				id = R.drawable.murbai;
			}
			else if (source.equals("ciplukan.png")) {
				id = R.drawable.ciplukan;
			}
			else if (source.equals("calingcing.png")) {
				id = R.drawable.calingcing;
			}
			else if (source.equals("stevia.png")) {
				id = R.drawable.stevia;
			}
			else if (source.equals("jamur.png")) {
				id = R.drawable.jamur;
			}
			else if (source.equals("begonia.png")) {
				id = R.drawable.begonia;
			}
			else if (source.equals("honje.png")) {
				id = R.drawable.honje;
			}
			else if (source.equals("harendong.png")) {
				id = R.drawable.harendong;
			}
			else if (source.equals("ciplukan.png")) {
				id = R.drawable.ciplukan;
			}
			else {
				return null;
			}

			Drawable d = getResources().getDrawable(id);
			d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
			return d;
		}
	}
}
