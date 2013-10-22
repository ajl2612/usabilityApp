package com.example.usabilityapp;

import java.util.ArrayList;

import Logic.ExerciseLogic.Exercise;
import Logic.ExerciseLogic.ExerciseDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class CreateWorkoutActivity extends Activity {
	
	private ExerciseDataSource eDatasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_workout);
        eDatasource = new ExerciseDataSource(this);
		eDatasource.open();
		ArrayList<Exercise> exercise_list = eDatasource.getAllExercises();
		final ListView lv1 = (ListView) findViewById(R.id.custom_workout_exercise_list);
		lv1.setAdapter(new CustomExerciseListAdapter(this,exercise_list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_workout, menu);
		return true;
	}

}
