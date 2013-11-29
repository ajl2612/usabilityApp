package Logic.Main;

import Logic.Exercise.ExerciseReaderContract;
import Logic.Stats.AerobicStatsReaderContract;
import Logic.Stats.BodyWeightStatsReaderContract;
import Logic.Stats.FreeWeightStatsReaderContract;
import Logic.Stats.MachineWeightStatsReaderContract;
import Logic.Workout.WorkoutExerciseReaderContract;
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
		db.execSQL(WorkoutExerciseReaderContract.SQL_CREATE_ENTRIES);
		db.execSQL(BodyWeightStatsReaderContract.SQL_CREATE_ENTRIES);
		db.execSQL(FreeWeightStatsReaderContract.SQL_CREATE_ENTRIES);
		db.execSQL(MachineWeightStatsReaderContract.SQL_CREATE_ENTRIES);
		db.execSQL(AerobicStatsReaderContract.SQL_CREATE_ENTRIES);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(ExerciseReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(WorkoutNameReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(WorkoutExerciseReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(BodyWeightStatsReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(FreeWeightStatsReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(MachineWeightStatsReaderContract.SQL_DELETE_ENTRIES);
		db.execSQL(AerobicStatsReaderContract.SQL_DELETE_ENTRIES);
		onCreate(db);
	}

}
