package austinburch.LifeMed.med.view;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import austinburch.LifeMed.med.model.MedicalOffice;
public class LoginWindow implements EventHandler<ActionEvent> {

	Parent root; 
	Stage stage;
	
	
	@FXML private Button loginBtn, cancelBtn;
	@FXML private Label userLabel;
	@FXML private Label passLabel;
	@FXML private HBox buttonBox;
	@FXML private TextField username, password;
	@FXML private HomeWindow homeWindow = new HomeWindow();
	
	public Alert usernameAlert, passwordAlert;
	
	public LoginWindow()
	{
		this(new Stage());
	}
	
	public LoginWindow(Stage stage)
	{
		//loginStage = new Stage();
		
	
		
	}
	
	@FXML
	private void handleLoginButtonAction(ActionEvent event) throws IOException {
		try {
			String adminName = username.getText();
			String passwd = password.getText();
		
			if (adminName.equals(MedicalOffice.getInstance().findAdminByName(adminName).getAdminName()) && passwd.equals(MedicalOffice.getInstance().findAdminByName(adminName).getAdminPassword())) 
			{
				homeWindow.loadHomeWindow(event);
				
			} else if (!adminName.equals(MedicalOffice.getInstance().findAdminByName(adminName).getAdminName())
					|| (!passwd.equals(MedicalOffice.getInstance().findAdminByName(adminName).getAdminPassword()))) {
				Alert incorrectAlert = new Alert(AlertType.ERROR, "Incorrect username or password", ButtonType.OK);
				incorrectAlert.showAndWait();
			}

		} catch (NullPointerException ex) {
			Alert loginAlert = new Alert(AlertType.ERROR, "Enter a username and password", ButtonType.OK);
			loginAlert.showAndWait();
		}
	}


	@FXML
	protected void handleCancelButtonAction(ActionEvent event) {
		Platform.exit();
	}
	

	
	public void show(Stage primaryStage) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("/austinburch/LifeMed/med/view/LoginWindow.fxml"));
		stage = primaryStage;
		stage.setTitle("Life Med: A Medical Management System");
		stage.setScene(new Scene(root, 800, 500));
		stage.show();
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}


	
	
}


