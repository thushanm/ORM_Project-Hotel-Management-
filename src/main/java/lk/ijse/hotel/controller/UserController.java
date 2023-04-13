package lk.ijse.hotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.ijse.hotel.bo.BOFactory;
import lk.ijse.hotel.bo.BOType;
import lk.ijse.hotel.bo.custom.UserBO;
import lk.ijse.hotel.bo.custom.impl.UserBOImpl;
import lk.ijse.hotel.dto.UserDTO;
import lk.ijse.hotel.tm.UserTM;
import lk.ijse.hotel.util.MyAlerts;
import lk.ijse.hotel.util.Regex;
import lk.ijse.hotel.util.TextFields;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserController {

    public TableView mainTable;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colAction;
    public Label lblUserID;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPwd;
    public JFXButton btnSaveAndUpdate;
    ObservableList<UserTM> obUserList= FXCollections.observableArrayList();
    UserBO userBO=(UserBOImpl) BOFactory.getInstance().getBO(BOType.USER);
    ModelMapper modelMapper=new ModelMapper();
    public void initialize(){
            loadTable();
    }
boolean isUpdate;
    private void loadTable() {
        if(obUserList.size()>0){
            obUserList.clear();
        }
        lblUserID.setText(userBO.generateIDUser());
         colID.setCellValueFactory(new PropertyValueFactory<>("userID"));
         colName.setCellValueFactory(new PropertyValueFactory<>("name"));
         colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         colAction.setCellValueFactory(new PropertyValueFactory<>("btnBox"));

        final List<UserDTO> allUser = userBO.getAllUser();
        for (UserDTO u : allUser) {
            final UserTM userTM = new UserTM(u.getUserID(), u.getName(), u.getEmail(), u.getPassword());
            userTM.setBtnBox(getActionBtn(userTM));
            obUserList.add(userTM);
        }
        mainTable.setItems(obUserList);
        mainTable.refresh();
    }

    private HBox getActionBtn(UserTM u) {
        Button btnDelete= MyAlerts.getDeleteBtn();
        btnDelete.setOnAction(event ->{
            if(MyAlerts.getAlertConfirmation(404,"Are You Sure Delete This..?").get()==ButtonType.APPLY){
                if(userBO.deleteUser(u.getUserID())){
                    obUserList.remove(u);
                    loadTable();
                }
            }
        });
        Button btnEdit=MyAlerts.getEditBtn();
        btnEdit.setOnAction(event ->{
            lblUserID.setText(u.getUserID());
            txtName.setText(u.getName());
            txtEmail.setText(u.getEmail());
            txtPwd.setText(u.getPassword());
            mainTable.setDisable(true);
            isUpdate=true;
            btnSaveAndUpdate.setStyle("-fx-background-color: green");
            btnSaveAndUpdate.setText("Update");
        });
        Pane pane=new Pane();
        pane.setMinWidth(4);
        return new HBox(btnEdit,pane,btnDelete);
    }

    public void btnSaveAndUpdate(ActionEvent actionEvent) {
        if(isValid()){
            final UserDTO userDTO = new UserDTO(lblUserID.getText(), txtName.getText(), txtEmail.getText(), txtPwd.getText());
            if(!isUpdate){
                if (userBO.saveUser(userDTO)!=null) {
                    new Alert(Alert.AlertType.INFORMATION,"Saved").show();
                }
            }else{
                if(userBO.updateUser(userDTO)!=null){
                    mainTable.setDisable(false);
                    btnSaveAndUpdate.setStyle("-fx-background-color: blue");
                    btnSaveAndUpdate.setText("Save");
                }
            }
                    isUpdate=false;
            clearTextField();
            loadTable();
        }
    }

    private void clearTextField() {
        txtName.setText("");
        txtEmail.setText("");
        txtPwd.setText("");
    }

    private boolean isValid() {
        if(!Regex.isTextFieldValid(TextFields.NAME,txtName.getText())){
            new Alert(Alert.AlertType.WARNING,"Invalid Name").show();
            return false;
        }else{
            if(!Regex.isTextFieldValid(TextFields.EMAIL,txtEmail.getText())){
            new Alert(Alert.AlertType.WARNING,"Invalid Email").show();
            return false;
            }else{
                if(!Regex.isTextFieldValid(TextFields.PWD,txtPwd.getText())){
            new Alert(Alert.AlertType.WARNING,"Password not Strong").show();
                    return false;
                }
            }
        }
        return true;
    }
}
