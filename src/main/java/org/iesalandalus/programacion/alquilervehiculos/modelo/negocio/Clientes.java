package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class Clientes implements IClientes {

	private List<Cliente> coleccionClientes;

	public Clientes() {

		coleccionClientes = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#get()
	 */
	
	@Override
	public List<Cliente> get() {

		List<Cliente> clientesCopia = new ArrayList<>(coleccionClientes);
		return clientesCopia;
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#getCantidad()
	 */
	
	@Override
	public int getCantidad() {

		return coleccionClientes.size();
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#insertar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente)
	 */
	
	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {

			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {

			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");

		}
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#buscar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente)
	 */
	
	@Override
	public Cliente buscar(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

		int indice = coleccionClientes.indexOf(cliente);

		if (indice == -1) {
			return null;
		} else {
			return coleccionClientes.get(coleccionClientes.indexOf(cliente));
		}
	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#borrar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente)
	 */
	
	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {

			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		} else {
			coleccionClientes.remove(cliente);
		}

	}

	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes#modificar(org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente, java.lang.String, java.lang.String)
	 */
	
	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		if (nombre != null && !nombre.trim().isEmpty()) {
			//cliente.setNombre(nombre);
			coleccionClientes.get(coleccionClientes.indexOf(cliente)).setNombre(nombre);
		}
		if (telefono != null && !telefono.trim().isEmpty()) {
			//cliente.setTelefono(telefono);
			coleccionClientes.get(coleccionClientes.indexOf(cliente)).setTelefono(telefono);
		}

		if (!coleccionClientes.contains(cliente)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

	}
}
