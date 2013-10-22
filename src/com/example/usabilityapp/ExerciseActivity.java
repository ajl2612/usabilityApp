package com.example.usabilityapp;

import java.util.ArrayList;

import com.example.usabilityapp.R;
import Logic.ExerciseLogic.Exercise;
import Logic.ExerciseLogic.ExerciseDataSource;
import Logic.Main.MainDbHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ExerciseActivity extends Activity {
	Button exerciseButton;
	private ExerciseDataSource datasource;
	private MainDbHelper helper = new MainDbHelper(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        datasource = new ExerciseDataSource(this);
		datasource.open();
		ArrayList<Exercise> exercise_list = datasource.getAllExercises();
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
		Object o = lv1.getAdapter().getItem(position);
		Exercise exercise = (Exercise) o;
		datasource = new ExerciseDataSource(this);
		datasource.open();
		datasource.deleteExercise(exercise.getId());
		startActivity(getIntent());
		finish();
	}
	
}
