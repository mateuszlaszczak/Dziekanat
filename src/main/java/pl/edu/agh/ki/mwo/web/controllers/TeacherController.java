package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.StudentGroup;
import pl.edu.agh.ki.mwo.model.Teacher;
import pl.edu.agh.ki.mwo.model.Course;
import pl.edu.agh.ki.mwo.model.DegreeCourse;
import pl.edu.agh.ki.mwo.model.Grade;
import pl.edu.agh.ki.mwo.model.Student;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;
@Controller
public class TeacherController {
	
	@RequestMapping(value="/Teachers")
    public String listTeachers(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("teachers", DatabaseConnector.getInstance().getTeachers());
    	
        return "teacherList";    
    }
	
	
	

	
    
    @RequestMapping(value="/AddTeacher")
    public String addTeacher(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
        return "teacherForm";    
    }
//    
//    
//    
    @RequestMapping(value="/ModifyTeacher")
   public String modifyStudent(@RequestParam(value="teacherId", required=false) String teacherId, 
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Teacher teacher = DatabaseConnector.getInstance().getTeacher(teacherId);
    	model.addAttribute("teacherName", teacher.getName());
    	model.addAttribute("teacherSurname", teacher.getSurname());
    	model.addAttribute("teacherEmail", teacher.getEmail());
    	model.addAttribute("teacherId",teacher.getId());
        
    	return "teacherModForm";
    }
//    
    @RequestMapping(value="/teacherCourse")
   public String showMarks(@RequestParam(value="teacherId", required=false) String teacherId, 
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	model.addAttribute("courses", DatabaseConnector.getInstance().getTeacherCourse(teacherId));
    	return "teacherCourse";
    }
////    
    @RequestMapping(value="/SaveTeacher", method=RequestMethod.POST)
    public String saveTeacher(@RequestParam(value="teacherName", required=false) String name,
    		@RequestParam(value="teacherSurname", required=false) String surname,
    		@RequestParam(value="teacherEmail", required=false) String email,
    		@RequestParam(value="teacherId", required=false) String teacherId,
    		
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	
    	Teacher teacher = DatabaseConnector.getInstance().getTeacher(teacherId);
    
    	teacher.setName(name);
    	teacher.setSurname(surname);
    	teacher.setEmail(email);
    	
    	   
    	DatabaseConnector.getInstance().addTeacher(teacher);
       	model.addAttribute("teachers", DatabaseConnector.getInstance().getTeachers());
    	model.addAttribute("message", "Dane wykładowcy zostały zapisane");
         	
    	return "teacherList";
    }
////    
////    
//
    @RequestMapping(value="/CreateTeacher", method=RequestMethod.POST)
    public String createStudent(@RequestParam(value="teacherName", required=false) String name,
    		@RequestParam(value="teacherSurname", required=false) String surname,
    		@RequestParam(value="teacherEmail", required=false) String email,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Teacher teacher = new Teacher();
    	
    	teacher.setName(name);
    	teacher.setSurname(surname);
    	teacher.setEmail(email);
    	
    	DatabaseConnector.getInstance().addTeacher(teacher);
    	   
    	
       	model.addAttribute("teachers", DatabaseConnector.getInstance().getTeachers());
    	model.addAttribute("message", "Nowy wykładowca zostal dodany");
         	
    	return "teacherList";
    }

    
    @RequestMapping(value="/DeleteTeacher", method=RequestMethod.POST)
    public String deleteTeacher(@RequestParam(value="teacherId", required=false) String teacherId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteTeacher(teacherId);  	
       	model.addAttribute("teachers", DatabaseConnector.getInstance().getTeachers());
    	model.addAttribute("message", "Nauczyciel został usunięty");
         	
    	return "teacherList";
    }
    
    
    @RequestMapping(value="/AddCourse")
    public String addCourse(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	model.addAttribute("courses", DatabaseConnector.getInstance().getCourses());
        return "courseForm";    
    }
    
    @RequestMapping(value="/SaveTeacherCourse")
    public String saveTeacherCourse(
    		@RequestParam(value="teacherId", required=false) String teacherId, 
    		@RequestParam(value="allCourses", required=false) String courseId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	DatabaseConnector.getInstance().addCourseToTeacher(teacherId, courseId);
    	
    	model.addAttribute("teachers", DatabaseConnector.getInstance().getTeachers());
        return "teacherList";    
    }
    
    

}
