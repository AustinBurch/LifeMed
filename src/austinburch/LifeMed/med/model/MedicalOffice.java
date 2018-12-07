package austinburch.LifeMed.med.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.fxml.FXML;

public class MedicalOffice {
	private static MedicalOffice instance = new MedicalOffice();
	private ArrayList<Patient> patients = new ArrayList<>();
	private ArrayList<Doctor> doctors = new ArrayList<>();
	private ArrayList<Admin> admins = new ArrayList<>();
	@FXML
	private ArrayList<Patient> bones = new ArrayList<>();
	@FXML
	private ArrayList<Patient> grant = new ArrayList<>();
	@FXML
	private ArrayList<Patient> jones = new ArrayList<>();
	@FXML
	private ArrayList<Patient> hall = new ArrayList<>();

	/*
	 * Gets the Singleton Medical Office Instance
	 * 
	 * @return Returns Singleton Medical Office Instance
	 */
	public static MedicalOffice getInstance() {
		return instance;
	}

	/**
	 * Create a new Patient and place in patients list
	 * 
	 * @param patientName
	 * @param age
	 * @param diagnosis
	 * @return Returns true if patient name is created or false if patient already
	 *         exists
	 */
	public boolean createPatient(String firstName, String lastName, int age, String diagnosis, String doctor) {
		if (findPatientByName(firstName + " " + lastName) != null) {
			return false;
		}

		Patient patient = new Patient(firstName, lastName, age, diagnosis);
		addToDrArrays(patient, doctor);
		return patients.add(patient);
	}

	public void addToDrArrays(Patient patient, String doctor) {
		switch (doctor) {
		case "Dr. Bones":
			bones.add(patient);
			break;
		case "Dr. Grant":
			grant.add(patient);
			break;
		case "Dr. Jones":
			jones.add(patient);
			break;
		case "Dr. Hall":
			hall.add(patient);
			break;
		}
	}

	/**
	 * Uses stream method on patients list which returns a patient name that equals
	 * the predicate and places the name in an Optional container. Then executes if
	 * statement if the name is present. Sets pat value with match and returns pat.
	 * 
	 * @param patientName
	 * @return Returns the patient name if found;
	 */
	public Patient findPatientByName(String patientName) {
		Patient pat = null;
		Optional<Patient> match = patients.stream().filter(e -> e.getPatientName().equals(patientName)).findFirst();
		if (match.isPresent()) {
			pat = match.get();
		}
		return pat;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllPatientNames() {
		ArrayList<String> patientNames = new ArrayList<>();
		patients.stream().forEach(e -> patientNames.add(e.getPatientName()));
		return patientNames;
	}

	public String findDrByPatientName(String patientName) {
		String docName = null;
		for (Patient pat : bones) {
			if (pat.getPatientName().equals(patientName)) {
				docName = "Dr. Bones";
			}
		}
		for (Patient pat : grant) {
			if (pat.getPatientName().equals(patientName)) {
				docName = "Dr. Grant";
			}
		}
		for (Patient pat : jones) {
			if (pat.getPatientName().equals(patientName)) {
				docName = "Dr. Jones";
			}
		}
		for (Patient pat : hall) {
			if (pat.getPatientName().equals(patientName)) {
				docName = "Dr. Hall";
			}
		}

		return docName;

	}

	/**
	 * 
	 * @param drName
	 * @param specialty
	 * @return
	 */
	public boolean createDr(String drName, String specialty) {
		if (findDrByName(drName) != null) {
			return false;
		}

		Doctor doctor = new Doctor(drName, specialty);

		return doctors.add(doctor);
	}

	/**
	 * 
	 * @param drName
	 * @return
	 */
	public Doctor findDrByName(String drName) {
		Doctor dr = null;
		Optional<Doctor> match = doctors.stream().filter(e -> e.getDrName().equals(drName)).findFirst();
		if (match.isPresent()) {
			dr = match.get();
		}
		return dr;
	}

	public String getDoctorSpecialty(String drName) {
		Doctor doc = null;
		Optional<Doctor> match = doctors.stream().filter(e -> e.getDrName().equals(drName)).findFirst();
		if (match.isPresent()) {
			doc = match.get();
		}
		return doc.getSpecialty();
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllDrNames() {
		ArrayList<String> doctorNames = new ArrayList<>();
		doctors.stream().forEach(e -> doctorNames.add(e.getDrName()));
		return doctorNames;
	}

	public void findDoctorByPatient(String patientName) {

		// Optional<Patient> match = bones.stream().filter(e ->
		// e.getPatientName().equals(patientName));
	}

	/**
	 * 
	 * @param adminName
	 * @param password
	 * @return
	 */
	public boolean createAdmin(String adminName, String password) {
		if (findAdminByName(adminName) != null) {
			return false;
		}

		Admin admin = new Admin(adminName, password);
		return admins.add(admin);
	}

	/**
	 * 
	 * @param adminName
	 * @return
	 */
	public Admin findAdminByName(String adminName) {
		Admin adm = null;
		Optional<Admin> match = admins.stream().filter(e -> e.getAdminName().equals(adminName)).findFirst();
		if (match.isPresent()) {
			adm = match.get();
		}
		return adm;
	}

	public void loadPatients(String filePath) throws FileNotFoundException {
		Scanner input = new Scanner(new File(filePath));

		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			String firstName = fields[0];
			String lastName = fields[1];
			int patientAge = Integer.parseInt(fields[2]);
			String patDiagnosis = fields[3];
			String doctor = fields[4];

			createPatient(firstName, lastName, patientAge, patDiagnosis, doctor);

		}

	}

	public void loadDoctors(String filePath) throws FileNotFoundException {
		Scanner input = new Scanner(new File(filePath));

		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			String doctorName = fields[0];
			String specialty = fields[1];

			createDr(doctorName, specialty);
		}
	}

