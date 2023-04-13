package lk.ijse.hotel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
   private static AnchorPane pane;
    public static void navigate(Routes routes,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window= (Stage) pane.getScene().getWindow();
        switch (routes) {
            case DASHBOARD:
                window.setTitle("Dashboard");
                window.setResizable(true);
                window.setMaximized(true);
                initUi("","Dashboard.fxml");
                break;
            case RESERVATION:
                initUi("page/","Reservation.fxml");
                break;
            case ROOM:
                initUi("page/","Room.fxml");
                break;
            case STUDENT:
                initUi("page/","Student.fxml");
                break;
            case USER:
                initUi("page/","User.fxml");
                break;
            default:
               new Alert(Alert.AlertType.WARNING,"Ui Not Found").show();
        }

    }
    public static void initUi(String folder,String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/"+folder+location)));
    }
}
