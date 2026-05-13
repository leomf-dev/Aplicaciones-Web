package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rol {
	// Con el tag @JsonProperty apunta al nombre del objeto original.
	@JsonProperty("roleID")
	int rolID;
	@JsonProperty("roleName")
	String nombre;
	@JsonProperty("roleDescription")
	String descripcion;
	
	public Rol() {}

	public int getRolID() {
		return rolID;
	}

	public void setRolID(int rolID) {
		this.rolID = rolID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
