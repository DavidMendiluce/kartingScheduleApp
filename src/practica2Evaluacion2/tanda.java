package practica2Evaluacion2;



public class tanda {
	double precio;
	int hora;
	int numTanda;
	
  public tanda(double precio, int hora, int numTanda) {
	  this.precio = precio;
	  this.hora = hora;
	  this.numTanda = numTanda;
	  
  }
  
  public double getTandaPrecio() {
	  return this.precio;
  }
}
