package com.example.usabilityapp;

import java.util.ArrayList;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseDataSource;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainStatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_stats);
		ExerciseDataSource eDatasource= new ExerciseDataSource(this);
		eDatasource.open();
		
		ArrayList<Exercise> exercise_list = eDatasource.getAllExercises();
		final ListView lv1 = (ListView) findViewById(R.id.custom_exercise_list);
		lv1.setAdapter(new CustomExerciseListAdapter(this, exercise_list));
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("Selected");
				Exercise selected = (Exercise) (lv1.getItemAtPosition(arg2));	 
					Intent intent = new Intent(arg1.getContext(), GenerateStatsActivity.class);
					intent.putExtra("name", selected.getName());
					intent.putExtra("id", selected.getId());
					System.out.println("In on cllick");
					startActivity(intent);
			}
		});
		// Show the Up button in the action bar.
		setupActionBar();
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
		// getMenuInflater().inflate(R.menu.main_stats, menu);
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
