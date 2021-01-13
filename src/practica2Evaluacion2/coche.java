package practica2Evaluacion2;

import java.util.Date;

public class coche {
	protected boolean isVehicleRented;
	String letraCoche;
	
	public coche(String letraCoche) {
		this.letraCoche = letraCoche;
	}
	
	public String getLetraCoche() {
		return this.letraCoche;
	}
	
	public boolean getRentStatus() {
        return this.isVehicleRented;
    }
	
	public void rentVehicle() {
        this.isVehicleRented = true;
        System.out.println("El kart: " + this.letraCoche + "Ha sido alquilado.");
        
    }
}
