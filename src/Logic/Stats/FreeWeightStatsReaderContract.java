package Logic.Stats;

import android.provider.BaseColumns;

public class FreeWeightStatsReaderContract {
	public FreeWeightStatsReaderContract() {
	}

	public static abstract class FreeWeightStatsEntry implements BaseColumns {
		public static final String TABLE_NAME = "FreeWeightStatsReader";
		public static final String FW_STATS_ENTRY_ID = "FWsId";
		public static final String FW_EXERCISE_ID = "exerciseId";
		public static final String FW_REPS = "FWsReps";
		public static final String FW_WEIGHT = "FWsWeight";
	}

	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ FreeWeightStatsEntry.TABLE_NAME + " ("
			+ FreeWeightStatsEntry.FW_STATS_ENTRY_ID
			+ " integer primary key autoincrement, "
			+ FreeWeightStatsEntry.FW_EXERCISE_ID + INT_TYPE + COMMA_SEP
			+ FreeWeightStatsEntry.FW_REPS + INT_TYPE + COMMA_SEP
			+ FreeWeightStatsEntry.FW_WEIGHT + INT_TYPE + " )";

	public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ FreeWeightStatsEntry.TABLE_NAME;
}
