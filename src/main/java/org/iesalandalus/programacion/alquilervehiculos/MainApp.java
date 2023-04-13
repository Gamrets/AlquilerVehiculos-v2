package org.iesalandalus.programacion.alquilervehiculos;


import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;


public class MainApp {

	public static void main(String[] args) {
		
		Modelo modeloCascada = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		Vista  vistaTexto = new VistaTexto();
		
		Controlador controlador = new Controlador(modeloCascada, vistaTexto);
		
		try {
			controlador.comenzar();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

}
