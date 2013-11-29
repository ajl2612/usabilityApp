package Logic.Stats;

import java.util.ArrayList;

import Logic.Main.MainDbHelper;
import Logic.Stats.BodyWeightStatsReaderContract.BodyWeightStatsEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import android.content.ContentValues;
import android.content.Context;

public class BodyWeightStatsDataSource {

	private SQLiteDatabase database;
	private MainDbHelper dbHelper;

	private String[] allColumns = { BodyWeightStatsEntry.BW_STATS_ENTRY_ID,
			BodyWeightStatsEntry.BW_EXERCISE_ID, BodyWeightStatsEntry.BW_REPS };

	public BodyWeightStatsDataSource(Context context) {
		dbHelper = new MainDbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long addStat(int exerciseId, int reps) {
		ContentValues values = new ContentValues();

		values.put(BodyWeightStatsEntry.BW_EXERCISE_ID, exerciseId);
		values.put(BodyWeightStatsEntry.BW_REPS, reps);

		long insertId = database.insert(BodyWeightStatsEntry.TABLE_NAME, null,
				values);

		Cursor cursor = database.query(BodyWeightStatsEntry.TABLE_NAME,
				allColumns, BodyWeightStatsEntry.BW_STATS_ENTRY_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		cursor.close();
		return insertId;
	}

	public ArrayList<Pair<Integer, Integer>> getExerciseStats(int exerciseId) {
		ArrayList<Pair<Integer, Integer>> dataPoints = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> pair;

		Cursor cursor = database.query(BodyWeightStatsEntry.TABLE_NAME,
				allColumns, BodyWeightStatsEntry.BW_EXERCISE_ID + " = "
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