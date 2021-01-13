package practica2Evaluacion2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import practica2Evaluacion2.DBUtilities;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KartGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearchDNI;
	private JTextField textFieldSearchFecha;
	static //dias que pasan, para imprimir poner mayor de 1
	int dia = 0;
	static int hora = 4;
	int numeroTanda=0;
	File ff;
	FileReader fff= null;
	String factura;
	ArrayList<String> facturaArray = new ArrayList<String>();
  
  static ArrayList<ArrayList<Conductor>> arraysListaConductores = new ArrayList<ArrayList<Conductor>>();
  static ArrayList<coche> listaCoches = new ArrayList<coche>();
  static ArrayList<tanda> listaTandas = new ArrayList<tanda>();
  static ArrayList<Conductor> listaConductores = new ArrayList<Conductor>();
  static ArrayList<Conductor> listasConductores = new ArrayList<Conductor>();
  static ArrayList<ConductorTieneKart> listaConductorKart = new ArrayList<ConductorTieneKart>();
  static List<List<Conductor>> tandaConductores = new ArrayList<List<Conductor>>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KartGUI frame = new KartGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static Connection connection=null;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public KartGUI() {
		connection=DBUtilities.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldSearchDNI = new JTextField();
		textFieldSearchDNI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query="select Tanda, Hora, Coche, Nombre, Edad, DNI from facturakartdia where DNI=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldSearchDNI.getText());
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		textFieldSearchDNI.setBounds(578, 84, 120, 20);
		contentPane.add(textFieldSearchDNI);
		textFieldSearchDNI.setColumns(10);
		
		textFieldSearchFecha = new JTextField();
		textFieldSearchFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String query="select Tanda, Hora, Coche, Nombre, Edad, DNI from facturakartdia where Hora=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldSearchFecha.getText());
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		textFieldSearchFecha.setBounds(578, 115, 86, 20);
		contentPane.add(textFieldSearchFecha);
		textFieldSearchFecha.setColumns(10);
		
		JLabel lblConsultarPorDni = new JLabel("Consultar por dni:");
		lblConsultarPorDni.setBounds(433, 87, 115, 14);
		contentPane.add(lblConsultarPorDni);
		
		JLabel lblConsultarPorFecha = new JLabel("Consultar por fecha:");
		lblConsultarPorFecha.setBounds(433, 118, 135, 14);
		contentPane.add(lblConsultarPorFecha);
		
		JButton btnCargarTandas = new JButton("Cargar Tandas");
		btnCargarTandas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select Tanda, Hora, Coche, Nombre, Edad, DNI from facturakartdia order by hora desc limit 10";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
			
		});
		btnCargarTandas.setBounds(126, 236, 134, 23);
		contentPane.add(btnCargarTandas);
		
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileConductoresPath;
				
				JFileChooser jf = new JFileChooser();
				int aa=jf.showOpenDialog(null);
				if(aa==JFileChooser.APPROVE_OPTION) {
					try {
						ff=jf.getSelectedFile();
						fileConductoresPath = ff.getAbsolutePath();
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
				    			  
				    			  System.out.println("HELLO");
				    		  }
				    		  
				    		  
				    		  
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
					      
					     
						
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		btnImportar.setBounds(504, 33, 89, 23);
		contentPane.add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factura = arrayListStringToString(facturaArray);
	    	    guardarFactura(factura);
	    	    JOptionPane.showMessageDialog(null, "Exportado");
			}
		});
		btnExportar.setBounds(504, 180, 89, 23);
		contentPane.add(btnExportar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 21, 333, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
		    	  try {
		    		  String query="insert into facturakartdia (Tanda, Hora, Coche, Nombre, Edad, DNI) values (null,?,?,?,?,?)";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.setInt(1, hora  );
						pst.setString(2, listaConductorKart.get(i).getKart());
						pst.setString(3, listaConductorKart.get(i).getConductor());
						pst.setInt(4, tanda.get(i).getEdad());
						pst.setString(5,tanda.get(i).getDni());
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Guardado en facturasKarting");
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
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
