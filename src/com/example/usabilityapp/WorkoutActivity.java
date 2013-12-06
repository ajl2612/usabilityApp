package com.example.usabilityapp;


import java.util.ArrayList;

import Logic.Workout.Workout;
import Logic.Workout.WorkoutExerciseDataSource;
import Logic.Workout.WorkoutNameDataSource;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WorkoutActivity extends Activity {

	private WorkoutNameDataSource nDatasource;
	private WorkoutExerciseDataSource eDatasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        nDatasource = new WorkoutNameDataSource(this);
		nDatasource.open();
		ArrayList<Workout> workout_list = nDatasource.getAllWorkouts();
		final ListView lv1 = (ListView) findViewById(R.id.custom_workout_list);
		lv1.setAdapter(new CustomWorkoutListAdapter(this,workout_list));
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					Workout selected = (Workout) (lv1.getItemAtPosition(arg2));	 
					Intent intent = new Intent(arg1.getContext(), ReviewWorkoutActivity.class);
					intent.putExtra("name", selected.getName());
					intent.putExtra("id", selected.getId());
					startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

	public void gotoCreateWorkout(View view){
		Intent intent = new Intent(this, CreateWorkoutActivity.class);
		startActivity(intent);
	}
	
	public void deleteWorkout(View view){
		eDatasource = new WorkoutExerciseDataSource(this);
		nDatasource = new WorkoutNameDataSource(this);
		eDatasource.open();
		nDatasource.open();
		
		final ListView lv1 = (ListView) findViewById(R.id.custom_workout_list);
		final int position = lv1.getPositionForView((View) view.getParent());
		Object o = lv1.getAdapter().getItem(position);
		Workout workout = (Workout) o;
		int wId = workout.getId();
		
		eDatasource.deleteWorkout(wId);
		nDatasource.deleteWorkout(wId);
	
		startActivity(getIntent());
		finish();
	}


}
