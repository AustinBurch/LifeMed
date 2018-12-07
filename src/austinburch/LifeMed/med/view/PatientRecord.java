package austinburch.LifeMed.med.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import austinburch.LifeMed.med.model.MedicalOffice;
import austinburch.LifeMed.med.model.Patient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientRecord implements Initializable {

	@FXML
	private Button backBtn, logOutBtn, searchBtn;
	@FXML
	private TextField searchField;
	@FXML
	private Text searchTxt, nameTxt, ageTxt, diagnosisTxt, doctorTxt;
	@FXML
	protected ComboBox<String> bonesBox = new ComboBox<>();
	@FXML
	protected ComboBox<String> grantBox = new ComboBox<>();
	@FXML
	protected ComboBox<String> jonesBox = new ComboBox<>();
	@FXML
	protected ComboBox<String> hallBox = new ComboBox<>();

	private Alert nullAlert, notFoundAlert;
	private String patientName;
	ArrayList<Patient> bones = new ArrayList<>();
	ArrayList<Patient> grant = new ArrayList<>();
	ArrayList<Patient> jones = new ArrayList<>();
	ArrayList<Patient> hall = new ArrayList<>();

	public void show(ActionEvent event) throws IOException {
		Parent record = FXMLLoader
				.load(getClass().getResource("/austinburch/LifeMed/med/view/PatientRecord.FXML"));
		Scene recordScene = new Scene(record);
		Stage recordStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		recordStage.setScene(recordScene);

	}

	@FXML
	private void onLogOutAction() {
		Platform.exit();
	}

	@FXML
	private void onBackBtnAction(ActionEvent event) throws IOException {
		HomeWindow home = new HomeWindow();
		home.loadHomeWindow(event);
	}

	@FXML
	private void onSearchBtnAction() {
		try {
			patientName = String.valueOf(searchField.getText());
			nameTxt.setText(
					String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getPatientName()));
			ageTxt.setText((String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getAge())));
			diagnosisTxt
					.setText(String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getDiagnosis()));
			doctorTxt.setText(String.valueOf(MedicalOffice.getInstance().findDrByPatientName(patientName)));

		} catch (NullPointerException ex) {
			nullAlert = new Alert(AlertType.ERROR, "Please Enter An Existing Patient Name", ButtonType.OK);
			nullAlert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert bonesBox != null;
		assert grantBox != null;
		assert jonesBox != null;
		assert hallBox != null;

		bones = MedicalOffice.getInstance().getBonesList();
		loadComboBox(bones);
		grant = MedicalOffice.getInstance().getGrantList();
		loadComboBox(grant);
		jones = MedicalOffice.getInstance().getJonesList();
		loadComboBox(jones);
		hall = MedicalOffice.getInstance().getHallList();
		loadComboBox(hall);
	}

	private void loadComboBox(ArrayList<Patient> list) {

		Collections.sort(list, new PatientRecord.patientComparer());
		ArrayList<String> patNames = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			patNames.add(list.get(i).getPatientName());
		}

		if (list.equals(bones)) {
			bonesBox.getItems().addAll(patNames);
			bonesBox.setValue("Select");
		} else if (list.equals(grant)) {
			grantBox.getItems().addAll(patNames);
			grantBox.setValue("Select");
		} else if (list.equals(jones)) {
			jonesBox.getItems().addAll(patNames);
			jonesBox.setValue("Select");
		} else if (list.equals(hall)) {
			hallBox.getItems().addAll(patNames);
			hallBox.setValue("Select");
		}

	}

	@FXML
	private void onBonesBoxAction() {
		bonesBox.setValue(bonesBox.getSelectionModel().getSelectedItem());
		patientName = bonesBox.getValue();
		showPatientInfo(patientName);
	}

	@FXML
	private void onGrantBoxAction() {
		grantBox.setValue(grantBox.getSelectionModel().getSelectedItem());
		patientName = grantBox.getValue();
		showPatientInfo(patientName);
	}

	@FXML
	private void onHallBoxAction() {
		hallBox.setValue(hallBox.getSelectionModel().getSelectedItem());
		patientName = hallBox.getValue();
		showPatientInfo(patientName);
	}

	@FXML
	private void onJonesBoxAction() {
		jonesBox.setValue(jonesBox.getSelectionModel().getSelectedItem());
		patientName = jonesBox.getValue();
		showPatientInfo(patientName);
	}

	private void showPatientInfo(String patientName) {
		nameTxt.setText(String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getPatientName()));
		ageTxt.setText((String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getAge())));
		diagnosisTxt.setText(String.valueOf(MedicalOffice.getInstance().findPatientByName(patientName).getDiagnosis()));
		doctorTxt.setText(String.valueOf(MedicalOffice.getInstance().findDrByPatientName(patientName)));

	}
	
	
	public static class patientComparer implements Comparator<Patient> {

		@Override
		public int compare(Patient pat1, Patient pat2) {
			return pat1.getPatientName().compareTo(pat2.getPatientName());
		}

	}
}
