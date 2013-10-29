package com.example.usabilityapp;

import Logic.ExerciseLogic.Exercise;
import Logic.ExerciseLogic.ExerciseDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateExerciseActivity extends Activity {
	private ExerciseDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_exercise);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_exercise, menu);
		return true;
	}
	
	public void createExercise(View view){
		EditText nameText = (EditText)findViewById(R.id.createNameField);
		Spinner typeSpinner = (Spinner)findViewById(R.id.createTypeField);
		
		String name = nameText.getText().toString();
		String type = String.valueOf(typeSpinner.getSelectedItem());
		Exercise exercise;
		
		if(!name.equals("") && !type.equals("")){
			datasource = new ExerciseDataSource(this);
			datasource.open();
			exercise = datasource.createExercise(name, type);
		
			if(exercise != null){
				Intent intent = new Intent(this, ExerciseActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		}	
	}

}
