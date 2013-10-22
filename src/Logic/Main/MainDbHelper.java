package Logic.Main;

import Logic.ExerciseLogic.ExerciseReaderContract;
import Logic.Workout.WorkoutNameReaderContract;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDbHelper extends SQLiteOpenHelper{
	public static final int DATABASE_VERSION = 1;
	public static String DATABASE_NAME = "ExerciseReader.db";
	
	public MainDbHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db){
		db.execSQL(ExerciseReaderContract.SQL_CREATE_ENTRIES);
		db.execSQL(WorkoutNameReaderContract.SQL_CREATE_ENTRIES);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(ExerciseReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(WorkoutNameReaderContract.SQL_DELETE_ENTRIES);
		onCreate(db);
	}

}
