package com.example.uas_android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.uas_android.helper.JSONParser;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class MountainInfoDetailActivity extends Activity {

	private ArrayList<MountainInformation> mList;
	private Map<String, String> detailMountain = new HashMap<String, String>();
	private boolean success = false;
	private ProgressDialog pDialog;
	
	//from php
	private static final String TAG_SUKSES = "sukses";
	private static final String TAG_ERROR = "error";
	private static final String TAG_DATA = "data";
	private static final String TAG_ROWS = "total";

	// JSONArray data
	JSONArray data = null;
	JSONParser jParser = new JSONParser();
	
	ImageView MountainPictLarge;
	TextView DescriptionMountain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mountain_info_detail);
		// UNIVERSAL IMAGE LOADER SETUP
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheOnDisc(true).cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new FadeInBitmapDisplayer(300)).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new WeakMemoryCache())
				.discCacheSize(100 * 1024 * 1024).build();
		ImageLoader.getInstance().init(config);
		
		new AmbilDataJson().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mountain_info_detail, menu);
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
	
	class AmbilDataJson extends AsyncTask<String, String, String> {

		// inisialisasi url contact.php 
		private String url= "http://api-mobile.byethost12.com/gunung/getGunungDetail";
		
		int total;
	   	
		/**
		 * sebelum memulai background thread tampilkan Progress Dialognew MountainInformation(
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MountainInfoDetailActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * mengambil semua data JSON barang dari url
		 * dan memasukan ke dalam list barang
		 * dilakukan secara background
		 * */
		protected String doInBackground(String... args) {
			// membangun Parameter
			
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();	
			
			//params.add(new BasicNameValuePair("param1", "nilai1"));
			//params.add(new BasicNameValuePair("param2", "nilai2"));
			params.add(new BasicNameValuePair("barangId", "1"));
			
			Bundle extras = getIntent().getExtras();
			Log.d("bundle extras: ", extras.getString("idGunung"));
			
			// ambil JSON string dari URL
			JSONObject json = jParser.makeHttpRequest(url + "/" + extras.getString("idGunung"), "GET", params);
			
			// cek log cat untuk JSON reponse
			Log.d("params: ", json.toString());
			
			try {
				// mengecek untuk TAG SUKSES
				total = json.getInt(TAG_ROWS);
				
				Log.d("total: ", "total"+ total);
				
				if (total > 0) {
					// data ditemukan
					// mengambil  Array dari barang
					
					data = json.getJSONArray(TAG_DATA);
					
					// looping data semua member/anggota
					for (int i = 0; i < data.length(); i++) {
						JSONObject b = data.getJSONObject(i);

						// tempatkan setiap item json di variabel
						String id					= b.getString("id");
						String MountainPictLarge 	= b.getString("MountainPictLarge");
						String DescriptionMountain 	= b.getString("DescriptionMountain");

						detailMountain.put("id", id);
						detailMountain.put("MountainPictLarge", MountainPictLarge);
						detailMountain.put("DescriptionMountain", DescriptionMountain);
					}
				} 
				else {
					Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();	
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		/**
		 * setelah menyelesaikan background task hilangkan the progress dialog
		 * resfresh List view setelah data JSON diambil
		 * **/
		protected void onPostExecute(String file_url) {
			// hilangkan dialog setelah mendapatkan semua data member
			pDialog.dismiss();
			// update UI dari Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * update hasil parsing JSON ke ListView
					 * */
					
					if (total > 0){
						Log.d("detail", "id :" +(String) detailMountain.get("id"));
						
						DescriptionMountain = (TextView)findViewById(R.id.descriptionMountain);
						MountainPictLarge = (ImageView)findViewById(R.id.mountainPictLarge);
						
						// Loader image - will be shown before loading image
						ImageLoader imageLoader = ImageLoader.getInstance();
						DisplayImageOptions options = new DisplayImageOptions.Builder()
							.cacheOnDisk(true)
						  	.cacheInMemory(true)
						    .showImageForEmptyUri(R.drawable.loader)
						    .showImageOnFail(R.drawable.loader)
						    .build();
						ImageAware imageAware = new ImageViewAware(MountainPictLarge, false);
						
						imageLoader.displayImage("http://sandra.byethost12.com/WebServiceMobile/"+detailMountain.get("MountainPictLarge"), imageAware, options);
						DescriptionMountain.setText(detailMountain.get("DescriptionMountain"));
					}
				}
			});
		}
	}//endjson
	
	
}
