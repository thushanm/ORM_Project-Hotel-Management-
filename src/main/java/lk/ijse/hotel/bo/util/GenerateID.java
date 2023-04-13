package lk.ijse.hotel.bo.util;

public class GenerateID {
    public static String generateNewID(String start,String id){
        if(id !=null){
            final String[] split = id.split(start);
          int number=Integer.parseInt(split[1])+1;
          return String.format(start+"%04d",number);
        }else{
          return start+"0000";
        }

    }
}
