package br.com.worldcupgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worldcupgame.model.Log;
import br.com.worldcupgame.model.Usuario;
import br.com.worldcupgame.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;

	public Log formataLog(String action, Usuario usuario) {
		Log log = new Log(action, usuario);
		return logRepository.save(log);
	}
	
}
