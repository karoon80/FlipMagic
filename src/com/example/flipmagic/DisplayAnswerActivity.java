package com.example.flipmagic;

import com.example.flipmagic.MainActivity;
import com.example.flipmagic.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayAnswerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_answer);
		
		TextView goBack = (TextView)findViewById(R.id.editText1);
		goBack.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.answer_layout);
		layout.setBackgroundColor(Color.parseColor("#9400D3"));
		
		//Start new activity by retrieving info from an intent
		Intent luck = getIntent();
		String luckMessage = luck.getStringExtra(MainActivity.LUCK);
		//Display your luck from the answers list
		TextView displayLuck = (TextView)findViewById(R.id.luck_message);
		displayLuck.setText(luckMessage);
		displayLuck.setBackgroundColor(Color.parseColor("#C0C0C0"));
		int index = MainActivity.random;
		if (index >= 0 && index <= 9)
			displayLuck.setTextColor(Color.parseColor("#228B22"));
		else if (index >= 10 && index <= 14)
			displayLuck.setTextColor(Color.parseColor("#FFFF00"));
		else
			displayLuck.setTextColor(Color.parseColor("#FF0000"));
	}
	
	 @Override
	  public void onBackPressed(){
		  Intent finish = new Intent(this,MainActivity.class);
		  startActivity(finish);
		  finish();
	  }
}
