package lk.ijse.hotel.bo.custom.impl;

import lk.ijse.hotel.bo.custom.UserBO;
import lk.ijse.hotel.bo.util.FactoryConfiguration;
import lk.ijse.hotel.bo.util.GenerateID;
import lk.ijse.hotel.dao.DAOFactory;
import lk.ijse.hotel.dao.DAOType;
import lk.ijse.hotel.dao.custom.StudentDAO;
import lk.ijse.hotel.dao.custom.UserDAO;
import lk.ijse.hotel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotel.dao.custom.impl.UserDAOImpl;
import lk.ijse.hotel.dto.UserDTO;
import lk.ijse.hotel.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class UserBOImpl implements UserBO {
    Session session;
    Transaction transaction;
    ModelMapper modelMap=new ModelMapper();
    UserDAO userDAO= (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOType.USER);
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
    public UserDTO saveUser(UserDTO userDTO) {
        openSession();
        final User save = userDAO.save(session, modelMap.map(userDTO, User.class));
        closeSession();
        return modelMap.map(save,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        openSession();
        final User update = userDAO.update(session, modelMap.map(userDTO, User.class));
        closeSession();
        return modelMap.map(update,UserDTO.class);
    }

    @Override
    public boolean deleteUser(String id) {
        openSession();
        final boolean delete = userDAO.delete(session, id);
        closeSession();
        return delete;
    }

    @Override
    public List<UserDTO> getAllUser() {
        openSession();
        final List<UserDTO> list = modelMap.map(userDAO.getAll(session), new TypeToken<List<UserDTO>>() {
        }.getType());
        closeSession();
        return list;
    }

    @Override
    public UserDTO searchUser(String id) {
        openSession();
        final UserDTO userDTO = modelMap.map(userDAO.search(session, id), UserDTO.class);
        closeSession();
        return userDTO;
    }

    @Override
    public String generateIDUser() {
        openSession();
        final String id = userDAO.generateID(session);
        closeSession();
        return GenerateID.generateNewID("UR-",id);
    }
}
