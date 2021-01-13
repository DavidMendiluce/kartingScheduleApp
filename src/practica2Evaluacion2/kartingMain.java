package practica2Evaluacion2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;





public class kartingMain {
	
	
	static //dias que pasan, para imprimir poner mayor de 1
	int dia = 0;
	static int hora = 4;
	int numeroTanda=0;
  
  static ArrayList<ArrayList<Conductor>> arraysListaConductores = new ArrayList<ArrayList<Conductor>>();
  static ArrayList<coche> listaCoches = new ArrayList<coche>();
  static ArrayList<tanda> listaTandas = new ArrayList<tanda>();
  static ArrayList<Conductor> listaConductores = new ArrayList<Conductor>();
  static ArrayList<Conductor> listasConductores = new ArrayList<Conductor>();
  static ArrayList<ConductorTieneKart> listaConductorKart = new ArrayList<ConductorTieneKart>();
  static List<List<Conductor>> tandaConductores = new ArrayList<List<Conductor>>();
  
  public static void main(String[] args) throws IOException {
	  
	  String factura;
	  ArrayList<String> facturaArray = new ArrayList<String>();
	  
	 
	  listaCoches.add(new coche("A"));
	  listaCoches.add(new coche("B"));
	  listaCoches.add(new coche("C"));
	  listaCoches.add(new coche("D"));
	  listaCoches.add(new coche("E"));
	  listaCoches.add(new coche("F"));
	  listaCoches.add(new coche("G"));
	  listaCoches.add(new coche("H"));
	  listaCoches.add(new coche("I"));
	  listaCoches.add(new coche("J"));
	  
	  listaTandas.add(new tanda(25,16,1));
	  listaTandas.add(new tanda(25,17,2));
	  listaTandas.add(new tanda(25,18,3));
	  listaTandas.add(new tanda(25,19,4));
	  listaTandas.add(new tanda(40,20,5));
	  listaTandas.add(new tanda(40,21,6));
	  listaTandas.add(new tanda(40,22,7));
	  listaTandas.add(new tanda(40,23,8));
	  
	      
	      int numeroLineas = count("conductores1.csv");
	      System.out.println(numeroLineas);
	      read(numeroLineas);
	      int plazas = listaConductores.size();
	      if(plazas>80) {
	    	  while(plazas>80) {
	    		  int z = 0;
	    		  for(int i = plazas; i>=10; i = 10 - i) {
	    			  arraysListaConductores.add(new ArrayList<Conductor>());
	    			  for(int j = 0; j<9; j++) {
	    				  arraysListaConductores.get(z).add(listaConductores.get(j));
	    			  }
	    			  asignarTanda(facturaArray,arraysListaConductores.get(z), dia, hora);
	    			  z++;
	    			  hora++;
	    		  }
	    		  
	    		  
	    		  
	    		  plazas = plazas-80;
	    		  dia++;
	    		  
	    	  } 
	      } else if(plazas>10 && plazas<20) {
	    	  int z = 0;
    		  for(int i = 20; i>=10; i = i - 10) {
    			  arraysListaConductores.add(new ArrayList<Conductor>());
    			  for(int j = 0; j<9; j++) {
    				  arraysListaConductores.get(z).add(listaConductores.get(j));
    			  }
    			  asignarTanda(facturaArray,arraysListaConductores.get(z), dia, hora);
    			  z++;
    			  hora++;
    			  dia++;
    			  System.out.println("HELLO");
    		  }
    		  factura = arrayListStringToString(facturaArray);
    	      guardarFactura(factura);
    		  
    		  
    	  } else if(plazas>20 && plazas<30) {
    		  
    	  } else if(plazas>30 && plazas<40) {
    		  
    	  } else if(plazas>40 && plazas<50) {
    		  
    	  } else if(plazas>60 && plazas<70) {
    		  
    	  } else if(plazas>70 && plazas<80) {
    		  
    	  } else {
    		  int z = 0;
    		  arraysListaConductores.add(new ArrayList<Conductor>());
    		  for(int j = 0; j<9; j++) {
				  arraysListaConductores.get(z).add(listaConductores.get(j));
			  }
    		  asignarTanda(facturaArray,arraysListaConductores.get(z), dia, hora);
			  z++;
			  dia++;
			  hora++;
    	  }
	      
	      
		  
	  
}
  
