package Logic.ExerciseLogic;

import java.util.ArrayList;

import Logic.ExerciseLogic.ExerciseReaderContract.ExerciseEntry;
import Logic.Main.MainDbHelper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

public class ExerciseDataSource {

  // Database fields
	
  private SQLiteDatabase database;
  private MainDbHelper dbHelper;
  
  private String[] allColumns = { 
		 ExerciseEntry.EXERCISE_ID,
		 ExerciseEntry.EXERCISE_NAME,
		 ExerciseEntry.EXERCISE_TYPE
  };

  public ExerciseDataSource(Context context) {
    dbHelper = new MainDbHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  
  public Exercise createExercise(String name, String type ) {
    ContentValues values = new ContentValues();
    values.put(ExerciseEntry.EXERCISE_NAME, name);
    values.put(ExerciseEntry.EXERCISE_TYPE, type);
     
    long insertId = database.insert(ExerciseEntry.TABLE_NAME, null,
        values);
    
    Cursor cursor = database.query(ExerciseEntry.TABLE_NAME,
        allColumns, ExerciseEntry.EXERCISE_ID + " = " + insertId, null,
        null, null, null);
    
    cursor.moveToFirst();
    Exercise newExercise = cursorToExercise(cursor);
    cursor.close();
    return newExercise;
  }

  public Exercise updateExercise(long id,String name, String type){
	  ContentValues values = new ContentValues();
	    values.put(ExerciseEntry.EXERCISE_NAME, name);
	    values.put(ExerciseEntry.EXERCISE_TYPE, type);
	    
	  database.update(ExerciseEntry.TABLE_NAME, values, ExerciseEntry.EXERCISE_ID + " = " + id, null);
	  Cursor cursor = database.query(ExerciseEntry.TABLE_NAME,
	            allColumns, ExerciseEntry.EXERCISE_ID + " = " + id, null,
	            null, null, null);
	  
	  cursor.moveToFirst();
	  Exercise newExercise = cursorToExercise(cursor);
	  cursor.close();
	  return newExercise;
  }
  
  public void deleteExercise(long id) {
    database.delete(ExerciseEntry.TABLE_NAME, ExerciseEntry.EXERCISE_ID
        + " = " + id, null);
  }
  
  public Exercise getExercise(int id){
	    Cursor cursor = database.query(
	    		ExerciseEntry.TABLE_NAME, 
	    		allColumns, 
	    		ExerciseEntry.EXERCISE_ID + " = " + id, 
	    		null , 
	    		null , 
	    		null ,
	    		null, 
	    		"1");
	    cursor.moveToFirst();
	    Exercise exercise = cursorToExercise(cursor);
	    cursor.close();
	    return exercise;
  }
  
  public ArrayList<Exercise> getAllExercisesByIds(ArrayList<Integer> ids){
	    ArrayList<Exercise> exercises = new ArrayList<Exercise>();
	    for(int i: ids){
	    	exercises.add(getExercise(i));
	    }
	    return exercises;
  }

  public ArrayList<Exercise> getAllExercises() {
    ArrayList<Exercise> Exercises = new ArrayList<Exercise>();
    Cursor cursor = database.query(ExerciseEntry.TABLE_NAME,
        allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Exercise Exercise = cursorToExercise(cursor);
      Exercises.add(Exercise);
      cursor.moveToNext();
    }
    cursor.close();
    return Exercises;
  }
  
  private Exercise cursorToExercise(Cursor cursor) {
    Exercise Exercise = new Exercise();
    Exercise.setId(cursor.getInt(0));
    Exercise.setName(cursor.getString(1));
    Exercise.setType(cursor.getString(2));
    return Exercise;
  }
} 