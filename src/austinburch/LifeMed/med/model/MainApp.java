package austinburch.LifeMed.med.model;

import java.io.FileNotFoundException;

import austinburch.LifeMed.med.view.LoginWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private LoginWindow loginWindow = new LoginWindow();
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		loginWindow.show(primaryStage);
		
	}
	public static void main(String[] args) throws FileNotFoundException 
	{
		MedicalOffice office = MedicalOffice.getInstance();
		
		office.loadPatients("Patients.csv");
		office.loadDoctors("Doctors.csv");
		office.loadAdmins("Admins.csv");
		
		
		Application.launch(MainApp.class,args);

		
	}
	
	

}		