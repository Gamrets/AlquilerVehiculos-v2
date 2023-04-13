package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {

	private final static String ER_DNI = "\\d{8}[A-Za-z]";
	private final static String ER_TELEFONO = "(9|6)[0-9]{8}";
	private final static String ER_NOMBRE = "^([A-Z]{1}[a-z]+[ ]?){1,2}$";

	private String nombre;
	private String dni;
	private String telefono;

	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		this.nombre = cliente.getNombre();
		this.dni = cliente.getDni();
		this.telefono = cliente.getTelefono();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {

		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}

	private boolean comprobarLetraDni(String dni) {

		// obtiengo el último carácter de la cadena de entrada, que representa la letra
		// del DNI
		char letraDniRecibido = dni.charAt(dni.length() - 1);

		// elimino todos los caracteres que no son dígitos de cadena de entrada y un
		// String solo de dígitos del DNI.
		String parteNumerica = dni.replaceAll("\\D", "");

		// guardo todas letras posibles en un array unidimencional
		char[] arrayLetras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		// el modulo de la parte numerica de la cadena recibida es indice de la letra
		// gaurdada en array
		// si la letra de la cadena entrante coincide con la letra de array entonces DNI
		// es correcto
		// sino no
		if (arrayLetras[(Integer.parseInt(parteNumerica)) % 23] == letraDniRecibido) {

			return true;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return (String.format("%s - %s (%s)", nombre, dni, telefono));
	}

	public static Cliente getClienteConDni(String dniValido) {
		if (dniValido == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dniValido.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}

		Cliente cliente = new Cliente("SinNombre", dniValido, "633658896");
		return cliente;
	}

}