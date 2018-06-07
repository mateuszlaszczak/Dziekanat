package pl.edu.agh.ki.mwo.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.agh.ki.mwo.model.Course;
import pl.edu.agh.ki.mwo.model.DegreeCourse;
import pl.edu.agh.ki.mwo.model.Grade;
import pl.edu.agh.ki.mwo.model.StudentGroup;
import pl.edu.agh.ki.mwo.model.Teacher;
import pl.edu.agh.ki.mwo.model.Student;

public class DatabaseConnector {
	
	protected static DatabaseConnector instance = null;
	
	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}
		
		Session session;
	
		protected DatabaseConnector() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void teardown() {
		session.close();
		HibernateUtil.shutdown();
		instance = null;
	}
	
	public Iterable<DegreeCourse> getSchools() {
		String hql = "FROM DegreeCourse";
		Query query = session.createQuery(hql);
		List <DegreeCourse>schools = query.list();
		
		for(DegreeCourse scho: schools) {
			System.out.println(scho.getName());
		}
		
		return schools;
	}
	
	public DegreeCourse getSpecificSchool(String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id="+schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		return results.get(0);
	}
	
	
	
	
	
	public void addSchool(DegreeCourse school) {
		Transaction transaction = session.beginTransaction();
		session.save(school);
		transaction.commit();
	}
	
	public void deleteSchool(String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (DegreeCourse s : results) {
			session.delete(s);
		}
		transaction.commit();
	}

	public Iterable<StudentGroup> getSchoolClasses() {
		String hql = "FROM StudentGroup";
		Query query = session.createQuery(hql);
		List <StudentGroup> schoolClasses = query.list();
		
		for(StudentGroup stu: schoolClasses) {
			System.out.println(stu.getName());
		}
		
		return schoolClasses;
	}
	
	public Iterable<StudentGroup> getGroupBasedOnCourseDagree(long l) {
		String hql = "SELECT S FROM StudentGroup S  INNER JOIN S.degreeCourse degreeCourse WHERE degreeCourse.id="+l;
		Query query = session.createQuery(hql);
		List <StudentGroup> schoolClasses = query.list();
		for(StudentGroup stu:schoolClasses) {
			System.out.println("LOL " +stu.getName() );
		}
		return schoolClasses;
	}
	
	
	
	public void addSchoolClass(StudentGroup schoolClass, String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(schoolClass);
		} else {
			DegreeCourse school = results.get(0);
			school.addGroup(schoolClass);
			session.save(school);
		}
		transaction.commit();
	}
	
	public void deleteSchoolClass(String schoolClassId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (StudentGroup s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	//Students
	public Iterable<Student> getStudents() {
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List <Student> students = query.list();
		return students;
	}
	
	public void getTestStudents() {
		Student student = (Student) session.get(Student.class, 1);
		System.out.println(student.getName());
	}
	
	public Student getStudent(String studentId) {
		String hql = "FROM Student S WHERE S.id="+studentId;
		Query query = session.createQuery(hql);
		List<Student> results = query.list();
		return results.get(0);
	}
//	
	public void addStudent(Student student, String schoolClassId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(student);
		} else {
			StudentGroup schoolClass = results.get(0);
			schoolClass.add(student);
			session.save(student);
			session.save(schoolClass);
		}
		session.getTransaction().commit();
	}
	
//	
	public void saveModifiedStudent(Student student, String schoolClassId, String degreeCourseId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(student);
		} else {
			StudentGroup schoolClass = results.get(0);
			session.save(schoolClass);
	
		transaction.commit();
	}
	}
//	
//	
	public long getSpecificStudentClass(String studentId) {
		String hql = "SELECT S FROM StudentGroup S  INNER JOIN S.studList student WHERE student.id="+studentId;
		Query query = session.createQuery(hql);
		StudentGroup sclass = (StudentGroup) query.list().get(0);
		return sclass.getId();
	}
//
//	
	public void deleteStudent(String studentId) {
		String hql = "FROM Student S WHERE S.id=" + studentId;
		Query query = session.createQuery(hql);
		List<Student> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Student s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	public Iterable<Grade> getGrades(String studentId) {
		String hql = "SELECT S FROM Grade S INNER JOIN S.student stud WHERE stud.id="+studentId;
		Query query = session.createQuery(hql);
		List <Grade> grades = query.list();
		return grades;
	}


	//Teachers
	public Iterable<Teacher> getTeachers() {
		String hql = "FROM Teacher";
		Query query = session.createQuery(hql);
		List <Teacher> teachers = query.list();
		return teachers;
	}

	public Iterable<Course> getTeacherCourse(String teacherId) {
		String hql = "select s from Course s INNER JOIN s.teacher teach where teach.id="+teacherId;
		Query query = session.createQuery(hql);
		List <Course> courses = query.list();
		courses.forEach(e->System.out.println(e.getName()));
		return courses;
	}
	
	public Teacher getTeacher(String teacherId) {
		String hql = "FROM Teacher S WHERE S.id="+teacherId;
		Query query = session.createQuery(hql);
		List<Teacher> results = query.list();
		return results.get(0);
	}
	
	public void addTeacher(Teacher teacher) {
		Transaction transaction = session.beginTransaction();
		session.save(teacher);
		transaction.commit();
	}
	
	public void deleteTeacher(String teacherId) {
		String hql = "FROM Teacher S WHERE S.id=" + teacherId;
		Query query = session.createQuery(hql);
		List<Teacher> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Teacher s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	
	//Courses
	public Iterable<Course> getCourses() {
		String hql = "FROM Course";
		Query query = session.createQuery(hql);
		List <Course> courses = query.list();
		return courses;
	}
	
	public Course getSpecificCourse(String courseId) {
		String hql = "from Course s where s.id="+courseId;
		Query query = session.createQuery(hql);
		return (Course) query.list().get(0);
		
	}

	public void addCourseToTeacher(String teacherId, String courseId) {
		Transaction transaction = session.beginTransaction();
		Teacher teacher = DatabaseConnector.getInstance().getTeacher(teacherId);
		System.out.println("Nauczyciel "+teacher.getName());
		Course course = getSpecificCourse(courseId);
		System.out.println("KURS " + course.getName());
		teacher.addCourse(course);
		transaction.commit();
	}

	public void addGradeToStudent(String studentId, String courseId, String studentGrade) {
		Transaction transaction = session.beginTransaction();
		Grade grade = new Grade();
		grade.setMark(Double.parseDouble(studentGrade));
		Student student = getStudent(studentId);
		Course course = getSpecificCourse(courseId);
		student.addGrade(course, student, grade);
		
		System.out.println("STUDENT: "+student.getName());
		System.out.println("OCENA "+studentGrade);
		System.out.println("PRZEDMIOT " +course.getName() );
		session.save(grade);
		transaction.commit();
		
	}
	
	
}


