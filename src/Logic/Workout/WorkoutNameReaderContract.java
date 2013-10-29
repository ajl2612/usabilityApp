/**
 * 
 */
package Logic.Workout;

import android.provider.BaseColumns;

/**
 * @author maggiehewitt
 *
 */
public final class WorkoutNameReaderContract {
	
	public WorkoutNameReaderContract(){}
	
	public static abstract class WorkoutNameEntry implements BaseColumns{
		public static final String TABLE_NAME="WorkoutNameReader";
		public static final String WORKOUT_ID = "workoutId";
		public static final String WORKOUT_NAME = "name";
	}
	
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + WorkoutNameEntry.TABLE_NAME + " ("+
			WorkoutNameEntry.WORKOUT_ID + " integer primary key autoincrement, " +
			WorkoutNameEntry.WORKOUT_NAME + TEXT_TYPE
			+ " )";
	
	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + WorkoutNameEntry.TABLE_NAME;
}
