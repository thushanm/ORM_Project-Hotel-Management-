package lk.ijse.hotel.dao;

import lk.ijse.hotel.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
private DAOFactory(){}
    public static DAOFactory getInstance(){
    return daoFactory==null ? daoFactory=new DAOFactory():daoFactory;
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType) {
            case STUDENT:return new StudentDAOImpl();
            case ROOM:return new RoomDAOImpl();
            case RESERVATION:return new ReservationDAOImpl();
            case USER:return new UserDAOImpl();
            case CUSTOM:return new CustomDAOImpl();
            default:return null;
        }
    }
}
