package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {

	private static final String ER_MARCA = "([A-ZÑ][a-zñ]+([ -]?[A-ZÑ][A-ZÑa-zñ]+)?)|[A-Z]+";

	// Lo importante en matricula española letras validas son
	// B,C,D,G,H,J,K,L,M,N,P,R,S,T,V,W,X,Y,Z
	private static final String ER_MATRICULA = "\\d{4}\\s{0,1}([B-D]|[F-H]|[J-N]|[P-T]|[V-Z]){3}";

	private String marca;
	private String modelo;
	private String matricula;

	protected Vehiculo(String marca, String modelo, String matricula) {

		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}

	protected Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehiculo nulo.");
		}
		setMarca(vehiculo.getMarca());
		setModelo(vehiculo.getModelo());
		setMatricula(vehiculo.getMatricula());

	}

	protected abstract int getFactorPrecio();
	
    
	public Vehiculo copiar(Vehiculo vehiculo) {
		
		
		   //comprobamos que el veiculo recibido es de tipo instancia Turismo
		if (vehiculo instanceof Turismo) {
			
			return new Turismo((Turismo) vehiculo);
			
		} else if (vehiculo instanceof Autobus) {
			
			return new Autobus((Autobus) vehiculo);
			
		} else if (vehiculo instanceof Furgoneta) {
			
			return new Furgoneta((Furgoneta)vehiculo);
		} else {
			throw new IllegalArgumentException("ERROR: Tipo de vehículo desconocido.");
		}
	}
	

	public abstract Vehiculo getVehiculoConMatricula(String matriculaValida);


	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	protected void setMarca(String marca) {

		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}

		if (!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}

		this.marca = marca;
	}

	protected void setModelo(String modelo) {

		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}

		if (modelo.trim().isEmpty()) {

			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}

		this.modelo = modelo;
	}

	protected void setMatricula(String matricula) {

		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}

		if (!matricula.replaceAll(" ", "").matches(ER_MATRICULA)) {

			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}

		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Vehiculo) {
			Vehiculo other = (Vehiculo) obj;
			return Objects.equals(matricula, other.matricula);
		}
		return false;
	}

}
