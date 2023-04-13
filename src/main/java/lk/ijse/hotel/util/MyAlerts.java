package lk.ijse.hotel.util;

import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MyAlerts {

    public static Optional getAlertConfirmation(int type,String massage){
        Alert alert=null;
        switch (type) {
            case 404:alert=new Alert(Alert.AlertType.WARNING,massage);
            break;
            case 202:alert=new Alert(Alert.AlertType.INFORMATION,massage);
            break;

        }
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.APPLY);
        Optional<ButtonType> result=alert.showAndWait();
        return  result;
        // design
    }
    public static Button getDeleteBtn(){
        Button btnDelete=new Button("Delete");
        btnDelete.setStyle("-fx-font-size: 16px;-fx-border-color: red;-fx-border-width: 2px");
        btnDelete.setCursor(Cursor.HAND);
        return btnDelete;
    }
    public static Button getEditBtn(){
        Button btnEdit=new Button("Edit");
        btnEdit.setStyle("-fx-font-size: 16px;-fx-border-color: blue;-fx-border-width: 2px");
        btnEdit.setCursor(Cursor.HAND);
        return btnEdit;
    }
}
