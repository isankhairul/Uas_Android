package com.example.uas_android;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.uas_android.helper.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class MountainMapActivity extends Activity{

	private ArrayList<MountainInformation> mList;
	private boolean success = false;
	//private
	MyCustomAdapter dataAdapter;
	private ProgressDialog pDialog;

	//from php
	private static final String TAG_SUKSES = "sukses";
	private static final String TAG_ERROR = "error";
	private static final String TAG_DATA = "data";
	private static final String TAG_ROWS = "total";

	// JSONArray data
	JSONArray data = null;
	JSONParser jParser = new JSONParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mountain_map);
		
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
		
		mList = new ArrayList<MountainInformation>();
		new AmbilDataJson().execute();
		//create an ArrayAdaptar from the String Array
		
		dataAdapter = new MyCustomAdapter(this,
		   R.layout.mountain_map_row, mList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            Context context = view.getContext();
	            String idGunung = String.valueOf( ((TextView)view.findViewById(R.id.textView1)).getText() );
	            
	            Intent in = new Intent(getApplicationContext(), MountainMapDetailActivity.class);
	            Bundle bun = new Bundle();					
	            bun.putString("idGunung", idGunung);					
	            in.putExtras(bun);
				
				// mulai activity baru dan dapatkan respon dengan result kode 100
	            startActivityForResult(in, 100);
	        }
	      });
	}

	private void displayMountain(){
	 //if the request was successful then notify the adapter to display the data
		 if(success){
			 dataAdapter.notifyDataSetChanged();
		 }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mountain_map, menu);
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
	
	
	//custom array adapter to display our custom row layout for the listview
	class MyCustomAdapter extends ArrayAdapter<MountainInformation> {
		
		 public MyCustomAdapter(Context context, int textViewResourceId, 
				 ArrayList<MountainInformation> mList) {
			 super(context, textViewResourceId, mList);
		 }
	
		private class ViewHolder {
			ImageView MountainPictSmall;
			TextView id;
			TextView nama;
		 }
	
		 @Override
		 public View getView(int position, View convertView, ViewGroup parent) {
		
			  ViewHolder holder = null;
			  if (convertView == null) {
			
			   LayoutInflater vi = (LayoutInflater)getSystemService(
			     Context.LAYOUT_INFLATER_SERVICE);
			   convertView = vi.inflate(R.layout.mountain_map_row, null);
			
			   holder = new ViewHolder();
			   holder.MountainPictSmall = (ImageView) convertView.findViewById(R.id.MountainPictSmall);
			   holder.id = (TextView) convertView.findViewById(R.id.textView1);
			   holder.nama = (TextView) convertView.findViewById(R.id.nama);
			   
			   convertView.setTag(holder);
		  }
		  else {
			  holder = (ViewHolder) convertView.getTag();
		  }
		
			  // Loader image - will be shown before loading image
			  ImageLoader imageLoader = ImageLoader.getInstance();
			  DisplayImageOptions options = new DisplayImageOptions.Builder()
			  	.cacheOnDisk(true)
			  	.cacheInMemory(true)
			    .showImageForEmptyUri(R.drawable.loader)
			    .showImageOnFail(R.drawable.loader)
			    .build();
			  ImageAware imageAware = new ImageViewAware(holder.MountainPictSmall, false);
			  
			  MountainInformation m = mList.get(position);
			  
			  holder.id.setText(String.valueOf( m.getId() ));
			  holder.nama.setText( m.getMountainName() );
			  holder.MountainPictSmall.setTag( m.getMountainPictSmall() );
			  
			  imageLoader.displayImage("http://sandra.byethost12.com/WebServiceMobile/"+m.getMountainPictSmall(), imageAware, options);
			  
			  return convertView;
		 }
		
	}
	
	class AmbilDataJson extends AsyncTask<String, String, String> {

		// inisialisasi url contact.php 
		private String url= "http://api-mobile.byethost12.com/gunung/getGunung";
		
		int total;
	   	
		/**
		 * sebelum memulai background thread tampilkan Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MountainMapActivity.this);
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
			
			// ambil JSON string dari URL
			JSONObject json = jParser.makeHttpRequest(url, "GET", params);
			
			// cek log cat untuk JSON reponse
			Log.d("params: ", json.toString());
			
			try {
				// mengecek untuk TAG SUKSES
				total = json.getInt(TAG_ROWS);
				
				if (total > 0) {
					// data ditemukan
					// mengambil  Array dari barang
					
					data = json.getJSONArray(TAG_DATA);
					
					// looping data semua member/anggota
					for (int i = 0; i < data.length(); i++) {
						JSONObject b = data.getJSONObject(i);

						// tempatkan setiap item json di variabel
						int id						= Integer.valueOf( b.getString("id") );
						String MountainName 		= b.getString("MountainName");
						String MountainPictSmall 	= b.getString("MountainPictSmall");

						mList.add(new MountainInformation(id,MountainName,MountainPictSmall, "", "", "", ""));
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
					
					if (total>1){	
						success=true;
						displayMountain();
					}
				}
			});
		}
	}//endjson
	
}
