package pl.edu.agh.ki.mwo.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher implements java.io.Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}





	@Column(name="email")
	private String email;
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}





	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "teacher")
	Set<Course> courses;
	

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



	public Teacher() {

	}

	public Teacher(String name, String surname, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	


	
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}
	
	

}
