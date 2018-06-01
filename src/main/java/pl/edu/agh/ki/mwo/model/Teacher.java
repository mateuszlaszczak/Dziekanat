package pl.edu.agh.ki.mwo.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher implements java.io.Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="email")
	private String email;
	
	
	@ManyToMany(cascade= {CascadeType.MERGE,
						CascadeType.MERGE,CascadeType.PERSIST,
						CascadeType.REFRESH})
	@JoinTable(
			name="teacher_class", 
			joinColumns=@JoinColumn(name="teacher_id"),
			inverseJoinColumns=@JoinColumn(name="class_id")
			)
	private List<Group> classes;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Group> getClasses() {
		return classes;
	}

	public void setClasses(List<Group> classes) {
		this.classes = classes;
	}

	public Teacher() {

	}

	public Teacher(String name, String surname, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public void addSchoolClass(Group schoolClass) {
		if(classes==null) {
			classes=new ArrayList<Group>();
		}
		
		classes.add(schoolClass);
	}

	
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}
	
	

}
