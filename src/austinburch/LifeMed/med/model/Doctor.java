package austinburch.LifeMed.med.model;

import java.util.ArrayList;

import javafx.fxml.FXML;

public class Doctor {
	
	private String drName;
	private String specialty;
	
	
	public Doctor(String drName, String specialty)
	{
		this.drName = drName;
		this.specialty = specialty;
	}
	
	public String getDrName()
	{
		return drName;
	}
	
	public String getSpecialty()
	{
		return specialty;
	}
	
	
	
	
}
