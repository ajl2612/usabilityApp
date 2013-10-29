package Logic.Workout;

import java.util.ArrayList;
import Logic.Main.MainDbHelper;
import Logic.Workout.WorkoutNameReaderContract.WorkoutNameEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

public class WorkoutNameDataSource {

  // Database fields
	
  private SQLiteDatabase database;
  private MainDbHelper dbHelper;
  
  private String[] allColumns = { 
		 WorkoutNameEntry.WORKOUT_ID,
		 WorkoutNameEntry.WORKOUT_NAME
  };

  public WorkoutNameDataSource(Context context) {
    dbHelper = new MainDbHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  

  public Workout createWorkout(String name) {
    ContentValues values = new ContentValues();
    
    //values.put(WorkoutNameEntry.WORKOUT_NAME, name);
    
    values.put(WorkoutNameEntry.WORKOUT_NAME, name);
     
    long insertId = database.insert(WorkoutNameEntry.TABLE_NAME, null,
        values);
    
    Cursor cursor = database.query(WorkoutNameEntry.TABLE_NAME,
        allColumns, WorkoutNameEntry.WORKOUT_ID + " = " + insertId, null,
        null, null, null);
    
    cursor.moveToFirst();
    Workout newWorkout = cursorToWorkout(cursor);
    cursor.close();
    return newWorkout;
  }

  public Workout updateWorkout(long id,String name, String type){
	  ContentValues values = new ContentValues();
	    values.put(WorkoutNameEntry.WORKOUT_NAME, name);
	    
	  database.update(WorkoutNameEntry.TABLE_NAME, values, WorkoutNameEntry.WORKOUT_ID + " = " + id, null);
	  Cursor cursor = database.query(WorkoutNameEntry.TABLE_NAME,
	            allColumns, WorkoutNameEntry.WORKOUT_ID + " = " + id, null,
	            null, null, null);
	  
	  cursor.moveToFirst();
	  Workout newWorkout = cursorToWorkout(cursor);
	  cursor.close();
	  return newWorkout;
  }
  
  public void deleteWorkout(long id) {
    database.delete(WorkoutNameEntry.TABLE_NAME, WorkoutNameEntry.WORKOUT_ID
        + " = " + id, null);
  }
  
  public ArrayList<Workout> getAllWorkouts() {
    ArrayList<Workout> Workouts = new ArrayList<Workout>();
    Cursor cursor = database.query(WorkoutNameEntry.TABLE_NAME,
        allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Workout Workout = cursorToWorkout(cursor);
      Workouts.add(Workout);
      cursor.moveToNext();
    }
    cursor.close();
    return Workouts;
  }
  
  private Workout cursorToWorkout(Cursor cursor) {
    Workout Workout = new Workout();
    Workout.setId(cursor.getInt(0));
    Workout.setName(cursor.getString(1));
    return Workout;
  }
} 