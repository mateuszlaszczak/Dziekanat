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
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="school_id")
	private Set<Group> groups;

	public DegreeCourse() {
		groups = new HashSet<Group>();
	}

	public void addGroup(Group newGroup) {
		groups.add(newGroup);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Group> getGroups() {
		return groups;
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

}
