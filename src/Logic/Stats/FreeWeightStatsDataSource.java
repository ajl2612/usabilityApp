package Logic.Stats;

import java.util.ArrayList;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseReaderContract.ExerciseEntry;
import Logic.Main.MainDbHelper;
import Logic.Stats.FreeWeightStatsReaderContract.FreeWeightStatsEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import android.content.ContentValues;
import android.content.Context;

public class FreeWeightStatsDataSource {

	private SQLiteDatabase database;
	private MainDbHelper dbHelper;

	private String[] allColumns = { FreeWeightStatsEntry.FW_STATS_ENTRY_ID,
			FreeWeightStatsEntry.FW_EXERCISE_ID, FreeWeightStatsEntry.FW_REPS,
			FreeWeightStatsEntry.FW_WEIGHT };

	public FreeWeightStatsDataSource(Context context) {
		dbHelper = new MainDbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long addStat(int exerciseId, int reps, int weight) {
		ContentValues values = new ContentValues();

		values.put(FreeWeightStatsEntry.FW_EXERCISE_ID, exerciseId);
		values.put(FreeWeightStatsEntry.FW_REPS, reps);
		values.put(FreeWeightStatsEntry.FW_WEIGHT, weight);
		System.out.println("Adding Stat");
		long insertId = database.insert(FreeWeightStatsEntry.TABLE_NAME, null,
				values);
		System.out.println("insert Id: " + insertId);
		Cursor cursor = database.query(FreeWeightStatsEntry.TABLE_NAME,
				allColumns, FreeWeightStatsEntry.FW_STATS_ENTRY_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		cursor.close();
		return insertId;
	}

	public ArrayList<Pair<Integer, Integer>> getExerciseStats(int exerciseId) {
		ArrayList<Pair<Integer, Integer>> dataPoints = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> pair;

		Cursor cursor = database.query(FreeWeightStatsEntry.TABLE_NAME,
				allColumns, FreeWeightStatsEntry.FW_EXERCISE_ID + " = "
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