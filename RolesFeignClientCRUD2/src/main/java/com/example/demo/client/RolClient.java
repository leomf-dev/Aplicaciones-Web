package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.models.Rol;

@FeignClient(name = "rolesClient", url = "http://mywebxd.somee.com/api/empresa/roles")
public interface RolClient {
	
	// Metodos CRUD
	@GetMapping("/listar")
	List<Rol> listar();
	@PostMapping("/insertar")
	void insertar(@RequestBody Rol rol);
	@PutMapping("/actualizar")
	void actualizar(@RequestBody Rol rol);
	@DeleteMapping("/eliminar/{id}")
	void eliminar(@PathVariable int id);
	
}
