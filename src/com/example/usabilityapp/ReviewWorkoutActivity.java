package com.example.usabilityapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseDataSource;
import Logic.Stats.BodyWeightStatsDataSource;
import Logic.Stats.FreeWeightStatsDataSource;
import Logic.Stats.MachineWeightStatsDataSource;
import Logic.Workout.Workout;
import Logic.Workout.WorkoutExerciseDataSource;
import Logic.Workout.WorkoutNameDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ReviewWorkoutActivity extends Activity {

	private Boolean timing = false;
	private ExerciseDataSource eDatasource;
	private WorkoutExerciseDataSource wDatasource;	
	private ArrayList<Exercise> selectedExercises = new ArrayList<Exercise>();	
	private ArrayList<Exercise> allExercises;
	//private Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_review_workout);
		TextView title = (TextView) findViewById(R.id.review_workout_title);
		Intent intent = getIntent();
		id = intent.getIntExtra("id", -1);
		String name = intent.getStringExtra("name");
		title.setText(name);
		
		System.out.println("Workout id: "+ id);
		
		if(id > 0){
			eDatasource = new ExerciseDataSource(this);
	        wDatasource = new WorkoutExerciseDataSource(this);
			eDatasource.open();
			wDatasource.open();
			
			ArrayList<Integer> allExerciseIds = wDatasource.getAllExerciseIds(id);
			allExercises = eDatasource.getAllExercisesByIds(allExerciseIds);
			final ListView lv1 = (ListView) findViewById(R.id.review_workout_exercise_list);
			lv1.setAdapter(new CustomExerciseCheckListAdapter(this,allExercises));
			lv1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

					Exercise selected = (Exercise) (lv1.getItemAtPosition(arg2));	   
					CustomExerciseCheckListAdapter.ViewHolder holder;
		            holder = (CustomExerciseCheckListAdapter.ViewHolder) arg1.getTag();
		           
		            if(!holder.nameView.isChecked() ){
		            	holder.nameView.setChecked(false);  
		            	generatePopIt(selected);
		            	arg1.setBackgroundColor(Color.BLACK);
		            }
			}}); 
		}
	}

	public void doWorkout(View view){
		Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
		if(!timing){
			chronometer.start();
			view.setBackgroundColor(Color.RED);
			timing = true;
		}else{
			chronometer.stop();
			view.setBackgroundColor(Color.GREEN);
			
			timing = false;
		}
	}
	
	public void generatePopIt(Exercise e){
		String type = e.getType();
		
		if(type.equals("Aerobic")){
			AerobicPopIt(e);
		}
		
		else if(type.equals("Body Weight")){
			BodyWeightPopIt(e);
		}
		
		else if(type.equals("Free Weight")){
			FreeWeightPopIt(e);
		}
		
		else if(type.equals("Weight Machine")){
			WeightMachinePopIt(e);
		}
		
		else if(type.equals("Stretch")){
			StretchPopIt(e);
		}
		
		else{
			ErrorPopIt();
		}
	}
	
	public void AerobicPopIt(Exercise e){
		final Dialog d = new Dialog(this);
		d.setTitle(e.getName());
		d.setContentView(R.layout.aerobic_dialog_layout);
		Button b1 = (Button)d.findViewById(R.id.aerobicOk);
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
     		EditText distField = (EditText) findViewById(R.id.distanceText);
    		EditText timeField = (EditText) findViewById(R.id.timeText);
    		//double dist = Double.parseDouble(distField.getText().toString());
    		//double time = Double.parseDouble(distField.getText().toString());
    		//This is where the db call would go.
    		d.dismiss();
          }    
         });
		d.show();
	}
	
	public void BodyWeightPopIt(final Exercise e){

		final Dialog d = new Dialog(this);
		d.setTitle(e.getName());
		d.setContentView(R.layout.body_weight_dialog_layout);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.repNumberPicker);
		Button b1 = (Button)d.findViewById(R.id.bodyWeightOk);
		np.setMaxValue(100);
		np.setMinValue(0);
		np.setWrapSelectorWheel(false);
		
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
            Integer reps = Integer.parseInt(String.valueOf(np.getValue())); 
     		BodyWeightStatsDataSource bwDataSource = new BodyWeightStatsDataSource(getBaseContext());
     		bwDataSource.open();
     		bwDataSource.addStat(e.getId(), reps);
     		bwDataSource.close();
            d.dismiss();
          }    
         });
		d.show();
	}

	public void FreeWeightPopIt(final Exercise e){
		System.out.println("Error in Free Weight");
		final Dialog d = new Dialog(this);
		d.setTitle(e.getName());
		d.setContentView(R.layout.free_weight_dialog_layout);
		final NumberPicker wNP = (NumberPicker) d.findViewById(R.id.weightNumberPicker);
		final NumberPicker rNP = (NumberPicker) d.findViewById(R.id.repNumberPicker);
		Button b1 = (Button)d.findViewById(R.id.bodyWeightOk);
		wNP.setMaxValue(300);
		wNP.setMinValue(0);
		rNP.setMaxValue(100);
		rNP.setMinValue(0);	
		wNP.setWrapSelectorWheel(false);
		rNP.setWrapSelectorWheel(false);
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
            Integer weight = Integer.parseInt(String.valueOf(wNP.getValue())); 
            Integer reps = Integer.parseInt(String.valueOf(rNP.getValue())); 
      		FreeWeightStatsDataSource fwDataSource = new FreeWeightStatsDataSource(getBaseContext());
      		fwDataSource.open();
      		fwDataSource.addStat(e.getId(), reps, weight);
      		fwDataSource.close();
            d.dismiss();
          }    
         });
		d.show();
	}
	
	public void WeightMachinePopIt(final Exercise e){
		final Dialog d = new Dialog(this);
		d.setTitle(e.getName());
		d.setContentView(R.layout.free_weight_dialog_layout); // Essentially they're the same thing.
		final NumberPicker wNP = (NumberPicker) d.findViewById(R.id.weightNumberPicker);
		final NumberPicker rNP = (NumberPicker) d.findViewById(R.id.repNumberPicker);
		Button b1 = (Button)d.findViewById(R.id.bodyWeightOk);
		wNP.setMaxValue(300);
		wNP.setMinValue(0);
		rNP.setMaxValue(100);
		rNP.setMinValue(0);	
		wNP.setWrapSelectorWheel(false);
		rNP.setWrapSelectorWheel(false);
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
            Integer weight = Integer.parseInt(String.valueOf(wNP.getValue())); 
            Integer reps = Integer.parseInt(String.valueOf(rNP.getValue())); 
       		MachineWeightStatsDataSource mwDataSource = new MachineWeightStatsDataSource(getBaseContext());
       		mwDataSource.open();
       		mwDataSource.addStat(e.getId(), reps, weight);
       		mwDataSource.close();
            d.dismiss();
          }    
         });
		d.show();
	}
	
	public void StretchPopIt(Exercise e){
		final Dialog d = new Dialog(this);
		d.setTitle(e.getName());
		d.setContentView(R.layout.stretch_dialog_layout);
		Button b1 = (Button)d.findViewById(R.id.stretchOk);
        b1.setOnClickListener(new OnClickListener()
        {
         @Override
         public void onClick(View v) {
        	 CheckBox stretchCB = (CheckBox)findViewById(R.id.stretchCB);
    		//This is where the db call would go.
        	 d.dismiss();
          }    
         });
		d.show();
	}
	
	public void ErrorPopIt(){
        new AlertDialog.Builder(this)
        .setTitle( "Error" )
        .setMessage( "Something didn't go quite right." )
        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface arg0, int arg1) {
        
        }}).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_workout, menu);
		return true;
	}
	
}
