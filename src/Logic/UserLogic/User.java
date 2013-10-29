package Logic.UserLogic;

public class User {
	private String name;
	int id;
	private int age;
	private int height;
	private int weight;

	public User(){}
	
	/**
	 * Creates a user with specified information. 
	 *  
	 * @param n - name of user
	 * @param a - age of user
	 * @param h - height in inches of user
	 * @param w - weihgt in pounds of user
	 */
	public User(int id, String n, int a, int h, int w){
		this.id = id;
		this.name = n;
		this.age = a;
		this.weight = w;
		this.height = h;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge(){
		return age;
	}
	
	public void setAge( int newAge){
		age = newAge;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight( int newHeight){
		height = newHeight;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void setWeight( int newWeight){
		weight = newWeight;
	}
	
	public String toString(){
		return name;
	}

	public void setId(int newID) {
		this.id = newID;
	}
	
	public int getID(){
		return id;
	}

}
