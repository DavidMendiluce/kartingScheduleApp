package practica2Evaluacion2;

public class Conductor {
  String nombre;
  String dni;
  int edad;
  double dinero;
  String vipNoVip;
  
  public Conductor(String nombre, int edad, String dni, double dinero, String vipNoVip) {
	  this.nombre=nombre;
	  this.dni=dni;
	  this.edad=edad;
	  this.dinero=dinero;
	  this.vipNoVip = vipNoVip;
  }
  
  public double getDinero() {
	  return this.dinero;
  }
  
  public String getDni() {
	  return this.dni;
  }
  
  public String getNombre() {
	  return this.nombre;
  }
  
  public void restarDinero() {
	  if(this.vipNoVip.equals("VIP")) {
		  this.dinero = this.dinero - 40;
	  } else if(this.vipNoVip.equals("NOVIP")) {
		  this.dinero = this.dinero - 25;
	  }
  }

public int getEdad() {
	// TODO Auto-generated method stub
	return this.edad;
}
  
  
}
