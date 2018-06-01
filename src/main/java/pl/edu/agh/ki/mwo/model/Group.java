package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="schoolClasses")
public class Group implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	@Column
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="group_id")
	private Set<Student> students;
	
	
//	@ManyToMany(cascade= {CascadeType.MERGE,
//			CascadeType.MERGE,CascadeType.PERSIST,
//			CascadeType.REFRESH})
//	@JoinTable(
//			name="teacher_class", 
//			joinColumns=@JoinColumn(name="class_id"),
//			inverseJoinColumns=@JoinColumn(name="teacher_id")
//			)
//	private Set<Teacher> teachers;
	
//	@ManyToMany(cascade= {CascadeType.MERGE,
//			CascadeType.MERGE,CascadeType.PERSIST,
//			CascadeType.REFRESH})
//	@JoinTable(
//			name="class_course", 
//			joinColumns=@JoinColumn(name="class_id"),
//			inverseJoinColumns=@JoinColumn(name="course_id")
//			)
//	private Set<Course> courses;
	
	

	public Group() {
		students = new HashSet<Student>();
	}
	
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student newStudent) {
		students.add(newStudent);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getProfile() {
		return name;
	}

	public void setProfile(String profile) {
		this.name = profile;
	}


}