package com.cmpe.serviceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btn_pdf;
	private Button btn_image;
	private Button btn_text;
	private Button btn_close;
	private Intent pdfIntent;
	private Intent imageIntent;
	private Intent textIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.activity_main);
		btn_pdf =(Button)findViewById(R.id.btn_pdf);
		btn_image =(Button)findViewById(R.id.btn_image);
		btn_text =(Button)findViewById(R.id.btn_text);
		btn_close =(Button)findViewById(R.id.btn_close);
		btn_pdf.setOnClickListener(this);
		btn_image.setOnClickListener(this);
		btn_text.setOnClickListener(this);
		btn_close.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_pdf:
			pdfIntent = new Intent(this,DownloadPdfActivity.class);
			startActivity(pdfIntent);
			break;
		case R.id.btn_image:
			imageIntent = new Intent(this,DownloadImageActivity.class);
			startActivity(imageIntent);			
			break;
		case R.id.btn_text:
			textIntent = new Intent(this,DownloadTextActivity.class);
			startActivity(textIntent);			
			break;
		case R.id.btn_close:
			finish();
			break;			
		default:
			break;
		}
		
	}

}
