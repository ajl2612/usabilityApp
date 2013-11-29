package com.example.usabilityapp;

import java.util.ArrayList;

import com.example.usabilityapp.R;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseDataSource;
import Logic.Main.MainDbHelper;
import Logic.Workout.WorkoutExerciseDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ExerciseActivity extends Activity {
	Button exerciseButton;
	private ExerciseDataSource eDatasource;
	private WorkoutExerciseDataSource wDatasource;
	private MainDbHelper helper = new MainDbHelper(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        eDatasource = new ExerciseDataSource(this);
		eDatasource.open();
		ArrayList<Exercise> exercise_list = eDatasource.getAllExercises();
		final ListView lv1 = (ListView) findViewById(R.id.custom_exercise_list);
		lv1.setAdapter(new CustomExerciseListAdapter(this,exercise_list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exercise, menu);
        return true;
    }
    
    public void gotoCreateExercise(View view){
		Intent intent = new Intent(this, CreateExerciseActivity.class);
		startActivity(intent);
	}
    
	public void deleteExercise(View view){
		final ListView lv1 = (ListView) findViewById(R.id.custom_exercise_list);
		final int position = lv1.getPositionForView((View) view.getParent());
		wDatasource = new WorkoutExerciseDataSource(this);
		wDatasource.open();
		
		Object o = lv1.getAdapter().getItem(position);
		Exercise exercise = (Exercise) o;
		if(!wDatasource.isExerciseUsed(exercise.getId())){
			eDatasource = new ExerciseDataSource(this);
			eDatasource.open();				
			eDatasource.deleteExercise(exercise.getId());
			startActivity(getIntent());
			finish();
		}else{
			DeleteErrorPopIt("Cannot Delete Exercise", "This exercise is being used by one or more exercises and cannot be deleted.");
		}
	}
	
	// Not to be confused with the popular toy "Bop-It"
	// This is just like a js alert or pop-up.
	public void DeleteErrorPopIt( String title, String message ){
	        new AlertDialog.Builder(this)
	        .setTitle( title )
	        .setMessage( message )
	        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        }
	    }).show();
	}
}
