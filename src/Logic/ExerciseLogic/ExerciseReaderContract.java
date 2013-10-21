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
		public static final String EXERCISE_ID = "_id";
		public static final String EXERCISE_NAME = "exerciseName";
		public static final String EXERCISE_TYPE = "type";
	}
	
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String COMMA_SEP = ",";
	
	static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + ExerciseEntry.TABLE_NAME + " ("+
			ExerciseEntry.EXERCISE_ID + " integer primary key autoincrement, " +
			ExerciseEntry.EXERCISE_NAME + TEXT_TYPE + COMMA_SEP +
			ExerciseEntry.EXERCISE_TYPE + TEXT_TYPE
			+ " )";
	
	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + ExerciseEntry.TABLE_NAME;
}
