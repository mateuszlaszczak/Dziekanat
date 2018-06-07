package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="students")
public class Student implements java.io.Serializable {
	
	public Student() {
		
	}
	
	public Student(String name, String surname, String adress, String dateOfBirth, int year) {
		super();
		this.name = name;
		this.surname = surname;
		this.adress = adress;
		this.dateOfBirth = dateOfBirth;
		this.year = year;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	
	@Column
	private String adress;
	
	@Column
	private String dateOfBirth;
	
	
	@Column
	private int year;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "group_id", nullable=false)
	private StudentGroup studentGroup;
	
	
	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "student")
	Set<Grade> grades;
	


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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public StudentGroup getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}
	
	
	public void addGrade(Course course, Student student, Grade grade) {
		grades = new HashSet<>();
		grades.add(grade);
		grade.setCourse(course);
		grade.setStudent(this);
	}
	
	


	

	
}
