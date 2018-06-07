package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;





@SuppressWarnings("serial")
@Entity
@Table(name="groups")
public class StudentGroup implements java.io.Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@Column
	private String name;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "degreeCourse_id")
	private DegreeCourse degreeCourse;
	
	
	
	public DegreeCourse getDegreeCourse() {
		return degreeCourse;
	}

	public void setDegreeCourse(DegreeCourse degreeCourse) {
		this.degreeCourse = degreeCourse;
	}

	public StudentGroup() {
		
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

	public Set<Student> getStudList() {
		return studList;
	}

	public void setStudList(Set<Student> studList) {
		this.studList = studList;
	}

	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH,CascadeType.REMOVE}, mappedBy="studentGroup")
	private Set <Student> studList;
	
	
	
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
	
	public void add(Student student) {
		if(studList==null) {
			System.out.println("Set jest pusty");
			studList = new HashSet<>();
			studList.add(student);
			student.setStudentGroup(this);
		}else {
			System.out.println("Set nie jest pusty");
			System.out.println(studList.size());
			studList = new HashSet<>();
			studList.add(student);
			System.out.println(studList.size());
			student.setStudentGroup(this);
		}
		
		
	}
	







}