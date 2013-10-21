package Logic.ExerciseLogic;

public class Exercise {
	private int id;
	private String name;
	private String type;

	public Exercise(){}
	
	public Exercise(int i, String n, String t){
		this.id = i;
		this.name = n;
		this.type = t;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}


