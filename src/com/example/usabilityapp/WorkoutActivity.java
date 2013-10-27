package com.example.usabilityapp;


import java.util.ArrayList;

import Logic.ExerciseLogic.Exercise;
import Logic.Workout.Workout;
import Logic.Workout.WorkoutNameDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WorkoutActivity extends Activity {

	private WorkoutNameDataSource nDatasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Creating nDatasource");
        setContentView(R.layout.activity_workout);
        nDatasource = new WorkoutNameDataSource(this);
		nDatasource.open();
		System.out.println("Datasource has been opened");
		ArrayList<Workout> workout_list = nDatasource.getAllWorkouts();
		System.out.println("got all workouts and their names");
		final ListView lv1 = (ListView) findViewById(R.id.custom_workout_list);
		System.out.println("set up a custom exercise list");
		lv1.setAdapter(new CustomWorkoutListAdapter(this,workout_list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

	public void gotoCreateWorkout(View view){
		Intent intent = new Intent(this, CreateWorkoutActivity.class);
		startActivity(intent);
	}
}
