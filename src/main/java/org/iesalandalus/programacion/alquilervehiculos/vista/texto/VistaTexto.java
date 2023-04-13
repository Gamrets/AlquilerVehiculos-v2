package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto  extends Vista{
	
	public  VistaTexto() {
		
		Accion.setVista(this);
	}
	

	public void comenzar() {

		Accion accion = null;

		do {

			Consola.mostrarMenu();
			
			try {
				accion = Consola.elegirOpcion();
				accion.ejecutar();
				
			} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
				System.out.println(e.getMessage());
			}

		} while (accion != Accion.SALIR);

	}
	

	public void terminar() {
		System.out.println("Hasta luego,nos vemos pronto.");
	}

	protected void insertarCliente() {

		Consola.mostrarCabecera("Insertar cliente");
		Cliente cliente = Consola.leerCliente();

		try {
			controlador.insertar(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void insertarVehiculo() {
		Consola.mostrarCabecera("Insertar vehiculo");
		Vehiculo vehiculo = Consola.leerVehiculo();

		try {
			controlador.insertar(vehiculo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		Alquiler alquiler = Consola.leerAlquiler();

		try {
			controlador.insertar(alquiler);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		Cliente cliente = Consola.leerClienteDni();
		try {
			cliente = controlador.buscar(cliente);
			System.out.println(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void buscarVehiculo() {
		Consola.mostrarCabecera("Buscar vehiculo");
		Vehiculo vehiculo = Consola.leerVehiculoMatricula();
		try {
			vehiculo = controlador.buscar(vehiculo);
			System.out.println(vehiculo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void buscarAlquiler() {
		Consola.mostrarCabecera("Buscar alquiler");
		Alquiler alquiler = Consola.leerAlquiler();
		try {
			alquiler = controlador.buscar(alquiler);
			System.out.println(alquiler);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void modificarCliente() {
		Consola.mostrarCabecera("Modificar cliente");
		Cliente cliente = Consola.leerClienteDni();
		String nombre = Consola.leerNombre();
		String telefono = Consola.leerTelefono();
		try {
			controlador.modificar(cliente, nombre, telefono);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void devolverAlquiler() {
		Consola.mostrarCabecera("Devolver alquiler");
		Alquiler alquiler = Consola.leerAlquiler();
		LocalDate fechaDevolucion = Consola.leerFechaDevolucion();
		try {
			controlador.devolver(alquiler, fechaDevolucion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		Cliente cliente = Consola.leerClienteDni();
		try {
			controlador.borrar(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void borrarTurismo() {
		Consola.mostrarCabecera("Borrar turismo");
		Vehiculo turismo = Consola.leerVehiculoMatricula();
		try {
			controlador.borrar(turismo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		Alquiler alquiler = Consola.leerAlquiler();
		try {
			controlador.borrar(alquiler);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarClientes() {
		Consola.mostrarCabecera("Listar clientes");
		try {
			// Obrengo lista de clientes del controlador
			List<Cliente> clientes = controlador.getClientes();

			// Utilisando colection sort ordeno la lista / con comparator defino criterio de
			// comparacion
			Collections.sort(clientes, new Comparator<Cliente>() {

				public int compare(Cliente c1, Cliente c2) {
					// Primero se compara por el nobre
					int resultado = c1.getNombre().compareTo(c2.getNombre());
					// Despues por dni
					if (resultado == 0) {
						resultado = c1.getDni().compareTo(c2.getDni());
					}
					return resultado;
				}
			});
			for (Cliente cliente : clientes) {
				System.out.println(cliente);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void listarVehiculos() {
		Consola.mostrarCabecera("Listar todos los vehiculos");
		try {
	        List<Vehiculo> vehiculos = controlador.getTurismos();
	        Collections.sort(vehiculos, new Comparator<Vehiculo>() {
	        
	            public int compare(Vehiculo t1, Vehiculo t2) {
	                int resultado = t1.getMarca().compareTo(t2.getMarca());
	                if (resultado == 0) {
	                    resultado = t1.getModelo().compareTo(t2.getModelo());
	                    if (resultado == 0) {
	                        resultado = t1.getMatricula().compareTo(t2.getMatricula());
	                    }
	                }
	                return resultado;
	            }
	        });
	        for (Vehiculo vehiculo : vehiculos) {
	            System.out.println(vehiculo);
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}
	

	protected void listarAlquileres() {
		Consola.mostrarCabecera("Listar alquileres");
		try {
			List<Alquiler> alquileres = controlador.getAlquileres();
			Collections.sort(alquileres, new Comparator<Alquiler>() {
				public int compare(Alquiler a1, Alquiler a2) {
					int resultado = a1.getFechaAlquiler().compareTo(a2.getFechaAlquiler());
					if (resultado == 0) {
						resultado = a1.getCliente().getNombre().compareTo(a2.getCliente().getNombre());
						if (resultado == 0) {
							resultado = a1.getCliente().getDni().compareTo(a2.getCliente().getDni());
						}
					}
					return resultado;
				}
			});
			for (Alquiler alquiler : alquileres) {
				System.out.println(alquiler);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void listarAlquileresCliente() {
		Consola.mostrarCabecera("Listar alquileres de un cliente");
		Cliente cliente = Consola.leerClienteDni();
		try {
			List<Alquiler> alquileres = controlador.getAlquileres(cliente);
			Collections.sort(alquileres, new Comparator<Alquiler>() {
				public int compare(Alquiler a1, Alquiler a2) {
					int resultado = a1.getFechaAlquiler().compareTo(a2.getFechaAlquiler());
					if (resultado == 0) {
						resultado = a1.getCliente().getNombre().compareTo(a2.getCliente().getNombre());
						if (resultado == 0) {
							resultado = a1.getCliente().getDni().compareTo(a2.getCliente().getDni());
						}
					}
					return resultado;
				}
			});
			
			for (Alquiler alquiler : alquileres) {
				System.out.println(alquiler);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void listarAlquileresTurismo() {
		Consola.mostrarCabecera("Listar alquileres de un turismo");
		Vehiculo turismo = Consola.leerVehiculoMatricula();
		try {
			List<Alquiler> alquileres = controlador.getAlquileres(turismo);
			Collections.sort(alquileres, new Comparator<Alquiler>() {
				public int compare(Alquiler a1, Alquiler a2) {
					int resultado = a1.getFechaAlquiler().compareTo(a2.getFechaAlquiler());
					if (resultado == 0) {
						resultado = a1.getCliente().getNombre().compareTo(a2.getCliente().getNombre());
						if (resultado == 0) {
							resultado = a1.getCliente().getDni().compareTo(a2.getCliente().getDni());
						}
					}
					return resultado;
				}
			});

			for (Alquiler alquiler : alquileres) {
				System.out.println(alquiler);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
