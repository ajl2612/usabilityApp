package Logic.Exercise;

public class DataPoint {

	private int exerciseID;
	private int reps;
	private int set;
	private int weight;
	private int time;
	
	
	public DataPoint(){}	
	
	/**
	 * Constructor for data point object with specified information. 
	 * 
	 * @param exe_id - unique ID of exercise this data point is associated with
	 * @param reps - how many reps were completed
	 * @param set - what set number this data point is for
	 * @param weight - the weight used this set
	 * @param seconds - time to complete exercise in seconds
	 */
	public DataPoint( int exeID, int reps, int set, int weight, int seconds){
		this.exerciseID = exeID;
		this.reps = reps;
		this.set = set;
		this.weight = weight;
		this.time = seconds;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSet() {
		return set;
	}

	public void setSet(int set) {
		this.set = set;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String toString(){
		String s = "";
		return s + "ExerciseID: " + exerciseID + " Set: " + set + " Reps: " + reps;
	}
	
	
}
