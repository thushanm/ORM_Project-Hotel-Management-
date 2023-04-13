package lk.ijse.hotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.ijse.hotel.bo.BOFactory;
import lk.ijse.hotel.bo.BOType;
import lk.ijse.hotel.bo.custom.StudentBO;
import lk.ijse.hotel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotel.dto.StudentDTO;
import lk.ijse.hotel.tm.StudentTM;
import lk.ijse.hotel.util.MyAlerts;
import lk.ijse.hotel.util.Regex;
import lk.ijse.hotel.util.TextFields;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.Date;
import java.util.List;

public class StudentController {

    public DatePicker dateDob;
    public ToggleGroup gender;
    public JFXButton btnSaveAndUpdate;
    ObservableList<StudentTM> obStudentList = FXCollections.observableArrayList();
    StudentBO studentBO = (StudentBOImpl) BOFactory.getInstance().getBO(BOType.STUDENT);
    ModelMapper modelMapper = new ModelMapper();
    boolean isUpdate;
    @FXML
    private TableView<StudentTM> mainTable;
    @FXML
    private TableColumn colStudentID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colAddress;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colDob;
    @FXML
    private TableColumn colGender;
    @FXML
    private TableColumn colAction;
    @FXML
    private Label lblStudentID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXRadioButton radioBtnMale;
    @FXML
    private JFXRadioButton radioBtnFemale;

    public void initialize() {
        loadTable();
    }

    private void loadTable() {
        lblStudentID.setText(studentBO.generateIDStudent());
        if (obStudentList.size()>=0) {
            obStudentList.clear();
        }
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnBox"));
        List<StudentTM> list = modelMapper.map(studentBO.getAllStudent(), new TypeToken<List<StudentTM>>() {
        }.getType());
        obStudentList.addAll(list);
        for (StudentTM s : obStudentList) {
            s.setBtnBox(getActionBox(s));
        }
        mainTable.setItems(obStudentList);
        mainTable.refresh();


    }

    private HBox getActionBox(StudentTM s) {
        Button btnDelete = MyAlerts.getDeleteBtn();
        btnDelete.setOnAction(actionEvent -> {
            if (MyAlerts.getAlertConfirmation(404, "Are You Sure Delete This").get() == ButtonType.APPLY) {
                if (studentBO.deleteStudent(s.getStudentID())) {
                    obStudentList.remove(s);
                    loadTable();
                    new Alert(Alert.AlertType.INFORMATION, "Delete Successfully").show();
                }
            }
        });
        Button btnEdit = MyAlerts.getEditBtn();
        btnEdit.setOnAction(actionEvent -> {
            txtName.setText(s.getName());
            txtAddress.setText(s.getAddress());
            txtContact.setText(s.getTel());
            lblStudentID.setText(s.getStudentID());
            dateDob.setValue(s.getDob().toLocalDate());
            btnSaveAndUpdate.setText("Update");
            btnSaveAndUpdate.setStyle("-fx-background-color: green");
            isUpdate = true;
            mainTable.setDisable(true);
        });
        Pane pane = new Pane();
        pane.setMinWidth(4);
        return new HBox(btnEdit, pane, btnDelete);
    }


    public void btnSave(ActionEvent actionEvent) {
        if (isValid()) {
            final RadioButton selectedToggle = (RadioButton) gender.getSelectedToggle();
            final String genderText = selectedToggle.getText();
            final StudentDTO studentDTO = new StudentDTO(
                    lblStudentID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    Date.valueOf(dateDob.getValue()),
                    genderText
            );
            if (!isUpdate) {
                if (studentBO.saveStudent(studentDTO)!=null) {
                    new Alert(Alert.AlertType.INFORMATION,"Saved..").show();
                }
            }else{
                if (studentBO.updateStudent(studentDTO)!=null) {
                    new Alert(Alert.AlertType.INFORMATION,"Updated..").show();
                    mainTable.setDisable(false);
                }
                btnSaveAndUpdate.setText("Save");
                btnSaveAndUpdate.setStyle("-fx-background-color: blue");
            }
            clearTextFields();
            loadTable();
        }
        isUpdate=false;
    }

    private void clearTextFields() {
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        dateDob.setValue(null);



    }

    private boolean isValid() {
        if (!Regex.isTextFieldValid(TextFields.NAME, txtName.getText())) {
            new Alert(Alert.AlertType.WARNING, "Invalid Name").show();
            return false;
        } else {
            if (!Regex.isTextFieldValid(TextFields.ADDRESS, txtAddress.getText())) {
                new Alert(Alert.AlertType.WARNING, "Invalid Address").show();
                return false;
            } else {
                if (!Regex.isTextFieldValid(TextFields.PHONE, txtContact.getText())) {
                    new Alert(Alert.AlertType.WARNING, "Invalid Phone Number").show();
                    return false;
                } else {
                    if (dateDob.getValue() == null) {
                        new Alert(Alert.AlertType.WARNING, "Please Select Date").show();
                        return false;
                    }
                }
            }
        }
        return true;
    }
}