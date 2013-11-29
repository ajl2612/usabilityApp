package Logic.Workout;

import java.util.ArrayList;

import Logic.Exercise.Exercise;

public class Workout {
	int id;
	String name;
	ArrayList<Exercise> exercises;
	
	public Workout(){
	}
	
	public Workout(String n){
		this.name = n;
		this.exercises = new ArrayList<Exercise>();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	public void addExercise(Exercise exercise){
		this.exercises.add(exercise);
	}
	
}
