package day2;

public class PojoClass {
	
	String name;
	String location;
	String phone;
	String course[];
	/*to get the setters and getters methods just select the global created variable
	 * and click on source in eclipse and click on generate getters and setters*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourse() {
		return course;
	}
	public void setCourse(String[] course) {
		this.course = course;
	}
	

}
