
public class Jooj {
	private int a;
	static int classId;
	
	public Jooj() {
		classId++;
		a = classId;
	}
	
	public int getId() {
		return a;
	}
}
