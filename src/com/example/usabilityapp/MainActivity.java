package com.example.usabilityapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void gotoExercise(View view) {
	    Intent intent = new Intent(this, ExerciseActivity.class);
	    startActivity(intent);
	}
	
	public void gotoGoals(View view){
		Intent intent = new Intent(this, GoalsActivity.class);
		startActivity(intent);
	}
	
	public void gotoSettings(View view) {
	    Intent intent = new Intent(this, SettingsActivity.class);
	    startActivity(intent);
	}
	
	public void gotoSchedule(View view){
		Intent intent = new Intent(this, ScheduleActivity.class);
		startActivity(intent);
	}
	public void gotoStats(View view){
		Intent intent = new Intent(this, StatsActivity.class);
		startActivity(intent);
	}
	public void gotoWorkout(View view){
		Intent intent = new Intent(this, WorkoutActivity.class);
		startActivity(intent);
	}

}
