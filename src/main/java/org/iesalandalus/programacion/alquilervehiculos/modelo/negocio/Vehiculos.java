package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Vehiculos implements IVehiculos {


	private List<Vehiculo> coleccionVehiculos;

	public Vehiculos() {

		coleccionVehiculos = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos#get()
	 */
	
	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> turismosCopia = new ArrayList<>(coleccionVehiculos);
		return turismosCopia;
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos#getCantidad()
	 */
	
	@Override
	public int getCantidad() {

		return coleccionVehiculos.size();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos#insertar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo)
	 */
	
	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {

			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		}

		if (!coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.add(vehiculo);
		} else {

			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");

		}
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos#buscar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo)
	 */
	
	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		if (vehiculo == null) {
	        throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
	    }
	    if (coleccionVehiculos.contains(vehiculo)) {
	        return coleccionVehiculos.get(coleccionVehiculos.indexOf(vehiculo));
	    } else {
	        return null;
	    }

	
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos#borrar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo)
	 */
	
	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {

			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		}

		if (!coleccionVehiculos.contains(vehiculo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
		} else {
			coleccionVehiculos.remove(vehiculo);
		}

	}
}
