package lk.ijse.hotel.bo.custom.impl;

import lk.ijse.hotel.bo.custom.StudentBO;
import lk.ijse.hotel.bo.util.FactoryConfiguration;
import lk.ijse.hotel.bo.util.GenerateID;
import lk.ijse.hotel.dao.DAOFactory;
import lk.ijse.hotel.dao.DAOType;
import lk.ijse.hotel.dao.custom.RoomDAO;
import lk.ijse.hotel.dao.custom.StudentDAO;
import lk.ijse.hotel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotel.dto.StudentDTO;
import lk.ijse.hotel.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    Session session;
    Transaction transaction;
    ModelMapper modelMap=new ModelMapper();
    StudentDAO studentDAO= (StudentDAOImpl) DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    @Override
    public void openSession() {
        session= FactoryConfiguration.getInstance().getSession();
        transaction= session.beginTransaction();
    }

    @Override
    public void closeSession() {
        transaction.commit();
        session.close();
    }
    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        openSession();
        final Student save = studentDAO.save(session, modelMap.map(studentDTO, Student.class));
        closeSession();
        return modelMap.map(save,StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        openSession();
        final Student update = studentDAO.update(session, modelMap.map(studentDTO, Student.class));
        closeSession();
        return modelMap.map(update,StudentDTO.class);
    }

    @Override
    public boolean deleteStudent(String id) {
        openSession();
        final boolean delete = studentDAO.delete(session, id);
        closeSession();
        return delete;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
    openSession();
        final List<StudentDTO> list = modelMap.map(studentDAO.getAll(session), new TypeToken<List<StudentDTO>>() {
        }.getType());
        closeSession();
        return list;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        openSession();
        final StudentDTO map = modelMap.map(studentDAO.search(session, id), StudentDTO.class);
        closeSession();
        return map;
    }

    @Override
    public String generateIDStudent() {
        openSession();
        final String id = studentDAO.generateID(session);
        closeSession();
        return GenerateID.generateNewID("ST-",id);
    }
}
