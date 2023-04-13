package lk.ijse.hotel.bo.custom.impl;

import lk.ijse.hotel.bo.custom.RoomBO;
import lk.ijse.hotel.bo.util.FactoryConfiguration;
import lk.ijse.hotel.bo.util.GenerateID;
import lk.ijse.hotel.dao.DAOFactory;
import lk.ijse.hotel.dao.DAOType;
import lk.ijse.hotel.dao.custom.RoomDAO;
import lk.ijse.hotel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel.dto.RoomDTO;
import lk.ijse.hotel.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class RoomBOImpl implements RoomBO {
    Session session;
    Transaction transaction;
    ModelMapper modelMap=new ModelMapper();
    RoomDAO roomDAO= (RoomDAOImpl) DAOFactory.getInstance().getDAO(DAOType.ROOM);
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
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        openSession();
        final Room save = roomDAO.save(session,modelMap.map(roomDTO, Room.class));
        closeSession();
        return modelMap.map(save,RoomDTO.class);
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO) {
        openSession();
        final Room update = roomDAO.update(session,modelMap.map(roomDTO, Room.class));
        closeSession();
        return modelMap.map(update,RoomDTO.class);
    }

    @Override
    public boolean deleteRoom (String id) {
        openSession();
        final boolean delete = roomDAO.delete(session, id);
        closeSession();
        return delete;
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        openSession();
        final List<RoomDTO> list = modelMap.map(roomDAO.getAll(session), new TypeToken<List<RoomDTO>>() {
        }.getType());
        closeSession();
        return list;
    }

    @Override
    public RoomDTO searchRoom(String id) {
        openSession();
        final RoomDTO roomDTO = modelMap.map(roomDAO.search(session, id), RoomDTO.class);
        closeSession();
        return roomDTO;
    }

    @Override
    public String generateIDRoom() {
        openSession();
        final String id = roomDAO.generateID(session);
        closeSession();
        return GenerateID.generateNewID("RM-",id);
    }
}
