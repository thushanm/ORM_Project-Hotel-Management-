package lk.ijse.hotel.bo.custom.impl;

import lk.ijse.hotel.bo.custom.ReservationBO;
import lk.ijse.hotel.bo.util.FactoryConfiguration;
import lk.ijse.hotel.bo.util.GenerateID;
import lk.ijse.hotel.dao.DAOFactory;
import lk.ijse.hotel.dao.DAOType;
import lk.ijse.hotel.dao.custom.ReservationDAO;
import lk.ijse.hotel.dao.custom.RoomDAO;
import lk.ijse.hotel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel.dto.ReservationDTO;
import lk.ijse.hotel.entity.Reservation;
import lk.ijse.hotel.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    Session session;
    Transaction transaction;
    ModelMapper modelMapper=new ModelMapper();
    ReservationDAO reservationDAO= (ReservationDAOImpl) DAOFactory.getInstance().getDAO(DAOType.RESERVATION);
    RoomDAO roomDAO= (RoomDAOImpl) DAOFactory.getInstance().getDAO(DAOType.ROOM);

    @Override
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        openSession();
        final Reservation save = reservationDAO.save(session, modelMapper.map(reservationDTO, Reservation.class));
        if(save!=null){
             Room search = roomDAO.search(session, reservationDTO.getRoom().getRoomID());
            search.setQty(search.getQty()-1);
        }
        closeSession();
            return  modelMapper.map(save,ReservationDTO.class);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        openSession();
        Reservation reservation = reservationDAO.search(session, reservationDTO.getResID()); // get old reservation
        session.detach(reservation);
         Room room = roomDAO.search(session, reservation.getRoom().getRoomID());// get old room number
         Reservation update = reservationDAO.update(session, modelMapper.map(reservationDTO, Reservation.class));
        if(!update.getRoom().getRoomID().equals(room.getRoomID())){ // checked if equals old one and new one
             Room room2 = roomDAO.search(session, update.getRoom().getRoomID());
             room.setQty(room.getQty()+1);
            room2.setQty(room.getQty()-1);
        }
        closeSession();
        return  modelMapper.map(update,ReservationDTO.class);
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        openSession();
        final boolean delete = reservationDAO.delete(session, reservationDTO.getResID());
         Room search = roomDAO.search(session, reservationDTO.getRoom().getRoomID());
        search.setQty(search.getQty()+1);
        closeSession();
        return delete;
    }

    @Override
    public List<ReservationDTO> getAllReservation() {
        openSession();
        final List<ReservationDTO> list = modelMapper.map(reservationDAO.getAll(session), new TypeToken<List<ReservationDTO>>(){}.getType());
        closeSession();
        return list;
    }

    @Override
    public ReservationDTO searchReservation(String id) {
        openSession();
        final ReservationDTO map = modelMapper.map(reservationDAO.search(session, id), ReservationDTO.class);
        closeSession();
        return map;
    }

    @Override
    public String generateIDReservation() {
        openSession();
        final String id = reservationDAO.generateID(session);
        closeSession();
        return GenerateID.generateNewID("RS-",id);
    }

    @Override
    public void openSession() {
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

    }

    @Override
    public void closeSession() {
        transaction.commit();
        session.close();
    }
}
