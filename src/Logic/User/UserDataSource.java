package Logic.User;

import java.util.ArrayList;



//import Logic.ExerciseLogic.ExerciseDbHelper;
import Logic.Exercise.ExerciseReaderContract.ExerciseEntry;
import Logic.User.UserReaderContract.UserEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

public class UserDataSource {

  // Database fields
	
  private SQLiteDatabase database;
  //private ExerciseDbHelper dbHelper;
  
  private String[] allColumns = { 
		 ExerciseEntry.EXERCISE_ID,
		 ExerciseEntry.EXERCISE_NAME,
		 ExerciseEntry.EXERCISE_TYPE
  };

  public UserDataSource(Context context) {
 //   dbHelper = new ExerciseDbHelper(context);
  }

  public void open() throws SQLException {
   // database = dbHelper.getWritableDatabase();
  }

  public void close() {
  //  dbHelper.close();
  }
  

  public User createUser(String name, int age, int height, int weight ) {
    ContentValues values = new ContentValues();
    values.put(UserEntry.USER_NAME, name);
    values.put(UserEntry.USER_AGE, age);
    values.put(UserEntry.USER_HEIGHT, height);
    values.put(UserEntry.USER_WEIGHT, weight);
     
    long insertId = database.insert(UserEntry.TABLE_NAME, null,
        values);
    
    Cursor cursor = database.query(UserEntry.TABLE_NAME,
        allColumns, UserEntry.USER_ID + " = " + insertId, null,
        null, null, null);
    
    cursor.moveToFirst();
    User newUser = cursorToUser(cursor);
    cursor.close();
    return newUser;
  }

  public User updateUser(long id,String name, int age, int weight, int height){
	  ContentValues values = new ContentValues();
	    values.put(UserEntry.USER_NAME, name);
	    values.put(UserEntry.USER_AGE, age);
	    values.put(UserEntry.USER_HEIGHT, height);
	    values.put(UserEntry.USER_WEIGHT, weight);
	    
	  database.update(UserEntry.TABLE_NAME, values, UserEntry.USER_ID + " = " + id, null);
	  Cursor cursor = database.query(UserEntry.TABLE_NAME,
	            allColumns, UserEntry.USER_ID + " = " + id, null,
	            null, null, null);
	  
	  cursor.moveToFirst();
	  User newUser = cursorToUser(cursor);
	  cursor.close();
	  return newUser;
  }
  
  public void deleteExercise(long id) {
    database.delete(ExerciseEntry.TABLE_NAME, ExerciseEntry.EXERCISE_ID
        + " = " + id, null);
  }

  public ArrayList<User> getAllExercises() {
    ArrayList<User> Users = new ArrayList<User>();
    Cursor cursor = database.query(UserEntry.TABLE_NAME,
        allColumns, null, null, null, null, null);
    System.out.println("error is in getAll");
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      User user = cursorToUser(cursor);
      Users.add(user);
      cursor.moveToNext();
    }
    cursor.close();
    return Users;
  }
  
  private User cursorToUser(Cursor cursor) {
    User user = new User();
    user.setId(cursor.getInt(0));
    user.setName(cursor.getString(1));
    user.setAge(cursor.getInt(2));
    user.setHeight(cursor.getInt(3));
    user.setWeight(cursor.getInt(4));
    return user;
  }
} 