package org.iesalandalus.programacion.alquilervehiculos;


import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;


public class MainApp {

	public static void main(String[] args) {
		
		ModeloCascada modelo = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		VistaTexto vista = new VistaTexto();
		
		Controlador controlador = new Controlador(modelo, vista);
		
		try {
			controlador.comenzar();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

}
