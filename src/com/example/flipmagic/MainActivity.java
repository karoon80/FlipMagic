package com.example.flipmagic;

import java.util.Random;

import com.example.flipmagic.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	SensorManager mngr;
	Sensor accel;
	public final static String LUCK = "Luck Message";
	public static int random = 0;
	Intent luck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Retrieve the version name and display it
				PackageInfo pInfo = null;
				  try {
				   pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
				  } catch (NameNotFoundException e) {
				   e.printStackTrace();
				  }
				  String version = pInfo.versionName;
				  
				  TextView versionText = (TextView)findViewById(R.id.version_name);
				  versionText.setText("v."+version);
				  
				  RelativeLayout layout = (RelativeLayout)findViewById(R.id.main_layout);
				  layout.setBackgroundColor(Color.parseColor("#9400D3"));
				  
				  TextView text = (TextView)findViewById(R.id.editText1);
				  text.setTextColor(Color.parseColor("#FFFFFF"));
				  
				  //Sensor manager instance
				  mngr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
				  accel = mngr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				  mngr.registerListener(listener, accel, SensorManager.SENSOR_DELAY_NORMAL);
				  luck = new Intent(this,DisplayAnswerActivity.class);
	}
	
	//Sensor Event Listener:
	SensorEventListener listener = new SensorEventListener(){
	public void onSensorChanged(SensorEvent event){
		  float filteredAccel = event.values[2];
		  Log.d("ZValue", Float.toString(filteredAccel));
		  Log.d("ZOLDVALUE", Float.toString(event.values[2]));
		  
		  if (filteredAccel < 0){
			 
			 String [] listOfLucks = {"It is certain","It is decidedly so","Without a doubt","Yes definitely","You may rely on it",
					  "As I see it yes","Most likely","Outlook good","Yes","Signs point to yes","Reply hazy try again","Ask again later"
					  ,"Better not tell you now","Cannot predict now","Concentrate and ask again","Don't count on it","My reply is no","My sources say no",
					  "Outlook not so good","Very doubtful"};
			  Random randomGenerator = new Random();
			  random = randomGenerator.nextInt(20);
			  luck.putExtra(LUCK, listOfLucks[random]);
			  startActivity(luck);
		  }
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy){
	
	}
	};
	
	 protected void onResume() {
		    super.onResume();
		    mngr.registerListener(listener, accel, SensorManager.SENSOR_DELAY_NORMAL);
		  }

		  @Override
		  protected void onPause() {
		    super.onPause();
		    mngr.unregisterListener(listener);
		  }
		  
}
