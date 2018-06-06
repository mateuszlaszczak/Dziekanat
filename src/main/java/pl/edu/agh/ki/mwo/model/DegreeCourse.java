package pl.edu.agh.ki.mwo.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="degreeCourses")
public class DegreeCourse implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String year;
	
	@OneToMany(mappedBy="degreeCourse",cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Set <StudentGroup> studGroups;

	public DegreeCourse() {
		studGroups= new HashSet<StudentGroup>();
	}

	public void addGroup(StudentGroup newGroup) {
		studGroups.add(newGroup);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGroups(Set<StudentGroup> groups) {
		this.studGroups = groups;
	}

	public Set<StudentGroup> getGroups() {
		return studGroups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString() {
		return "DegreeCourse: " + getName() + " (" + getYear() + ", " + getGroups().size() + " groups)";
}
	
	public void addStudentGroup(StudentGroup schoolClass) {
		if(studGroups==null) {
			studGroups=new HashSet<StudentGroup>();
			studGroups.add(schoolClass);
			schoolClass.setDegreeCourse(this);
		}else {
			studGroups.add(schoolClass);
			schoolClass.setDegreeCourse(this);
		}
	}

}
