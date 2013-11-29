/**
 * @author Andrew Lyne III
 */

package Logic.User;

import android.provider.BaseColumns;

public final class UserReaderContract {
	
	public UserReaderContract(){}
	
	public static abstract class UserEntry implements BaseColumns{
		public static final String TABLE_NAME="User";
		public static final String USER_ID = "_id";
		public static final String USER_NAME = "userName";
		public static final String USER_AGE = "userAge";
		public static final String USER_WEIGHT = "userWeight";
		public static final String USER_HEIGHT = "userHeight";
	}
		
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String COMMA_SEP = ",";
	private static final String INT_TYPE = "integer";
		
	static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + UserEntry.TABLE_NAME + " ("+
			UserEntry.USER_ID + " integer primary key autoincrement, " +
			UserEntry.USER_NAME + TEXT_TYPE + COMMA_SEP +
			UserEntry.USER_AGE + INT_TYPE + COMMA_SEP +
			UserEntry.USER_WEIGHT + INT_TYPE + COMMA_SEP +
			UserEntry.USER_HEIGHT + INT_TYPE + " )";
		
	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
}

