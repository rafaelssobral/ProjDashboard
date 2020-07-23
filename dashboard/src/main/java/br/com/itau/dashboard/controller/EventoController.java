package br.com.itau.dashboard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.dashboard.dao.EventoDAO;
import br.com.itau.dashboard.model.Evento;
import br.com.itau.dashboard.model.Intervalo;

@CrossOrigin("*")
@RestController
public class EventoController {
	
	@Autowired
	private EventoDAO dao;
	
	
	@GetMapping("/eventos")
	public ResponseEntity<ArrayList<Evento>> listaEvento(){
		ArrayList<Evento> lista = (ArrayList<Evento>) dao.findAll();
		if(lista.size()==0) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}

	
	@PostMapping("eventos/intervalo")
	public ResponseEntity<ArrayList<Evento>> getEventosPorData(@RequestBody Intervalo intervalo){
		ArrayList<Evento> lista = dao.getByData(intervalo.getInicio(), intervalo.getFim());
		if(lista.size()==0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	
	/*
	@PostMapping("alarmes/total")
	public ResponseEntity<ArrayList<Evento>> getTotalAlarme(@RequestBody Intervalo intervalo){
		ArrayList<Evento> lista = dao.findByAlarmeAndData(intervalo.getInicio(), intervalo.getFim());
		if(lista.size()==0) {
			return ResponseEntity.status(404).build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	*/
}
