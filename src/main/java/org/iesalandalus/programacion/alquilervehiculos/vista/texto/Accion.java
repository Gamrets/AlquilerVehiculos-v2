package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion {

	SALIR("Salir") {
		public void ejecutar() {
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		public void ejecutar() {
			vista.insertarCliente();
		}
	},
	INSERTAR_VEHICULO("Insertar vehiculo") {
		public void ejecutar() {
			vista.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("Insertar alquiler") {
		public void ejecutar() {
			vista.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {

		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar vehiculo") {
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar alquiler") {
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar cliente") {
		public void ejecutar() {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER("Devolver alquiler") {
		public void ejecutar() {
			vista.devolverAlquiler();
		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		public void ejecutar() {
			vista.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar vehiculo") {
		public void ejecutar() {
			vista.borrarTurismo();
		}
	},
	BORRAR_ALQUILER("Borrar alquiler") {
		public void ejecutar() {
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Listar vehiculos") {
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar alquileres") {
		public void ejecutar() {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTES("Listar alquileres de clientes") {
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres de vehiculo") {
		public void ejecutar() {
			vista.listarAlquileresTurismo();
		}
	};

	private String cadenaAmostrar;
	private static VistaTexto vista;

	Accion(String cadenaAmostrar) {
		this.cadenaAmostrar = cadenaAmostrar;
	}

	public static Accion getOpcionSegunOrdinal(int ordinal) {

		if (esOrdinalValido(ordinal)) {

			return values()[ordinal];

		} else {

			throw new IllegalArgumentException("ERROR: Ordinal de la accion no valido.");
		}

	}

	public abstract void ejecutar();

	protected static void setVista(VistaTexto vista) {
		Accion.vista = vista;
	}

	public static boolean esOrdinalValido(int ordinal) {

		return (ordinal >= 0 && ordinal <= values().length - 1);
	}

	public String toString() {

		return String.format("%d.-%s", ordinal(), cadenaAmostrar);
	}
}
