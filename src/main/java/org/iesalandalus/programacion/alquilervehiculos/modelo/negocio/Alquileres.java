package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Alquileres implements IAlquileres {

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#get()
	 */
	
	@Override
	public List<Alquiler> get() {

		List<Alquiler> alquileresCopia = new ArrayList<>(coleccionAlquileres);
		return alquileresCopia;
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#get(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente)
	 */
	
	@Override
	public List<Alquiler> get(Cliente cliente) {

		//almaceno los alquileres asociados con cliente recibido
		List<Alquiler> listaAlquileres = new ArrayList<>();
		//creo un iterador para la colección de alquileres.
		Iterator<Alquiler> iterator = coleccionAlquileres.iterator();
		
		//se ejecuta mientras la colecciónAlquileres tenga elementos que no iterados
		while (iterator.hasNext()) {
			//iterador avanza al siguiente elemento y se almacena en variable alquiler
			Alquiler alquiler = iterator.next();
			//se comprueba si cliente recibido es igual al cliente de alquiler 
			if (alquiler.getCliente().equals(cliente)) {
				
				//si es igual este alquiler se añade a la lista de alquileres con este cliente asosiado
				listaAlquileres.add(alquiler);
			}
		}
		
		//devolvemos la lista de alquileres asociada a cliente
		return listaAlquileres;
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#get(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo)
	 */
	
	@Override
	public List<Alquiler> get(Vehiculo turismo) {

		//almaceno los alquileres asociados con turismo recibido
		List<Alquiler> listaAlquileres = new ArrayList<>();
		//creo un iterador para la colección de alquileres.
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
		
		//se ejecuta mientras la colecciónAlquileres tenga elementos que no iterados
		while (iterador.hasNext()) {
			//iterador avanza al siguiente elemento y se almacena en variable alquiler
			Alquiler alquiler = iterador.next();
			
			//se comprueba si turismo recibido es igual al turismo de alquiler 
			if (alquiler.getVehiculo().equals(turismo)) {
				
				//si es igual este alquiler se añade a la lista de alquileres con este turismo asosiado
				listaAlquileres.add(alquiler);
			}
		}
		//devolvemos la lista de alquileres asociada a turismo
		return listaAlquileres;
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#getCantidad()
	 */
	
	@Override
	public int getCantidad() {

		return coleccionAlquileres.size();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#insertar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler)
	 */
	
	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);

	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Iterator<Alquiler> iterator = coleccionAlquileres.iterator(); iterator.hasNext();) {
			Alquiler alquiler = iterator.next();

			if (alquiler.getFechaDevolucion() == null) {
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getVehiculo().equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}
			} else {
				if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getVehiculo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#devolver(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler, java.time.LocalDate)
	 */
	
	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (alquiler == null || fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}

		boolean encontrado = false;
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();

		while (iterador.hasNext()) {
			Alquiler alquilerActual = iterador.next();
			if (alquilerActual.equals(alquiler)) {
				alquilerActual.devolver(fechaDevolucion);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#borrar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler)
	 */
	
	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {

			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (!coleccionAlquileres.contains(alquiler)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		} else {
			coleccionAlquileres.remove(alquiler);
		}

	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres#buscar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler)
	 */
	
	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		int indice = coleccionAlquileres.indexOf(alquiler);

		if (indice == -1) {
			return null;
		} else {
			return coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler));
		}
	}

}
