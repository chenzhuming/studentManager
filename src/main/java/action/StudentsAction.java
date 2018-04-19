package action;

import com.opensymphony.xwork2.ModelDriven;
import entity.Students;
import services.StudentsDAO;
import services.impl.StudentsDAOImpl;

import java.util.List;

public class StudentsAction extends SuperAction implements ModelDriven<Students> {
    Students students = new Students();

    public String query() {
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        List<Students> students_list = studentsDAO.queryAllStudents();
        session.setAttribute("students_list", students_list);
        return SUCCESS;
    }

    public String delete() {
        String sid = request.getParameter("sid");
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        studentsDAO.deleteStudents(sid);
        return "delete_success";
    }

    public String modify() {
        String id = request.getParameter("sid");
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        Students students = studentsDAO.queryStudentBySid(id);
        session.setAttribute("modify_students",students);
        return "modify_success";
    }
    public String save(){
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        studentsDAO.updateStudents(students);
        return "save_success";
    }
    public String add() {
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        studentsDAO.addStudents(students);
        return "add_success";
    }

    @Override
    public Students getModel() {
        return students;
    }
}
