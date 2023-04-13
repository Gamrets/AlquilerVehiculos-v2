package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

public class FuenteDatosMemoria implements IFuenteDatos {
	
	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos#crearClientes()
	 */
	
	@Override
	public IClientes crearClientes() {
		return new Clientes();		
	}
	
	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos#crearVehiculos()
	 */
	
	@Override
	public IVehiculos crearVehiculos() {
		
		return new Vehiculos();
		
	}
	/* (non-Javadoc)
	 * @see org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos#crearAlquileres()
	 */
	
	@Override
	public IAlquileres crearAlquileres() {
		
		return new Alquileres();
		
	}

}
