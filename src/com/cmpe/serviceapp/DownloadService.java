package com.cmpe.serviceapp;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service{

	private IBinder mBinder = new LocalBinder();
	private URL urls[] = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("Service ","onBind");
		return mBinder;
	}

	public class LocalBinder extends Binder{
		public DownloadService getInstance(){
			return DownloadService.this;
		}
	}

	public void downloadPdf(Activity activityName) {
		Log.i("Service","Downloading File");
	
		try {
			urls = new URL[] {
			new URL("http://www.sjsu.edu/gape/docs/award_degree.pdf"),
			new URL("http://www.sjsu.edu/faculty/gerstman/StatPrimer/correlation.pdf"),
			new URL("http://www.sjsu.edu/gradstudies/docs/thesis_guidelines.pdf"),
			new URL("http://www.sjsu.edu/faculty/gerstman/StatPrimer/t-table.pdf"),
			new URL("http://www.sjsu.edu/gape/docs/course_substitution.pdf")};
		} catch (MalformedURLException e){
				e.printStackTrace();
		}
		new DownloadFile(urls,this,activityName).execute();
	}
	
	public void downloadImage(Activity activityName){
		try {
			urls = new URL[] {
			new URL("http://s3.sjsu.edu/large_SJSU.jpg"),
			new URL("http://www.sjsu.edu/academicscheduling/pics/towerhall_towerlawn_web.jpg"),
			new URL("http://www.sjsu.edu/sjsuhome/pics/statues-02.jpg")};
		} catch (MalformedURLException e){
				e.printStackTrace();
		}
		new DownloadFile(urls,this,activityName).execute();
	}
	
	public void downloadText(Activity activityName){
		try {
			urls = new URL[] {
			new URL("http://wordpress.org/plugins/about/readme.txt"),
			new URL("https://github.com/esromneb/sjsu-cs267/blob/master/c/web-crawler-install.txt"),
			new URL("http://www.horstmann.com/sjsu/spring2012/cs46b/lab1/deptdir.txt"),
			new URL("http://slisweb.sjsu.edu/rss/2006/0114.txt"),
			new URL("http://www.math.sjsu.edu/camcos/appl.txt")};
		} catch (MalformedURLException e){
				e.printStackTrace();
		}
		new DownloadFile(urls,this,activityName).execute();
	}
}




