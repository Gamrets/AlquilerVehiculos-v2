package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Alquileres implements IAlquileres {
	
	
	private final String RUTA_FICHERO ="datos/alquileres.xml";
	private final String FORMATO_FECHA = "dd/MM/yyyy";
	private final String RAIZ = "Alquileres";
	private final String ALQUILER = "Alquiler";
	private final String DNI_CLIENTE = "Dni";
	private final String MATRICULA_VEHICULO = "Matricula";
	private final String FECHA_ALQUILER = "FechaAlquiler";
	private final String FECHA_DEVOLUCION = "FechaDevolucion";
	private final String FORMATO = "Formato";
	private final String TIPO_DATO = "TipoDato";
	
	
	
	
	

	private List<Alquiler> coleccionAlquileres;
	
	//instancia
	private static Alquileres instancia = new Alquileres();

	//constructor privado
	private Alquileres() {
		
	}
	
	//obtener instancia
	public static IAlquileres getInstancia() {

		return instancia;
	}
	
	
	
	public void comenzar() {
		try {
			
			coleccionAlquileres  = new ArrayList<>();
			leerXml();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	private void leerXml() {

		Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element listaAlquileres = DOM.getDocumentElement();

		NodeList listaNodos = listaAlquileres.getChildNodes();

		for (int i = 0; i < listaNodos.getLength(); i++) {
			Node nodo = listaNodos.item(i);

			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Alquiler alquiler = elementToAlquiler((Element) nodo);
				try {
					insertar(alquiler);
				} catch (OperationNotSupportedException e) {
				}
			}
		}
	}

	private Alquiler elementToAlquiler(Element element) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

		Cliente cliente = null;
		Vehiculo vehiculo = null;

		Element alquilerDOM = element;
		String matriculaAtributo = alquilerDOM.getAttribute(MATRICULA_VEHICULO);
		String dniAtributo = alquilerDOM.getAttribute(DNI_CLIENTE);

		Element fechaAlquiler = (Element) alquilerDOM.getElementsByTagName(FECHA_ALQUILER).item(0);
		Element fechaDevolucion = (Element) alquilerDOM.getElementsByTagName(FECHA_DEVOLUCION).item(0);

		List<Cliente> listaClientes = Clientes.getInstancia().get();
		for (Cliente clienteS : listaClientes) {
			if (clienteS.getDni().equalsIgnoreCase(dniAtributo)) {
				cliente = clienteS;
			}
		}

		List<Vehiculo> listaVehiculos = Vehiculos.getInstancia().get();

		for (Vehiculo vehiculoS : listaVehiculos) {
			if (vehiculoS.getMatricula().equalsIgnoreCase(matriculaAtributo)) {
				vehiculo = vehiculoS;
			}
		}
		Alquiler alquiler = new Alquiler(cliente, vehiculo, LocalDate.parse(fechaAlquiler.getTextContent(), formatter));

		if (fechaDevolucion.getTextContent() != null && fechaDevolucion.getTextContent() != "") {
			try {
				alquiler.devolver(LocalDate.parse(fechaDevolucion.getTextContent(), formatter));
			} catch (OperationNotSupportedException | DOMException e) {
				e.printStackTrace();
			}
		}
		return alquiler;
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

	public void devolver(Cliente cliente, LocalDate fechaDevolucion)
			throws NullPointerException, OperationNotSupportedException {
		if (cliente == null || fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		Alquiler alquiler = null;
		alquiler = getAlquilerAbierto(cliente);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		alquiler.devolver(fechaDevolucion);
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion)
			throws NullPointerException, OperationNotSupportedException {
		if (vehiculo == null || fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		Alquiler alquiler = null;
		alquiler = getAlquilerAbierto(vehiculo);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		alquiler.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler de cliente nulo.");
		}
		Alquiler alquiler = null;
		for (Alquiler alquilerBusca : coleccionAlquileres) {
			if (alquilerBusca.getFechaDevolucion() == null && alquilerBusca.getCliente().equals(cliente)) {
				alquiler = alquilerBusca;
			}

		}
		return alquiler;
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler de cliente nulo.");
		}
		Alquiler alquiler = null;
		for (Alquiler alquilerBusca : coleccionAlquileres) {
			if (alquilerBusca.getFechaDevolucion() == null && alquilerBusca.getVehiculo().equals(vehiculo)) {
				alquiler = alquilerBusca;
			}

		}
		return alquiler;
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

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}

}