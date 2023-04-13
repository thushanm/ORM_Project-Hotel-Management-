import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hotel.bo.custom.RoomBO;
import lk.ijse.hotel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotel.dto.ReservationDTO;
import lk.ijse.hotel.dto.RoomDTO;
import lk.ijse.hotel.tm.ReservationTM;
import lk.ijse.hotel.tm.RoomTM;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.IOException;
import java.util.List;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
