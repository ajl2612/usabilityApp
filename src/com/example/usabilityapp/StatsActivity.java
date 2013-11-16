package com.example.usabilityapp;

import java.util.ArrayList;

import Logic.ExerciseLogic.Exercise;
import Logic.ExerciseLogic.ExerciseDataSource;
import Logic.Stats.BodyWeightStatsDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class StatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		// Show the Up button in the action bar.
		loadBodyWeightStats(this);
		setupActionBar();	
	}

	
	public void loadBodyWeightStats(Context context){
		BodyWeightStatsDataSource bwDataSource = new BodyWeightStatsDataSource(context);
		ExerciseDataSource eDataSource = new ExerciseDataSource(context);
		bwDataSource.open();
		eDataSource.open();
		Exercise e;
		ArrayList<Pair<Integer, Integer>> stats = bwDataSource.getExerciseStats(1);
		
		for(Pair<Integer,Integer> p : stats){
			e = eDataSource.getExercise(p.first);
			System.out.println("EXERCISE: "+e.getName() + " REPS: " + p.second);
		}
		bwDataSource.close();
		eDataSource.close();
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
