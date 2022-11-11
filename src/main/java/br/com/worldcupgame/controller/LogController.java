package br.com.worldcupgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.worldcupgame.dto.LogDTO;
import br.com.worldcupgame.model.Log;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.LogRepository;
import br.com.worldcupgame.repository.UsuarioRepository;
import br.com.worldcupgame.services.LogService;

@RestController
@RequestMapping(path = "/api/logs")
public class LogController {

	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Log> findById(@PathVariable("id") Long id){
		return logRepository.findById(id)
				.map(log -> ResponseEntity.ok().body(log))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "")
	public Iterable<Log> findAll(){
		return logRepository.findAll();
	}
	
	//@GetMapping(path= "/user/{userId}")
	//public List<Log> findByGroupId(@PathVariable("userId") Long userId){
		//return logRepository.findLogsByUserId(userId);
	
	@PostMapping(path = "")
	public Log newLog(@RequestBody LogDTO logDTO){
		Usuario usuario = usuarioRepository.findById(logDTO.getUsuario()).get();
		Log log = logService.formataLog(logDTO.getAction(), usuario);
		return logRepository.save(log);
	}
}

