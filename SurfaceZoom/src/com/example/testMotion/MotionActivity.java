package com.example.testMotion;

import android.app.Activity;
import android.os.Bundle;

public class MotionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(new TouchExampleView(getApplicationContext()));
	}
}
