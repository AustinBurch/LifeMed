package austinburch.LifeMed.med.view;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeWindow{

	
	private NewPatient newPatient = new NewPatient();
	private PatientRecord patientRecord = new PatientRecord();
	private AnalysisWindow officeAnalysis = new AnalysisWindow();
	
	
	@FXML private BorderPane homePane;
	@FXML private Button analysisBtn, recordsBtn, newPatientBtn, billingBtn, logOutBtn;
	
	@FXML
	private void onNewPatientAction(ActionEvent event) throws IOException
	{	
		newPatient.show(event);
	}
	
	@FXML
	private void onPatientRecordAction(ActionEvent event) throws IOException
	{
		patientRecord.show(event);
	}
	
	@FXML void onAnalysisAction(ActionEvent event) throws IOException
	{
		officeAnalysis.show(event);
	}
	
	@FXML
	private void onLogOutAction(ActionEvent event) throws IOException
	{
		Platform.exit();
	}
	
	
	public void loadHomeWindow(ActionEvent event) throws IOException
	{
		double width = 1200;
		double height = 720;
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Parent homeparent =FXMLLoader.load(getClass().getResource("/austinburch/LifeMed/med/view/HomeWindow.fxml"));
		Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		homeStage.setX((screenBounds.getWidth() - width)/2);
		homeStage.setY((screenBounds.getHeight() - height)/2);
		Scene homescene = new Scene(homeparent);
		homeStage.setScene(homescene);
		homeStage.show();
	}
	
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	

	
	
}
