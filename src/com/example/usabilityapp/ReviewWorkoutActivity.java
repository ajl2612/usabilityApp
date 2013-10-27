package com.example.usabilityapp;

import java.util.ArrayList;

import Logic.ExerciseLogic.Exercise;
import Logic.ExerciseLogic.ExerciseDataSource;
import Logic.Workout.Workout;
import Logic.Workout.WorkoutExerciseDataSource;
import Logic.Workout.WorkoutNameDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ReviewWorkoutActivity extends Activity {

	private ArrayList<Exercise> selectedExercises = new ArrayList<Exercise>();	
	private ExerciseDataSource eDatasource;
	private WorkoutNameDataSource nDatasource;
	private WorkoutExerciseDataSource wDatasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_workout);
		TextView title = (TextView) findViewById(R.id.review_workout_title);
		
		ArrayList<Exercise> allExercises = eDatasource.getAllExercises();
		final ListView lv1 = (ListView) findViewById(R.id.review_workout_exercise_list);
		lv1.setAdapter(new CustomExerciseListAdapter(this,allExercises));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_workout, menu);
		return true;
	}
	
	public void createWorkout(View view){
		EditText nameText = (EditText)findViewById(R.id.workoutTitleText);		
		String name = nameText.getText().toString();
		
		if(!name.equals("") && selectedExercises.size() > 0){
			nDatasource = new WorkoutNameDataSource(this);
			wDatasource = new WorkoutExerciseDataSource(this);
			nDatasource.open();
			wDatasource.open();
			
			Workout workout = nDatasource.createWorkout(name);
			
			for(Exercise e: selectedExercises){
				wDatasource.addExercise(workout.getId(), e.getId());
			}
		}

	}

}
