package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Vehiculos implements IVehiculos {

	private static final String RUTA_FICHERO = "datos/vehiculos.xml";
	private static final String RAIZ = "Vehiculos"; 
	private static final String VEHICULO = "Vehiculo"; 
	private static final String MARCA = "Marca"; 
	private static final String MODELO = "Modelo"; 
	private static final String MATRICULA = "Matricula"; 
	private static final String CILINDRADA ="Cilindrada"; 
	private static final String PLAZAS = "Plazas"; 
	private static final String PMA = "Pma"; 
	private static final String TIPO = "Tipo"; 
	private static final String TURISMO = "Turismo"; 
	private static final String AUTOBUS = "Autobus"; 
	private static final String FURGONETA = "Furgoneta"; 
	private static final String TIPO_DATO = "TipoDato"; 
	
	
	
	
	
	private List<Vehiculo> coleccionVehiculos;
	
	//instancia
	private static Vehiculos instancia = new Vehiculos();

	private Vehiculos() {

		
	}
	
	//obtener instancia
	public static IVehiculos getInstancia() {

		return instancia;
	}
	
	
	
	public void comenzar() {
		try {
			coleccionVehiculos = new ArrayList<>();
			leerXml();
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
	
	
	
	private void leerXml() {

		Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element listaVehiculos = DOM.getDocumentElement();

		NodeList listaNodos = listaVehiculos.getChildNodes();

		for (int i = 0; i < listaNodos.getLength(); i++) {
			Node nodo = listaNodos.item(i);

			
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Vehiculo vehiculo = elementToVehiculo((Element) nodo);
				try {
					insertar(vehiculo);
				} catch (OperationNotSupportedException e) {
				}
			}
		}
	}

	private Vehiculo elementToVehiculo(Element elemento) {

		Vehiculo vehiculo = null;
		Element vehiculoDOM = elemento;
		String matriculaAtributo = vehiculoDOM.getAttribute(MATRICULA);
		String tipoVehiculo = vehiculoDOM.getAttribute(TIPO);

		Element marca = (Element) vehiculoDOM.getElementsByTagName(MARCA).item(0);
		Element modelo = (Element) vehiculoDOM.getElementsByTagName(MODELO).item(0);

		if (tipoVehiculo.equalsIgnoreCase(TURISMO)) {

			Element turismoDOM = (Element) vehiculoDOM.getElementsByTagName(TURISMO).item(0);
			Element cilindrada = (Element) turismoDOM.getElementsByTagName(CILINDRADA).item(0);
			vehiculo = new Turismo(marca.getTextContent(), modelo.getTextContent(),
					Integer.parseInt(cilindrada.getTextContent()), matriculaAtributo);
		}

		if (tipoVehiculo.equalsIgnoreCase(FURGONETA)) {
			Element furgonetaDOM = (Element) vehiculoDOM.getElementsByTagName(FURGONETA).item(0);

			Element pma = (Element) furgonetaDOM.getElementsByTagName(PMA).item(0);
			Element plazas = (Element) furgonetaDOM.getElementsByTagName(PLAZAS).item(0);
			vehiculo = new Furgoneta(marca.getTextContent(), modelo.getTextContent(),
					Integer.parseInt(pma.getTextContent()), Integer.parseInt(plazas.getTextContent()), matriculaAtributo);
		}

		if (tipoVehiculo.equalsIgnoreCase(AUTOBUS)) {
			Element autobusDOM = (Element) vehiculoDOM.getElementsByTagName(AUTOBUS).item(0);
			Element plazas = (Element) autobusDOM.getElementsByTagName(PLAZAS).item(0);
			vehiculo = new Autobus(marca.getTextContent(), modelo.getTextContent(),
					Integer.parseInt(plazas.getTextContent()), matriculaAtributo);
		}

		return vehiculo;
	}
	
	@Override
	public void terminar() {
		try {
			escribirXml();
		} catch (Exception e) {
		}
		
	}
	
	private void escribirXml() {

		Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
		Element listaVehiculos = DOM.getDocumentElement();

		for (Vehiculo v : coleccionVehiculos) {

			try {
				Element vehiculoDOM = vehiculoToElement(DOM, v);
				listaVehiculos.appendChild(vehiculoDOM);
			} catch (DOMException e) {// TODO Auto-generated catch blocke.printStackTrace();}
			}

			UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
		}
	}
	
	
	private Element vehiculoToElement(Document DOM,Vehiculo vehiculo) {
		
		String tipoVehiculo = null;
		if(vehiculo instanceof Turismo) {tipoVehiculo=TURISMO;}
		if(vehiculo instanceof Autobus) {tipoVehiculo=AUTOBUS;}
		if(vehiculo instanceof Furgoneta) {tipoVehiculo=FURGONETA;}
		
		
		Element vehiculoDOM = DOM.createElement(VEHICULO);
		vehiculoDOM.setAttribute(MATRICULA, vehiculo.getMatricula());
		vehiculoDOM.setAttribute(TIPO, tipoVehiculo);
		
		
		Element marcaElemento = DOM.createElement(MARCA);
		marcaElemento.setTextContent(vehiculo.getMarca());
		marcaElemento.setAttribute(TIPO_DATO, "String");
		vehiculoDOM.appendChild(marcaElemento);
		
		Element modeloElemento = DOM.createElement(MODELO);
		modeloElemento.setTextContent(vehiculo.getModelo());
		modeloElemento.setAttribute(TIPO_DATO, "String");
		vehiculoDOM.appendChild(modeloElemento);
		
		if(vehiculo instanceof Turismo) {
			Element turismoElemento = DOM.createElement(TURISMO);
			vehiculoDOM.appendChild(turismoElemento);
			
			Element cilindradaE = DOM.createElement(CILINDRADA);
			cilindradaE.setTextContent( Integer. toString(((Turismo) vehiculo).getCilindrada()));
			cilindradaE.setAttribute(TIPO_DATO, "Integer");
			turismoElemento.appendChild(cilindradaE);
					
		}
		
		if(vehiculo instanceof Autobus) {
			Element autobusE = DOM.createElement(AUTOBUS);
			vehiculoDOM.appendChild(autobusE);
			
			Element plazasE = DOM.createElement(PLAZAS);
			plazasE.setTextContent( Integer. toString(((Autobus) vehiculo).getPlazas()));
			plazasE.setAttribute(TIPO_DATO, "Integer");
			autobusE.appendChild(plazasE);
		
		}
		
		return vehiculoDOM;
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
