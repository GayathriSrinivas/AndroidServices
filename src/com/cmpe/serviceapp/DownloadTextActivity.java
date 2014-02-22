package com.cmpe.serviceapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cmpe.serviceapp.DownloadService.LocalBinder;

public class DownloadTextActivity extends Activity implements OnClickListener{
	
	private DownloadService downloadService;
	private boolean isBound = false;
	private Button btn_text;
	private Intent downloadIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.text_download);
		btn_text = (Button)findViewById(R.id.btn_text);
		btn_text.setOnClickListener(this);
		getActionBar().setTitle("Text Download Activity");
	}

	
	@Override
	protected void onStart() {
		super.onStart();
		//Bind To Download Service
		downloadIntent = new Intent(this,DownloadService.class);
		bindService(downloadIntent, connection, Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//unbound from the Download Service
		if(isBound){
			unbindService(connection);
			isBound = false;
		}
	}
	
	@Override
	public void onClick(View v) {
		if(isBound){
			downloadService.downloadText(this);
			finish();
		}
	}

	private ServiceConnection connection = new ServiceConnection(){
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder)service;
			downloadService = binder.getInstance();  
			isBound = true;
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			isBound = false;
		}	
	};
}
