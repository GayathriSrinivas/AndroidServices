package com.cmpe.serviceapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cmpe.serviceapp.DownloadService.LocalBinder;

public class DownloadPdfActivity extends Activity implements OnClickListener{

	private DownloadService downloadService;
	private boolean isBound = false;
	private Button btn_download;
	private Intent downloadIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Activity onCreate","On Create...");
        setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.pdf_download);
		btn_download = (Button)findViewById(R.id.btn_download);
		btn_download.setOnClickListener(this);
		getActionBar().setTitle("PDF Download Activity");
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		//Bind To Download Service
		downloadIntent = new Intent(this,DownloadService.class);
		Log.i("Activity onStart","Call Before bindService");
		bindService(downloadIntent, connection, Context.BIND_AUTO_CREATE);
		Log.i("Activity onStart","Call After bindService");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//unbound from the Download Service
		if(isBound){
			Log.i("Activity onDestroy","Call After bindService");
			unbindService(connection);
			isBound = false;
		}
	}
	
	@Override
	public void onClick(View v) {
		if(isBound){
			Log.i("Activity onClick","Calling Service Method");
			downloadService.downloadPdf(this);
			finish();
		}
	}

	private ServiceConnection connection = new ServiceConnection(){
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder)service;
			downloadService = binder.getInstance();  
			isBound = true;
			Log.i("Activity onServiceConnected","Service is Bound");
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			isBound = false;
			Log.i("Activity onServiceDisconnected","Service is UnBound");
		}	
	};
}
