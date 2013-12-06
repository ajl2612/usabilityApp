package Logic.Stats;

import android.provider.BaseColumns;

public class MachineWeightStatsReaderContract {
	public MachineWeightStatsReaderContract() {
	}

	public static abstract class MachineWeightStatsEntry implements BaseColumns {
		public static final String TABLE_NAME = "MachineWeightStatsReader";
		public static final String MW_STATS_ENTRY_ID = "MWsId";
		public static final String MW_EXERCISE_ID = "exerciseId";
		public static final String MW_REPS = "MWsReps";
		public static final String MW_WEIGHT = "MWsWeight";
	}

	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ MachineWeightStatsEntry.TABLE_NAME + " ("
			+ MachineWeightStatsEntry.MW_STATS_ENTRY_ID
			+ " integer primary key autoincrement, "
			+ MachineWeightStatsEntry.MW_EXERCISE_ID + INT_TYPE + COMMA_SEP
			+ MachineWeightStatsEntry.MW_REPS + INT_TYPE + COMMA_SEP
			+ MachineWeightStatsEntry.MW_WEIGHT + INT_TYPE + " )";

	public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ MachineWeightStatsEntry.TABLE_NAME;

}
