package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Autobus extends Vehiculo {

	private static final int FACTOR_PLAZAS = 2;
	private int plazas;

	public Autobus(String marca, String modelo, int plazas, String matricula) {
		super(marca, modelo, matricula);
         setPlazas(plazas);
	}
	
	public Autobus(Autobus autobus) {		
		super(autobus);
		setPlazas(autobus.getPlazas());
	}
	
	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if(plazas < 0) {
			throw new IllegalArgumentException("ERROR: Las palzas no pueden ser negativas.");
		}		
		this.plazas = plazas;
	}
	

	@Override
	protected int getFactorPrecio() {
		
		int factorPrecio = plazas * FACTOR_PLAZAS;
		
		return  factorPrecio;
	}
	
	
	
	@Override
	public String toString() {
		return (String.format("%s  (%s plazas) (%sCV) - %s", super.getMarca(), getPlazas(), super.getModelo(),
				super.getMatricula(), "disponible"));
	}
		
}
