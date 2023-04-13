package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo extends Vehiculo {

	private static final int FACTOR_CILINDRADA = 10;
	private int cilindrada;

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);

		setCilindrada(cilindrada);
	}

	public Turismo(Turismo turismo) {
		super(turismo);
		setCilindrada(turismo.getCilindrada());
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {

		if ((cilindrada <= 0) || (cilindrada > 5000)) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}

		this.cilindrada = cilindrada;
	}
	
	@Override
	public Vehiculo getVehiculoConMatricula(String matricula){
		if (matricula==null) if (matricula == null) {throw new NullPointerException("ERROR: La matrícula no puede ser nula.");}
		return new Turismo("Mercedes","Bens",50,matricula);
	}
	

	@Override
	protected int getFactorPrecio() {
		
	    float factorPrecio = ((float)getCilindrada() / FACTOR_CILINDRADA); 

		return (int) factorPrecio;
	}

	/*@Override
	public int hashCode() {
		return Objects.hash(super.getMatricula());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Turismo) {
			Turismo other = (Turismo) obj;
			return Objects.equals(super.getMatricula(), other.getMatricula());
		}
		return false;
	}*/

	@Override
	public String toString() {
		return (String.format("%s %s (%sCV) - %s", super.getMarca(), getCilindrada(), super.getModelo(),
				super.getMatricula(), "disponible"));
	}

}
