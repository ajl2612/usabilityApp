package Logic.Stats;
import android.provider.BaseColumns;

public class BodyWeightStatsReaderContract {
	public BodyWeightStatsReaderContract(){}
	
	public static abstract class BodyWeightStatsEntry implements BaseColumns{
		public static final String TABLE_NAME="BodyWeightStatsReader";
		public static final String BW_STATS_ENTRY_ID = "bwsId";
		public static final String BW_EXERCISE_ID = "exerciseId";
		public static final String BW_REPS = "bwsReps";
	}
	
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + BodyWeightStatsEntry.TABLE_NAME + " ("+
		    BodyWeightStatsEntry.BW_STATS_ENTRY_ID + " integer primary key autoincrement, " +
			BodyWeightStatsEntry.BW_EXERCISE_ID +  INT_TYPE + COMMA_SEP +
			BodyWeightStatsEntry.BW_REPS + INT_TYPE
			+ " )";
	
	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + BodyWeightStatsEntry.TABLE_NAME;

}
