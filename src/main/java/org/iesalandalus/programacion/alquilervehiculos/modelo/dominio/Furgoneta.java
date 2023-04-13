package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Furgoneta extends Vehiculo {
	private static final int FACTOR_PMA = 100;
	private static final int FACTOR_PLAZAS = 1;

	private int pma;
	private int plazas;

	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPlazas(plazas);
		setPma(pma);
	}

	public Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		setPlazas(furgoneta.getPlazas());
		setPma(furgoneta.getPma());
	}

	public int getPma() {
		return pma;
	}

	private void setPma(int pma) {
		if(pma < 0) {
			throw new IllegalArgumentException("ERROR: El pma no puede ser negativo.");
		}	
		this.pma = pma;
	}

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if(plazas < 0) {
			throw new IllegalArgumentException("ERROR: Las plazas no pueden ser negativas.");
		}
		this.plazas = plazas;
	}
	
	@Override
	public Vehiculo getVehiculoConMatricula(String matricula){
		if (matricula==null) if (matricula == null) {throw new NullPointerException("ERROR: La matrícula no puede ser nula.");}
		return new Furgoneta("Mercedes","Benz",2,25,matricula);
		}

	@Override
	protected int getFactorPrecio() {

		float faclorPrecio =  ((float)getPma() / FACTOR_PMA + (float)getPlazas() * FACTOR_PLAZAS);

		return (int) faclorPrecio;
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
		return (String.format("%s (%s Pma) (%s plazas) (%sCV) - %s", super.getMarca(), getPma(),getPlazas(), super.getModelo(),
				super.getMatricula(), "disponible"));
	}

}
