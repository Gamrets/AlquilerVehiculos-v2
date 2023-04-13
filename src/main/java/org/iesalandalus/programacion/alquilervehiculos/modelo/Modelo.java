package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.FuenteDatosMemoria;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Vehiculos;





public abstract class Modelo {

	 IAlquileres alquileres;
	 IClientes clientes;
	 IVehiculos vehiculos;
	 IFuenteDatos fuenteDatos;

	 public void setFuenteDatos(IFuenteDatos fuenteDatos) {
		 
		 fuenteDatos = new FuenteDatosMemoria();
	 }

	public void comenzar() {
		alquileres = new Alquileres();
		clientes = new Clientes();
		vehiculos = new Vehiculos();
	}

	public void terminar() {
		System.out.println("El modelo finalizado");
	}

	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException ;
	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;
	public abstract void insertar(Vehiculo turismo) throws OperationNotSupportedException;
	public abstract Cliente buscar(Cliente cliente);
	public abstract Alquiler buscar(Alquiler alquiler);
	public abstract Vehiculo buscar(Vehiculo vehiculo);
	public abstract void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;
	public abstract void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException;
	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;
	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;
	public abstract void borrar(Vehiculo turismo) throws OperationNotSupportedException;
	public abstract List<Cliente> getClientes();
	public abstract List<Alquiler> getAlquileres();
	public abstract List<Vehiculo> getTurismos();
	public abstract List<Alquiler> getAlquileres(Cliente cliente);
	public abstract List<Alquiler> getAlquileres(Vehiculo turismo);
}
