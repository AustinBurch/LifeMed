package austinburch.LifeMed.med.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import austinburch.LifeMed.med.model.MedicalOffice;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnalysisWindow implements Initializable{

	@FXML Label orthoLbl, cardioLbl, neuroLbl, dermaLbl;
	
	@FXML Pane paneView;
	
	@FXML Button logOutBtn, backBtn;
	
	private Stage analysisStage;
	
	private void loadGraph()
	{
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Specialty");
		yAxis.setLabel("Number of Patients");
		BarChart<String, Number> patientNumbers = new BarChart<String, Number>(xAxis,yAxis);
		XYChart.Series<String, Number> series = new Series<String, Number>();
		series.setName("Number of Patients");
		series.getData().add(new XYChart.Data<>("Orthopedic", MedicalOffice.getInstance().getBonesList().size()));
		series.getData().add(new XYChart.Data<>("Cardiology", MedicalOffice.getInstance().getGrantList().size()));
		series.getData().add(new XYChart.Data<>("Neurology", MedicalOffice.getInstance().getJonesList().size()));
		series.getData().add(new XYChart.Data<>("Dermatology", MedicalOffice.getInstance().getHallList().size()));
		patientNumbers.getData().add(series);
		patientNumbers.setMinWidth(800);
		patientNumbers.setMinHeight(500);
		
		paneView.getChildren().add(patientNumbers);
		
		
	}

	
	public void show(ActionEvent event) throws IOException {
		Parent analysis = FXMLLoader.load(getClass().getResource("/austinburch/LifeMed/med/view/AnalysisWindow.FXML"));
		Scene analysisScene = new Scene(analysis);
		Stage analysisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		analysisStage.setScene(analysisScene);
		
		
	}
	
	@FXML
	private void onBackBtnAction(ActionEvent event)
	{
		HomeWindow home = new HomeWindow();
		try {
			home.loadHomeWindow(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void onLogOutBtnAction()
	{
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadGraph();		
	}
	
//	public void setPatientData
}
