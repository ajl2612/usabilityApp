package com.example.usabilityapp;

import java.util.ArrayList;
import java.util.Arrays;

import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseDataSource;
import Logic.Stats.BodyWeightStatsDataSource;
import Logic.Stats.FreeWeightStatsDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class GenerateStatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("In Create");
		setContentView(R.layout.activity_generate_stats);
		Intent intent = getIntent();
		int id = intent.getIntExtra("id", -1);
		// Show the Up button in the action bar
		
		ExerciseDataSource eDataSource = new ExerciseDataSource(this);
		eDataSource.open();
		Exercise e = eDataSource.getExercise(id);
		
		String type = e.getType();
		
		if(type.equals("Body Weight")){
			loadBodyWeightStats(this, e);
		}
		setupActionBar();		
	}
		
	public void loadBodyWeightStats(Context context, Exercise e){
		BodyWeightStatsDataSource bwDataSource = new BodyWeightStatsDataSource(context);
		bwDataSource.open();
		
		ArrayList<Pair<Integer, Integer>> stats = bwDataSource.getExerciseStats(e.getId());
		
		Number[] data = getBWStatArray(stats);
		if(data.length > 1){
			graph(data);
		}else{
			XYPlot mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
			mySimpleXYPlot.setVisibility(View.GONE);
			TextView warningMessage = (TextView) findViewById(R.id.warningMessage);
			warningMessage.setVisibility(View.VISIBLE);
		}
		
		int mean = getBWAverage(stats);
		int total = getBWTotal(stats);
				
		TextView nameView = (TextView)findViewById(R.id.exerciseName);
		nameView.setText(e.getName() + " Statistics");
		TextView meanView = (TextView)findViewById(R.id.exerciseMean);
		meanView.setText("Average Per Workout: " + mean);
		TextView totalView = (TextView)findViewById(R.id.exerciseTotal);
		totalView.setText("Total Completed: " + total);
		
		bwDataSource.close();
	}

	public void loadFreeWeightStats(Context context){
		FreeWeightStatsDataSource fwDataSource = new FreeWeightStatsDataSource(context);
		ExerciseDataSource eDataSource = new ExerciseDataSource(context);
		fwDataSource.open();
		eDataSource.open();
		
		Exercise e = eDataSource.getExercise(1);
		ArrayList<Pair<Integer, Integer>> stats = fwDataSource.getExerciseStats(1);
				
		Number[] data = getBWStatArray(stats);
		System.out.println(Arrays.toString(data));
		if(data.length > 1){
			graph(data);
		}else{
			XYPlot mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
			mySimpleXYPlot.setVisibility(View.GONE);
			
			TextView warningMessage = (TextView) findViewById(R.id.warningMessage);
			warningMessage.setVisibility(View.VISIBLE);
		}

		fwDataSource.close();
		eDataSource.close();
	}
	
	
	public void graph(Number[] data){
		XYPlot mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
		XYSeries series1 = new SimpleXYSeries(
				Arrays.asList(data),          // SimpleXYSeries takes a List so turn our array into a List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY // Y_VALS_ONLY means use the element index as the x value
				,""
				); // Set the display title of the series
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.rgb(255, 38, 38),                   // line color
				Color.rgb(255, 38, 38),                   // point color
				null, null);  
		mySimpleXYPlot.addSeries(series1, series1Format);
	    mySimpleXYPlot.getBackgroundPaint().setAlpha(0);
	    mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setAlpha(0);
	    mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setAlpha(0); 	
	}
	
	public Number[] getBWStatArray(ArrayList<Pair<Integer,Integer>> bwStats){
		Number[] stats = new Number[bwStats.size()];
		int i = 0;
		for(Pair<Integer,Integer> p : bwStats){
			stats[i] = p.second;
			i++;
		}
		return stats;
	}

	public int getBWAverage(ArrayList<Pair<Integer,Integer>> bwStats){
		int total = 0;
		int count = 0;
		for(Pair<Integer,Integer> p : bwStats){
			total += p.second;
			count += 1;
		}
		return total/count;
	}
	
	public int getBWTotal(ArrayList<Pair<Integer,Integer>> bwStats){
		int total = 0;
		for(Pair<Integer,Integer> p : bwStats){
			total += p.second;
		}	
		return total;
	}
	
	
	
	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
