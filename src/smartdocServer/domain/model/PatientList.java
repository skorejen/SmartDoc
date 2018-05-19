package smartdocServer.domain.model;

import java.util.ArrayList;

public class PatientList 
{
	private ArrayList<Patient> patients;

	public PatientList() 
	{
		patients = new ArrayList<>();
	}
	
	public int getNumberOfPatients()
	{
		return patients.size();
	}
	
	public void addPatient(Patient p)
	{
		patients.add(p);
	}
	
	public Patient getPatient(int index)
	{
		return patients.get(index);
	}
	
	public void removePatient(int index)
	{
		patients.remove(index);
	}
	
	public boolean isEmpty()
	{
		if(patients.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Patient getPatientByFname(String fname)
	{
		for(int i = 0;i<patients.size();i++)
		{
			if(patients.get(i).getFname().equals(fname))
			{
				return patients.get(i);
			}
		}
		
		return null;
	}
	
	public Patient getPatientByLname(String lname)
	{
		for(int i = 0;i<patients.size();i++)
		{
			if(patients.get(i).getLname().equals(lname))
			{
				return patients.get(i);
			}
		}
		
		return null;
	}
	
	public Patient getPatientByCpr(String cpr)
	{
		for(int i = 0;i<patients.size();i++)
		{
			if(patients.get(i).getCpr().equals(cpr))
			{
				return patients.get(i);
			}
		}
		
		return null;
	}
	
	}

