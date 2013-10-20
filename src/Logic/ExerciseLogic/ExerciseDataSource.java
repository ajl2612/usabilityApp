package Logic.ExerciseLogic;

import java.util.ArrayList;



import Logic.ExerciseLogic.ExerciseReaderContract.ExerciseEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

public class ExerciseDataSource {

  // Database fields
	
  private SQLiteDatabase database;
  private ExerciseDbHelper dbHelper;
  
  private String[] allColumns = { 
		 ExerciseEntry._ID,
		 ExerciseEntry.COLUMN_NAME_EXERCISE_NAME,
		 ExerciseEntry.COLUMN_NAME_TYPE
  };

  public ExerciseDataSource(Context context) {
    dbHelper = new ExerciseDbHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Exercise createExercise(String name, String type ) {
    ContentValues values = new ContentValues();
    values.put(ExerciseEntry.COLUMN_NAME_EXERCISE_NAME, name);
    values.put(ExerciseEntry.COLUMN_NAME_TYPE, type);
    
    long insertId = database.insert(ExerciseEntry.TABLE_NAME, null,
        values);
    
    Cursor cursor = database.query(ExerciseEntry.TABLE_NAME,
        allColumns, ExerciseEntry.COLUMN_NAME_EXERCISE_ID + " = " + insertId, null,
        null, null, null);
    
    cursor.moveToFirst();
    Exercise newExercise = cursorToExercise(cursor);
    cursor.close();
    return newExercise;
  }

  public Exercise updateExercise(long id,String name, String type){
	  ContentValues values = new ContentValues();
	    values.put(ExerciseEntry.COLUMN_NAME_EXERCISE_NAME, name);
	    values.put(ExerciseEntry.COLUMN_NAME_TYPE, type);
	    
	  database.update(ExerciseEntry.TABLE_NAME, values, ExerciseEntry.COLUMN_NAME_EXERCISE_ID + " = " + id, null);
	  Cursor cursor = database.query(ExerciseEntry.TABLE_NAME,
	            allColumns, ExerciseEntry.COLUMN_NAME_EXERCISE_ID + " = " + id, null,
	            null, null, null);
	  
	  cursor.moveToFirst();
	  Exercise newExercise = cursorToExercise(cursor);
	  cursor.close();
	  return newExercise;
  }
  
  public void deleteExercise(long id) {
    database.delete(ExerciseEntry.TABLE_NAME, ExerciseEntry.COLUMN_NAME_EXERCISE_ID
        + " = " + id, null);
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
    Exercise.setId(cursor.getLong(0));
    Exercise.setName(cursor.getString(1));
    Exercise.setType(cursor.getString(2));
    return Exercise;
  }
} 