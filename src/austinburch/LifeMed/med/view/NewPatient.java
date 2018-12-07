package austinburch.LifeMed.med.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import austinburch.LifeMed.med.model.MedicalOffice;
import austinburch.LifeMed.med.model.Patient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewPatient implements Initializable {

	Stage patientStage;

	@FXML
	private Button addBtn, logOutBtn, backBtn;
	@FXML
	private TextField firstField, lastField, ageField, diagnosisField;
	@FXML
	private Text firstTxt, lastTxt, ageTxt, diagnosisTxt, drError;

	private Alert patientAlert, inputAlert;

	@FXML
	protected ComboBox<String> nameBox = new ComboBox<>();

	@FXML
	public void show(ActionEvent event) throws IOException {
		Parent patient = FXMLLoader
				.load(getClass().getResource("/austinburch/LifeMed/med/view/NewPatient.fxml"));
		Scene patientScene = new Scene(patient);
		patientStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		patientStage.setScene(patientScene);
		// addToComboBox();
	}

	@FXML
	public void setFirstText() {
		firstTxt.setText(firstField.getText());
	}

	@FXML
	public void setLastText() {
		lastTxt.setText(lastField.getText());
	}

	@FXML
	public void setAgeText() {
		ageTxt.setText(ageField.getText());
	}

	@FXML
	public void setDiagnosisText() {
		diagnosisTxt.setText(diagnosisField.getText());
	}

	@FXML
	public void onAddBtnAction(ActionEvent event) {
		try {
			addNewPatient();
		} catch (NumberFormatException ex) {
			inputAlert = new Alert(AlertType.ERROR, "Please Fill Out The Empty Fields Or Click Back", ButtonType.OK);
			inputAlert.showAndWait();
		}
	}

	@FXML
	public void onBackBtnAction(ActionEvent event) throws IOException {
		HomeWindow home = new HomeWindow();
		home.loadHomeWindow(event);
	}

	@FXML
	private void onLogOutBtnAction() {
		Platform.exit();
	}

	@FXML
	private void onNameBoxAction() {
		nameBox.setValue(nameBox.getSelectionModel().getSelectedItem());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert nameBox != null;

		nameBox.getItems().clear();
		for (int i = 0; i < MedicalOffice.getInstance().getAllDrNames().size(); i++) {
			nameBox.getItems().addAll(MedicalOffice.getInstance().getAllDrNames().get(i) + " - " + MedicalOffice
					.getInstance().getDoctorSpecialty(MedicalOffice.getInstance().getAllDrNames().get(i)));
		}

		nameBox.setValue("Select");

	}

	public void addNewPatient() {
		
		if (!firstField.getText().equals(null) && !lastField.getText().equals(null)
				&& !ageField.getText().equals(null) && !diagnosisField.getText().equals(null)) {
			int age = Integer.parseInt(ageField.getText());
			String doctor = nameBox.getSelectionModel().getSelectedItem().toString();
			String patientName = firstField.getText() + " " + lastField.getText();
			String doc = null;

			switch (doctor) {
			case "Dr. Bones - Orthopedic":
				doc = "Dr. Bones";
				MedicalOffice.getInstance().createPatient(firstField.getText(), lastField.getText(), age,
						diagnosisField.getText(), doc);
				break;
			case "Dr. Grant - Neurologist":
				doc = "Dr. Grant";
				MedicalOffice.getInstance().createPatient(firstField.getText(), lastField.getText(), age,
						diagnosisField.getText(), doc);
				break;
			case "Dr. Jones - Cardiologist":
				doc = "Dr. Jones";
				MedicalOffice.getInstance().createPatient(firstField.getText(), lastField.getText(), age,
						diagnosisField.getText(), doc);
				break;
			case "Dr. Hall - Dermatologist":

				doc = "Dr. Hall";
				MedicalOffice.getInstance().createPatient(firstField.getText(), lastField.getText(), age,
						diagnosisField.getText(), doc);
				break;
			default:
				drError.setVisible(true);
				break;
			}
		} else {
			patientAlert = new Alert(AlertType.ERROR, "Please Enter All Patient Information", ButtonType.OK);
		}
	}

}
