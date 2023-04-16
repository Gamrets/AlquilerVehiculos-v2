package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {
	
	
	private final String RUTA_FICHERO ="datos/clientes.xml";
	private final String RAIZ = "Clientes";
	private final String CLIENTE = "Cliente";
	private final String NOMBRE ="Nombre";
	private final String DNI = "Dni";
	private final String TELEFONO = "Telefono";
	private final String TIPO_DATO = "TipoDato";
	
	

	

	private List<Cliente> coleccionClientes;
	
	//instancia
	private static Clientes instancia = new Clientes();

	private Clientes() {

	}
	
	//obtener instancia
		public static IClientes getInstancia() {

			return instancia;
		}
		
		
		
		public void comenzar() {
			try {
				coleccionClientes = new ArrayList<>();
				leerXml();
			} catch (Exception e) {
				System.out.println("Error" + e);
			}
		}

		private void leerXml() {

			File archivo = new File(RUTA_FICHERO);
			if (!archivo.exists()) {
			    System.out.println("ERROR: No se encontró el archivo " + RUTA_FICHERO);
			    return;
			}
			if (!archivo.isFile()) {
			    System.out.println("ERROR: " + RUTA_FICHERO + " no es un archivo.");
			    return;
			}
			if (!archivo.canRead()) {
			    System.out.println("ERROR: No se puede leer el archivo " + RUTA_FICHERO);
			    return;
			}
			
			
			Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
			Element listaClientes = DOM.getDocumentElement();

			NodeList listaNodos = listaClientes.getChildNodes();
			for (int i = 0; i < listaNodos.getLength(); i++) {
				Node nodo = listaNodos.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Cliente cliente = elementToCliente((Element) nodo);
					try {
						insertar(cliente);
					} catch (OperationNotSupportedException e) {
						
						System.out.println("Surgio siguiente error:" + e);
					}
				}
			}
		}

		private Cliente elementToCliente(Element elemento) {

			Element clienteDOM = elemento;
			String dniAtributo = clienteDOM.getAttribute(DNI);
			Element nombre = (Element) clienteDOM.getElementsByTagName(NOMBRE).item(0);
			Element telefono = (Element) clienteDOM.getElementsByTagName(TELEFONO).item(0);
			Cliente cliente = new Cliente(nombre.getTextContent(), dniAtributo, telefono.getTextContent());
			return cliente;
		}
		
		
		public void terminar() {
			try {
				escribirXml();
			} catch (Exception e) {
				System.out.println("Error" + e);
			}
		}

		private void escribirXml() {

			Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
			Element listaClientes = DOM.getDocumentElement();

			for (Cliente cliente : coleccionClientes) {

				try {
					Element clienteDOM = clienteToElement(DOM, cliente);
					listaClientes.appendChild(clienteDOM);
				} catch (DOMException e) {

					System.out.println("Error" + e);
				}
			
			}
			
			UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
		}

		private Element clienteToElement(Document DOM, Cliente cliente) {

			Element clienteDOM = DOM.createElement(CLIENTE);
			clienteDOM.setAttribute(DNI, cliente.getDni());

			Element nombreE = DOM.createElement(NOMBRE);
			nombreE.setTextContent(cliente.getNombre());
			nombreE.setAttribute(TIPO_DATO, "String");
			clienteDOM.appendChild(nombreE);

			Element telefonoE = DOM.createElement(TELEFONO);
			telefonoE.setTextContent(cliente.getTelefono());
			telefonoE.setAttribute(TIPO_DATO, "String");
			clienteDOM.appendChild(telefonoE);

			return clienteDOM;

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