package com.cmpe.serviceapp;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class DownloadFile extends AsyncTask<Void, Void, Void>{

	private URL urls[];
	private DownloadService ds;
	private Notification.Builder builder;
	private NotificationManager nm;
	private Activity name;
	
	public DownloadFile(URL[] urls,DownloadService ds,Activity name) {
		this.urls = urls;
		this.ds = ds;
		this.name = name;
	}
	
	@Override
	protected void onPreExecute() {
		nm = (NotificationManager) ds.getSystemService(
										Context.NOTIFICATION_SERVICE);
		builder = new Notification.Builder(ds)
						.setSmallIcon(android.R.drawable.stat_sys_download)
						.setContentTitle("Downloading PDFs")
						.setContentText("");
		nm.notify(0, builder.build());
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		for (int i = 0; i < urls.length; i++) {
			builder.setContentText("File " + (i + 1) + " of " + urls.length);
			nm.notify(0, builder.build());
			URLConnection connection;
			try {
				connection = urls[i].openConnection();
				connection.connect();			
				String[] str = urls[i].toString().split("/");
				Log.i("Activity Name ---- File Name","" + name.getLocalClassName() +"---" +  str[str.length - 1]);
				// download the file
		        InputStream input = new BufferedInputStream(urls[i].openStream());
		        OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + str[str.length - 1]);
				byte data[] = new byte[1024];
	            int count;
	            while ((count = input.read(data)) != -1) {
	                output.write(data, 0, count);
	            }
	            output.flush();
	            output.close();
	            input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 return null;       
	}
	
	@Override
	protected void onPostExecute(Void result) {
		builder.setSmallIcon(android.R.drawable.stat_sys_download_done);
		builder.setContentTitle("Download Complete!");
		builder.setContentText("All files downloaded successfully");
		nm.notify(0, builder.build());
	}

}
