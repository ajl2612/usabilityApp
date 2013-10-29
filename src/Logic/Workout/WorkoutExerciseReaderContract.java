package Logic.Workout;

import Logic.Workout.WorkoutNameReaderContract.WorkoutNameEntry;
import android.provider.BaseColumns;

public class WorkoutExerciseReaderContract {
	public WorkoutExerciseReaderContract(){}
	
	public static abstract class WorkoutExerciseEntry implements BaseColumns{
		public static final String TABLE_NAME="WorkoutExerciseReader";
		public static final String WORKOUT_EXERCISE_ENTRY_ID = "workoutExerciseId";
		public static final String WORKOUT_ID = "workoutId";
		public static final String WORKOUT_EXERCISE_ID = "name";
	}
	
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + WorkoutExerciseEntry.TABLE_NAME + " ("+
		    WorkoutExerciseEntry.WORKOUT_EXERCISE_ENTRY_ID + " integer primary key autoincrement, " +
			WorkoutExerciseEntry.WORKOUT_ID +  INT_TYPE + COMMA_SEP +
			WorkoutExerciseEntry.WORKOUT_EXERCISE_ID + INT_TYPE
			+ " )";
	
	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + WorkoutNameEntry.TABLE_NAME;

}
