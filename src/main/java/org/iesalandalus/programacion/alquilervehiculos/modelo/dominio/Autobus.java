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
	public Vehiculo getVehiculoConMatricula(String matricula){
		if (matricula==null) if (matricula == null) {throw new NullPointerException("ERROR: La matrícula no puede ser nula.");}
		return new Autobus("Mercedes","Bens",6,matricula);
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
	    if (obj instanceof Autobus) {
	        Autobus other = (Autobus) obj;
	        return Objects.equals(super.getMatricula(), other.getMatricula());
	    }
	    return false;
	}*/
	
	
	@Override
	public String toString() {
		return (String.format("%s  (%s plazas) (%sCV) - %s", super.getMarca(), getPlazas(), super.getModelo(),
				super.getMatricula(), "disponible"));
	}
		
}
