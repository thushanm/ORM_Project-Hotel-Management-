package lk.ijse.hotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.ijse.hotel.bo.BOFactory;
import lk.ijse.hotel.bo.BOType;
import lk.ijse.hotel.bo.custom.RoomBO;
import lk.ijse.hotel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotel.dto.RoomDTO;
import lk.ijse.hotel.tm.RoomTM;
import lk.ijse.hotel.util.MyAlerts;
import lk.ijse.hotel.util.Regex;
import lk.ijse.hotel.util.TextFields;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
public class RoomController {
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public Label lblRoomID;
    public TableColumn colAction;
    public TableColumn colQty;
    public TableColumn colKeyMoney;
    public TableColumn colType;
    public TableColumn colID;
    public TableView<RoomTM> mainTable;
    public JFXButton btnSave;
boolean isUpdate;
    ObservableList<RoomTM> obRoomList= FXCollections.observableArrayList();
ModelMapper modelMap=new ModelMapper();
    RoomBO roomBO=(RoomBOImpl) BOFactory.getInstance().getBO(BOType.ROOM);
    public void initialize(){
        loadTable();
    }
public void loadTable(){
        lblRoomID.setText(roomBO.generateIDRoom());
        if(obRoomList.size()>=0){
            obRoomList.clear();
        }
    colAction.setCellValueFactory(new PropertyValueFactory<>("btnBox"));
    colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
    colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    colID.setCellValueFactory(new PropertyValueFactory<>("roomID"));

    obRoomList.addAll( (List<RoomTM>)modelMap.map(roomBO.getAllRoom(), new TypeToken<List<RoomTM>>() {}.getType()));
    for (RoomTM roomTM : obRoomList) {
        roomTM.setBtnBox(getActionBox(roomTM));
    }
    mainTable.setItems(obRoomList);
    mainTable.refresh();



}

    private HBox getActionBox(RoomTM roomTM) {
        Button btnDelete=MyAlerts.getDeleteBtn();
        btnDelete.setOnAction(actionEvent -> {
              if(MyAlerts.getAlertConfirmation(404,"Are You Sure Delete This").get()==ButtonType.APPLY){
          if(roomBO.deleteRoom(roomTM.getRoomID())){
             obRoomList.remove(roomTM);
             loadTable();
              new Alert(Alert.AlertType.INFORMATION,"Delete Successfully").show();
          }
              }
        });
        Button btnEdit=MyAlerts.getEditBtn();
        btnEdit.setOnAction(actionEvent ->{
            lblRoomID.setText(roomTM.getRoomID());
            txtRoomType.setText(roomTM.getType());
            txtKeyMoney.setText(roomTM.getKey_money());
            txtQty.setText(roomTM.getQty()+"");
            mainTable.setDisable(true);
            btnSave.setText("Update");
            btnSave.setStyle("-fx-background-color: green");
            isUpdate=true;

        });
        Pane pane=new Pane();
        pane.setMinWidth(4);
        return new HBox(btnEdit,pane,btnDelete);
    }

    public void btnSave(ActionEvent actionEvent) {
        if(isValid()){
            final RoomDTO roomDTO1 = new RoomDTO(lblRoomID.getText(),txtRoomType.getText(),txtKeyMoney.getText(),Integer.parseInt(txtQty.getText()));
            if(!isUpdate){
                if(roomBO.saveRoom(roomDTO1)!=null){
                    new Alert(Alert.AlertType.INFORMATION,"saved").show();
                }
            }else{
               if(roomBO.updateRoom(roomDTO1)!=null){
                    new Alert(Alert.AlertType.INFORMATION,"updated").show();
                    mainTable.setDisable(false);
               }
            }
                   loadTable();
            isUpdate=false;
            clearTextFields();
        }
    }

    private void clearTextFields() {
        lblRoomID.setText(roomBO.generateIDRoom());
        txtKeyMoney.setText("");
        txtQty.setText("");
        txtRoomType.setText("");
    }

    private boolean isValid() {

            if(txtRoomType.getText().trim().isEmpty()){
             new Alert(Alert.AlertType.WARNING,"invalid Input Please Enter Valid Room type").show();
             return false;
            }else{
                if(!Regex.isTextFieldValid(TextFields.INTEGER_DECIMAL,txtKeyMoney.getText())){
             new Alert(Alert.AlertType.WARNING,"invalid Input Please Enter Valid Key Money").show();

                    return false;
                }else{
                    if(!Regex.isTextFieldValid(TextFields.INTEGER,txtQty.getText())){
             new Alert(Alert.AlertType.WARNING,"invalid Input Please Enter Valid Qty").show();
                        return false;
                    }
                }
            }
            return true;
    }
}
