package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.client.RolClient;
import com.example.demo.models.Rol;

import feign.FeignException;

@Controller
@RequestMapping("/roles")
public class RolController {
	private final RolClient client;
	public RolController(RolClient client) {
		this.client = client;
	}

	// Vista Listado
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("roles", client.listar());
		return "roles";
	}
	
	// Vista del Formulario (insertar y editar)
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("rol", new Rol());
		return "formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Rol rol, RedirectAttributes redirect) {
		try {
		// Validar el objeto rol por id
			if(rol.getRolID() == 0)
			{
				// Insertar
				client.insertar(rol);
				redirect.addFlashAttribute("mensaje", "Insertado correcto");
			}
			else // Si el Rol existe, se da entender que se actualiza
			{
				// Editar
				client.actualizar(rol);
				redirect.addFlashAttribute("mensaje", "Actualizado correcto");
			}
		}
		catch(FeignException e) {
			redirect.addFlashAttribute("error", "No se pudo guardar intente luego");
		}
		return "redirect:/roles";
	}

	@GetMapping("/editar/{rolID}")
	public String editar(@PathVariable int rolID, Model model) {
		var rol = client.listar().stream()
					.filter(r -> r.getRolID() == rolID)
					.findFirst().orElse(null);
		model.addAttribute("rol", rol);
		return "formulario";
	}

	@GetMapping("/eliminar/{rolID}")
	public String elimina(@PathVariable int rolID, RedirectAttributes redirect) {
		try {
			client.eliminar(rolID);
			redirect.addFlashAttribute("mensaje", "Eliminado correcto");
		}
		catch (Exception e) {
			redirect.addFlashAttribute("error", "No se pudo eliminar, intente luego");
		}
		return "redirect:/roles";
	}
	
}
