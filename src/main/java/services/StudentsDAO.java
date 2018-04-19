package services;

import entity.Students;

import java.util.List;

public interface StudentsDAO {
    public List<Students> queryAllStudents();
    public Students queryStudentBySid(String sid);
    public boolean addStudents(Students s);
    public boolean updateStudents(Students s);
    public boolean deleteStudents(String sid);
}