	public void loadAdmins(String filePath) throws FileNotFoundException {
		Scanner input = new Scanner(new File(filePath));

		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			String username = fields[0];
			String password = fields[1];

			createAdmin(username, password);
		}
	}


	  public void printPatientDrArray(String doctor) {
	  System.out.printf("Patient Records: %n"); switch(doctor) { case "Dr. Bones":
	  for(Patient patients : bones) {
	  
	  System.out.printf("%s  %d   %s%n", patients.getPatientName(),
	  patients.getAge(), patients.getDiagnosis()); } break; case "Dr. Grant":
	  for(Patient patients : grant) { System.out.printf("%s  %d   %s%n",
	  patients.getPatientName(), patients.getAge(), patients.getDiagnosis()); }
	  break; case "Dr. Jones": for(Patient patients : jones) {
	  System.out.printf("%s  %d   %s%n", patients.getPatientName(),
	  patients.getAge(), patients.getDiagnosis()); } break; case "Dr. Hall":
	  for(Patient patients : hall) { System.out.printf("%s  %d   %s%n",
	  patients.getPatientName(), patients.getAge(), patients.getDiagnosis()); }
	  break; }
	  
	 }
	 
	  
	  public ArrayList<Patient> getBonesList()
	  {
		  return bones;
	  }
	  
	  public ArrayList<Patient> getGrantList()
	  {
		  return grant;
	  }
	  
	  public ArrayList<Patient> getJonesList()
	  {
		  return jones;
	  }
	  
	  public ArrayList<Patient> getHallList()
	  {
		  return hall;
	  }

	public void printPatientsArray() {
		System.out.printf("Patient Records: %n");
		for (Patient patient : patients) {

			System.out.printf("%s     %d    %s%n", patient.getPatientName(), patient.getAge(), patient.getDiagnosis());
		}
	}

	public void printDrArray() {
		System.out.printf("Doctor Records: %n");
		for (Doctor dr : doctors) {
			System.out.printf("%s   %s%n", dr.getDrName(), dr.getSpecialty());
		}
	}

	public void printAdminArray() {
		System.out.printf("%nAdmin Records: %n");
		for (Admin ad : admins) {
			System.out.printf("%s   %s%n", ad.getAdminName(), ad.getAdminPassword());
		}
	}

}
