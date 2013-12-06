package Logic.Stats;

import java.util.ArrayList;

import Logic.Main.MainDbHelper;
import Logic.Stats.AerobicStatsReaderContract.AerobicStatsEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import android.content.ContentValues;
import android.content.Context;

public class AerobicStatsDataSource {

	private SQLiteDatabase database;
	private MainDbHelper dbHelper;

	private String[] allColumns = { AerobicStatsEntry.A_STATS_ENTRY_ID,
			AerobicStatsEntry.A_EXERCISE_ID, AerobicStatsEntry.A_DIST,
			AerobicStatsEntry.A_TIME };

	public AerobicStatsDataSource(Context context) {
		dbHelper = new MainDbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long addStat(int exerciseId, int dist, int time) {
		ContentValues values = new ContentValues();

		values.put(AerobicStatsEntry.A_EXERCISE_ID, exerciseId);
		values.put(AerobicStatsEntry.A_DIST, dist);
		values.put(AerobicStatsEntry.A_TIME, time);

		long insertId = database.insert(AerobicStatsEntry.TABLE_NAME, null,
				values);

		Cursor cursor = database.query(AerobicStatsEntry.TABLE_NAME,
				allColumns, AerobicStatsEntry.A_STATS_ENTRY_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		cursor.close();
		return insertId;
	}

	public ArrayList<Pair<Integer, Integer>> getExerciseStats(int exerciseId) {
		ArrayList<Pair<Integer, Integer>> dataPoints = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> pair;

		Cursor cursor = database.query(AerobicStatsEntry.TABLE_NAME,
				allColumns, AerobicStatsEntry.A_EXERCISE_ID + " = "
						+ exerciseId, null, null, null, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			pair = new Pair<Integer, Integer>(cursor.getInt(1),
					cursor.getInt(2));
			dataPoints.add(pair);
			cursor.moveToNext();
		}
		return dataPoints;
	}
}