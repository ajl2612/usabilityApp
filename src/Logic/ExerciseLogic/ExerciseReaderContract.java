/**
 * 
 */
package Logic.ExerciseLogic;

import android.provider.BaseColumns;

/**
 * @author maggiehewitt
 *
 */
public final class ExerciseReaderContract {
	
	public ExerciseReaderContract(){}
	
	public static abstract class ExerciseEntry implements BaseColumns{
		public static final String TABLE_NAME="Exercise";
		public static final String COLUMN_NAME_EXERCISE_ID = "exerciseId";
		public static final String COLUMN_NAME_EXERCISE_NAME = "exerciseName";
		public static final String COLUMN_NAME_TYPE = "type";
	}
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	
	static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + ExerciseEntry.TABLE_NAME + " ("+
			ExerciseEntry._ID + " INTEGER PRIMARY KEY," +
			ExerciseEntry.COLUMN_NAME_EXERCISE_ID + TEXT_TYPE + COMMA_SEP +
			ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + TEXT_TYPE + COMMA_SEP +
			ExerciseEntry.COLUMN_NAME_TYPE + TEXT_TYPE
			+ " )";
	
	static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + ExerciseEntry.TABLE_NAME;
}
