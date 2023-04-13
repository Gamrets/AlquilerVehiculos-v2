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
	protected int getFactorPrecio() {
		
	    float factorPrecio = ((float)getCilindrada() / FACTOR_CILINDRADA); 

		return (int) factorPrecio;
	}

	@Override
	public String toString() {
		return (String.format("%s %s (%sCV) - %s", super.getMarca(), getCilindrada(), super.getModelo(),
				super.getMatricula(), "disponible"));
	}

}
