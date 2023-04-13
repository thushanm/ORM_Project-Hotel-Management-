package lk.ijse.hotel.bo;

import lk.ijse.hotel.bo.custom.impl.*;
import org.hibernate.usertype.UserCollectionType;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static  BOFactory getInstance(){
        return boFactory==null ? boFactory=new BOFactory():boFactory;
    }
    public SuperBO getBO(BOType boType){
        switch (boType) {
            case RESERVATION:return new ReservationBOImpl();
            case STUDENT:return new StudentBOImpl();
            case ROOM:return new RoomBOImpl();
            case USER:return new UserBOImpl();
            case CUSTOM:return new CustomBOImpl();
            default:return null;
        }
    }
}
