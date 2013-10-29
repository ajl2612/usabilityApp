package Logic.Workout;

import java.util.ArrayList;
import Logic.Main.MainDbHelper;
import Logic.Workout.WorkoutExerciseReaderContract.WorkoutExerciseEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

public class WorkoutExerciseDataSource {

  // Database fields
	
  private SQLiteDatabase database;
  private MainDbHelper dbHelper;
  
  private String[] allColumns = { 
		WorkoutExerciseEntry.WORKOUT_EXERCISE_ENTRY_ID,
		WorkoutExerciseEntry.WORKOUT_ID,
		WorkoutExerciseEntry.WORKOUT_EXERCISE_ID
  };

  public WorkoutExerciseDataSource(Context context) {
    dbHelper = new MainDbHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  

  public long addExercise(int workoutId, int exerciseId) {
    ContentValues values = new ContentValues();
    
    values.put(WorkoutExerciseEntry.WORKOUT_ID, workoutId);
    values.put(WorkoutExerciseEntry.WORKOUT_EXERCISE_ID, exerciseId);
    
    long insertId = database.insert(WorkoutExerciseEntry.TABLE_NAME, null,
        values);
    
    Cursor cursor = database.query(WorkoutExerciseEntry.TABLE_NAME,
        allColumns, WorkoutExerciseEntry.WORKOUT_EXERCISE_ENTRY_ID + " = " + insertId, null,
        null, null, null);
    
    cursor.moveToFirst();
    cursor.close();
    return insertId;
  }
  
  public void deleteWorkout(long id) {
    database.delete(WorkoutExerciseEntry.TABLE_NAME, WorkoutExerciseEntry.WORKOUT_ID
        + " = " + id, null);
  }
  
  public Boolean isExerciseUsed(int id){
	    ArrayList<Integer> ids = new ArrayList<Integer>();
	    Cursor cursor = database.query(WorkoutExerciseEntry.TABLE_NAME,
	        allColumns, WorkoutExerciseEntry.WORKOUT_EXERCISE_ID + " = " + id, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      ids.add(cursor.getInt(0));
	      cursor.moveToNext();
	    }
	    cursor.close();
	    if(ids.size() > 0){
	    	return true;
	    }
	    return false;
  }
  
  /*Gets all the exercises for a workout */
  public ArrayList<Integer> getAllExerciseIds(int workoutId) {
    ArrayList<Integer> ids = new ArrayList<Integer>();
    Cursor cursor = database.query(WorkoutExerciseEntry.TABLE_NAME,
        allColumns, WorkoutExerciseEntry.WORKOUT_ID + " = " + workoutId, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      ids.add(cursor.getInt(0));
      cursor.moveToNext();
    }
    cursor.close();
    return ids;
  }
  
  
} 