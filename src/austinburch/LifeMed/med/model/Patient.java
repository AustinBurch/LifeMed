package austinburch.LifeMed.med.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import austinburch.LifeMed.med.view.PatientRecord;

public class Patient {

	private String firstName;
	private String lastName;
	private int age;
	private String diagnosis;
	
	public Patient(String firstName, String lastName, int age, String diagnosis)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.diagnosis = diagnosis;
	}
	
	
	public String getPatientName()
	{
	
		return firstName + " " + lastName;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getDiagnosis() 
	{
		return diagnosis;
	}
	
	
	public void sort(ArrayList<Patient> patientDrList)
	{
		 
			Collections.sort(patientDrList, new PatientRecord.patientComparer());
		
		
	}
	
	
	
	
	
}
