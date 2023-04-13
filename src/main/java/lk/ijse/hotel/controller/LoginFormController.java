package lk.ijse.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel.bo.BOFactory;
import lk.ijse.hotel.bo.BOType;
import lk.ijse.hotel.bo.custom.UserBO;
import lk.ijse.hotel.bo.custom.impl.UserBOImpl;
import lk.ijse.hotel.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    static String NAME_USER;
    public AnchorPane pane;
    public TextField txtEmail;
    public PasswordField txtPwd;
    public CheckBox chBox;
   public TextField txtPwdField;
    UserBO userBO = (UserBOImpl) BOFactory.getInstance().getBO(BOType.USER);
    final List<UserDTO> allUser = userBO.getAllUser();

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        String pwd =txtPwd.getText();
       /* if (chBox.isSelected()) {
            pwd = txtPwdField.getText();
        } else {
            pwd = txtPwd.getText();
        }*/

        for (UserDTO userDTO : allUser) {
            if (userDTO.getPassword().equals(pwd) && userDTO.getEmail().equals(txtEmail.getText())) {
                NAME_USER = userDTO.getName();
                final Stage window = (Stage) pane.getScene().getWindow();
                window.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.getIcons().add(new Image("/view/assets/img/icon.png"));
                stage.setTitle("Dashboard");
                stage.show();

            }
        }
    }

    public void btnTxtEmail(ActionEvent actionEvent) {
        for (UserDTO u : allUser) {
            if (txtEmail.getText().equals(u.getEmail())) {
                txtPwd.requestFocus();
            }
        }
    }

    public void btnTxtPwd(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    public void btnChBox(ActionEvent actionEvent) {
        if (chBox.isSelected()) {
            txtPwd.setVisible(false);
            txtPwdField.setVisible(true);
            txtPwdField.setText(txtPwd.getText());

        } else {
            txtPwd.setVisible(true);
            txtPwdField.setVisible(false);
            txtPwd.setText(txtPwdField.getText());
        }

    }
}
