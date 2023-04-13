package lk.ijse.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel.util.Navigation;
import lk.ijse.hotel.util.Routes;

import java.io.IOException;

public class DashboardController {
    public AnchorPane pane;
    public Label lblUserID;
    public void initialize(){
        lblUserID.setText(LoginFormController.NAME_USER);
    }

    public void btnNaviReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,pane);
    }

    public void btnNaviStudent(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,pane);
    }

    public void btnNaviRoom(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM,pane);
    }

    public void btnNaviUser(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,pane);
    }

    public void btnLogout(MouseEvent mouseEvent) throws IOException {
        final Stage window = (Stage) pane.getScene().getWindow();
        window.close();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
        window.setResizable(false);
        window.setMaximized(false);
        window.setTitle("Login..");
        window.show();
    }
}
