package Logic.Stats;

import java.util.ArrayList;

import Logic.Exercise.Exercise;
import Logic.Exercise.ExerciseReaderContract.ExerciseEntry;
import Logic.Main.MainDbHelper;
import Logic.Stats.MachineWeightStatsReaderContract.MachineWeightStatsEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import android.content.ContentValues;
import android.content.Context;

public class MachineWeightStatsDataSource {

	private SQLiteDatabase database;
	private MainDbHelper dbHelper;

	private String[] allColumns = { MachineWeightStatsEntry.MW_STATS_ENTRY_ID,
			MachineWeightStatsEntry.MW_EXERCISE_ID, MachineWeightStatsEntry.MW_REPS,
			MachineWeightStatsEntry.MW_WEIGHT };

	public MachineWeightStatsDataSource(Context context) {
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

		values.put(MachineWeightStatsEntry.MW_EXERCISE_ID, exerciseId);
		values.put(MachineWeightStatsEntry.MW_REPS, reps);
		values.put(MachineWeightStatsEntry.MW_WEIGHT, weight);

		long insertId = database.insert(MachineWeightStatsEntry.TABLE_NAME, null,
				values);

		Cursor cursor = database.query(MachineWeightStatsEntry.TABLE_NAME,
				allColumns, MachineWeightStatsEntry.MW_STATS_ENTRY_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		cursor.close();
		return insertId;
	}

	public ArrayList<Pair<Integer, Integer>> getExerciseStats(int exerciseId) {
		ArrayList<Pair<Integer, Integer>> dataPoints = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> pair;

		Cursor cursor = database.query(MachineWeightStatsEntry.TABLE_NAME,
				allColumns, MachineWeightStatsEntry.MW_EXERCISE_ID + " = "
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