  public static void asignarTanda(ArrayList<String> facturaArray,ArrayList<Conductor> tanda, int dia, int fecha) {
	  String letra;
	  String facturaTemp;
	  
	      for (int i = 0; i < tanda.size(); i++) {
			 letra = listaCoches.get(i).getLetraCoche();
		
		  for (coche s : listaCoches) {
	            if (letra.equals(s.getLetraCoche())) {
	            	if(tanda.get(i).getDinero()>25 &&
	            			tanda.get(i).getEdad()>15) {
		                s.rentVehicle();
		                tanda.get(i).restarDinero();
		                listaConductorKart.add(new ConductorTieneKart(tanda.get(i).getNombre(),listaCoches.get(i).getLetraCoche()));
		                System.out.println("dia: " + dia + "hora: " + hora);
		                
	            	}

	            }
	        } 
		  
		  
		  
	      }
	      
	      for(int i = 0; 8>i; i++) {
	    	  facturaTemp = "Coche: " + listaConductorKart.get(i).getKart() + " " + listaConductorKart.get(i).getConductor() + " " + tanda.get(i).getEdad() + " años" + "DNI: " + tanda.get(i).getDni() + "Dia : " + dia + " " + "Hora: " + hora + "\n";
	          facturaArray.add(facturaTemp);
	      }
	      
	     
	      
	     
	      
	      
  }
  
  public static void guardarFactura(String facturaKarting) {
		String facturaFinal = facturaKarting;
		BufferedWriter bw = null;
	    try {
		 
	       //Specify the file name and path here
		 File factura = new File("factura.txt");

		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!factura.exists()) {
		     factura.createNewFile();
		  }

		  FileWriter fw = new FileWriter(factura);
		  bw = new BufferedWriter(fw);
		  bw.write(facturaFinal);
	        System.out.println("Archivo Guardado Correctamente");

	    } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(bw!=null)
			 bw.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
		
		
	}
  
  public static String arrayListStringToString(ArrayList<String> list) {
		String listString = "";

		for (String s : list)
		{
		    listString += s;
		}

		return listString;
	}
  
 
	public static void read(int numeroLineas) throws IOException {
		
		String fileName = "conductores2.csv";
   		try {
   		// declaring the buffer size
   		byte[] buffer = new byte[1000];
   		
   		//File Input Stream initialization
   		FileInputStream inputStream = new FileInputStream(fileName);

   		int bufferCounter = 0;

   		// Reading the in chun of buffer size and breaks the while loop until finish the stream
   		while((bufferCounter = inputStream.read(buffer)) != -1) {

   		//Convert the buffer into String
   		String bufferSplit = (new String(buffer));
   	    String[] cols = bufferSplit.split(",");
   	     
   	    int j = 0;
   	 BufferedReader br = new BufferedReader (new FileReader (fileName));
   	String line = br.readLine();
    
   	
   	for(int i=0; i<numeroLineas;i++) {                
   		listaConductores.add(new Conductor(cols[j].trim(),Integer.parseInt(cols[j+1].trim()),cols[j+2].trim(),Double.parseDouble(cols[j+3].trim()),cols[j+4].trim()));
	    	 j = j + 5;
	    	 
	     }
   	
   	}{
   	    	
   	    	
   	     

   		// Always close files.
   		inputStream.close();

   		}
   		}
   		catch(FileNotFoundException ex) {
   		System.out.println(
   		"File can not be open now" +
   		fileName + "'");
   		}
   		catch(IOException ex) {
   		System.out.println(
   		"Unable to bread now "
   		+ fileName + "'");
   		}
        
		
		
		
	}
	
	public static int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	    byte[] c = new byte[1024];
	    int count = 0;
	    int readChars = 0;
	    boolean empty = true;
	    while ((readChars = is.read(c)) != -1) {
	        empty = false;
	        for (int i = 0; i < readChars; ++i) {
	            if (c[i] == '\n') {
	                ++count;
	            }
	        }
	    }
	    return (count == 0 && !empty) ? 1 : count;
	    } finally {
	    is.close();
	   }
	}
  
  
}
