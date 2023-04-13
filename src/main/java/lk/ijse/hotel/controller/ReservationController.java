package lk.ijse.hotel.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.ijse.hotel.bo.BOFactory;
import lk.ijse.hotel.bo.BOType;
import lk.ijse.hotel.bo.custom.CustomBO;
import lk.ijse.hotel.bo.custom.ReservationBO;
import lk.ijse.hotel.bo.custom.RoomBO;
import lk.ijse.hotel.bo.custom.StudentBO;
import lk.ijse.hotel.bo.custom.impl.CustomBOImpl;
import lk.ijse.hotel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hotel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotel.dto.ReservationDTO;
import lk.ijse.hotel.dto.RoomDTO;
import lk.ijse.hotel.dto.StudentDTO;
import lk.ijse.hotel.entity.Student;
import lk.ijse.hotel.tm.ReservationTM;
import lk.ijse.hotel.tm.RoomTM;
import lk.ijse.hotel.tm.StudentTM;
import lk.ijse.hotel.util.MyAlerts;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.Date;
import java.util.List;

public class ReservationController {

    boolean isUpdate;
    public TableView mainTable;
    public TableColumn colResID;
    public TableColumn colDate;
    public TableColumn colStudentID;
    public TableColumn colKeyMoney;
    public TableColumn colName;
    public TableColumn colRoomID;
    public TableColumn colRoomType;
    public TableColumn colStatus;
    public TableColumn colAction;
    public Label lblResID;
    public JFXComboBox<String> cmbStudent;
    public DatePicker dateRes;
    public JFXComboBox<String> cmbRoom;
    public JFXTextField txtStatus;
    public JFXButton btnSaveAndUpdate;
    CustomBO customBO=(CustomBOImpl) BOFactory.getInstance().getBO(BOType.CUSTOM);
    ReservationBO reservationBO=(ReservationBOImpl) BOFactory.getInstance().getBO(BOType.RESERVATION);
    StudentBO studentBO=(StudentBOImpl) BOFactory.getInstance().getBO(BOType.STUDENT);
    RoomBO roomBO=(RoomBOImpl) BOFactory.getInstance().getBO(BOType.ROOM);
    ModelMapper modelMapper=new ModelMapper();
ObservableList<ReservationTM> obResList= FXCollections.observableArrayList();
    public void initialize(){
        loadComboBox();
        loadTable();

    }

    private void loadComboBox() {
        if (cmbRoom.getItems().size()>=0) {
            cmbRoom.getItems().clear();
        }
        if (cmbStudent.getItems().size()>=0) {
            cmbStudent.getItems().clear();
        }
        final List<RoomDTO> allRoom = roomBO.getAllRoom();
        for (RoomDTO room : allRoom) {
        cmbRoom.getItems().addAll(room.getRoomID());

        }
        for (StudentDTO st : studentBO.getAllStudent()) {
         cmbStudent.getItems().addAll(st.getStudentID());

        }

    }

    private void loadTable() {
        if (obResList.size()>=0) {
            obResList.clear();
        }

        lblResID.setText(reservationBO.generateIDReservation());
        colResID.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnBox"));
        final List<ReservationDTO> allReservation = reservationBO.getAllReservation();
        for (ReservationDTO r : allReservation) {
            final ReservationTM reservationTM = new ReservationTM(
                    r.getResID(),
                    r.getDate(),
                    r.getStatus(),
                    r.getStudent().getStudentID(),
                    r.getStudent().getName(),
                    r.getRoom().getRoomID(),
                    r.getRoom().getType(),
                    r.getRoom().getKey_money()
                    );
            reservationTM.setBtnBox( addActionBtn(reservationTM));
            for (int i = 0; i < cmbStudent.getItems().size(); i++) {
                if (cmbStudent.getItems().get(i).equals(r.getStudent().getStudentID())) {
                    cmbStudent.getItems().remove(i);
                }
            }
            for (int i = 0; i < cmbRoom.getItems().size(); i++) {
                if (cmbRoom.getItems().get(i).equals(r.getRoom().getRoomID())) {
                    if (r.getRoom().getQty()<=0) {
                        cmbRoom.getItems().remove(i);
                    }
                }
            }
            obResList.add(reservationTM);
        }
            mainTable.setItems(obResList);
            mainTable.refresh();

    }

    private HBox addActionBtn(ReservationTM r) {
        Button btnDelete= MyAlerts.getDeleteBtn();
        btnDelete.setOnAction(event->{
            if(MyAlerts.getAlertConfirmation(404,"Are You Sure Delete This").get()==ButtonType.APPLY){
                if(reservationBO.deleteReservation(reservationBO.searchReservation(r.getResID()))){
                    obResList.remove(r);
                    loadComboBox();
                    loadTable();

                }

            }
        });
        Button btnEdit=MyAlerts.getEditBtn();
        btnEdit.setOnAction(event ->{
            if(MyAlerts.getAlertConfirmation(202,"Are You Sure Edit This").get()==ButtonType.APPLY){
                lblResID.setText(r.getResID());
                cmbStudent.setValue(r.getStudentID());
                cmbRoom.setValue(r.getRoomID());
                txtStatus.setText(r.getStatus());
                dateRes.setValue(r.getDate().toLocalDate());
                mainTable.setDisable(true);
                isUpdate=true;
            }
        });
        Pane pane=new Pane();
        pane.setMinWidth(4);
        return new HBox(btnEdit,pane,btnDelete);
    }

    public void btnSaveAndUpdate(ActionEvent actionEvent) {
        if(isValid()){
            final StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentID(cmbStudent.getValue());
            final RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomID(cmbRoom.getValue());
            ReservationDTO reservationDTO=new ReservationDTO(lblResID.getText(), Date.valueOf(dateRes.getValue()),txtStatus.getText(),studentDTO,roomDTO);
            if(!isUpdate){
                if(reservationBO.saveReservation(reservationDTO)!=null){
            new Alert(Alert.AlertType.INFORMATION,"Saved").show();
                }
            }else{
                isUpdate=false;
                if(reservationBO.updateReservation(reservationDTO)!=null){
                    new Alert(Alert.AlertType.INFORMATION,"updated").show();
                }
                mainTable.setDisable(false);
            }
            clearTextField();
            loadTable();
        }

    }

    private void clearTextField() {
        cmbRoom.getSelectionModel().clearSelection();
        cmbStudent.getSelectionModel().clearSelection();
        txtStatus.setText("");
        dateRes.setValue(null);
    }

    private boolean isValid() {
        if(cmbRoom.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"Please Select Room ID").show();
            return false;
        }else{
            if(cmbStudent.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"Please Select Student ID").show();
                return false;
            }else{
                if(txtStatus.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Write Status").show();
                    return false;
                }
                else{
                    if(dateRes.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"Please Select Date").show();
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
