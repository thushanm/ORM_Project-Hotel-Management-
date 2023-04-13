package lk.ijse.hotel.bo.custom;

import lk.ijse.hotel.bo.SuperBO;
import lk.ijse.hotel.dto.StudentDTO;
import org.hibernate.Session;

import java.util.List;

public interface StudentBO extends SuperBO {
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(String id);
    List<StudentDTO> getAllStudent();
    StudentDTO searchStudent(String id);
    String generateIDStudent();
}
