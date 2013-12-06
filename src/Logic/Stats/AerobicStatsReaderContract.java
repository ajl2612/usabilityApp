package Logic.Stats;

import android.provider.BaseColumns;

public class AerobicStatsReaderContract {
	public AerobicStatsReaderContract() {
	}

	public static abstract class AerobicStatsEntry implements BaseColumns {
		public static final String TABLE_NAME = "AerobicStatsReader";
		public static final String A_STATS_ENTRY_ID = "AsId";
		public static final String A_EXERCISE_ID = "exerciseId";
		public static final String A_DIST = "AsDIST";
		public static final String A_TIME = "AsTIME";
	}

	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ AerobicStatsEntry.TABLE_NAME + " ("
			+ AerobicStatsEntry.A_STATS_ENTRY_ID
			+ " integer primary key autoincrement, "
			+ AerobicStatsEntry.A_EXERCISE_ID + INT_TYPE + COMMA_SEP
			+ AerobicStatsEntry.A_DIST + INT_TYPE + COMMA_SEP
			+ AerobicStatsEntry.A_TIME + INT_TYPE + " )";

	public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ AerobicStatsEntry.TABLE_NAME;

}
