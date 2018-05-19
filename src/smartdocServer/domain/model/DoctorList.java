package smartdocServer.domain.model;

import java.util.ArrayList;

public class DoctorList {

	private ArrayList<Doctor> doctors;
	
	public DoctorList() {
		doctors = new ArrayList<>();
	}
		
		public int getNumberOfDoctors(){
			
			return doctors.size();
		}
		
		public void addDoctor(Doctor d){
			
			doctors.add(d);
		}
		
		public Doctor getDoctor(int index){
			
			return doctors.get(index);
		}
		
		public void removeDoctor(int index){
			
			doctors.remove(index);
		}
		
		public boolean isEmpty(){
			
			if(doctors.size() == 0){
				
				return true;
			}
			else{
				
				return false;
			}
		}
		
		public Doctor getDoctorByFname(String fname){
			
			for(int i = 0;i<doctors.size();i++){
				
				if(doctors.get(i).getFname().equals(fname)){
					
					return doctors.get(i);
				}
			}
			
			return null;
		}
		
		public Doctor getDoctorByLname(String lname){
			
			for(int i = 0;i<doctors.size();i++){
				
				if(doctors.get(i).getLname().equals(lname)){
					
					return doctors.get(i);
				}
			}
			
			return null;
		}
		
		public Doctor getDoctorByCpr(String cpr){
			
			for(int i = 0;i<doctors.size();i++){
				
				if(doctors.get(i).getCpr().equals(cpr)){
					
					return doctors.get(i);
				}
			}
			
			return null;
		}
		
		public Doctor getSpeciality(String speciality) {
			
			for(int i=0;i<doctors.size();i++) {
				
				if(doctors.get(i).getSpeciality().equals(speciality)) {
					return doctors.get(i);
				}
			}
			return null;
		}
		
}

