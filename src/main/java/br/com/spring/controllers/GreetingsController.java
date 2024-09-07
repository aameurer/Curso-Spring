package br.com.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.model.Usuario;
import br.com.spring.repository.UsuarioRepository;


@RestController //IC/CD ou CDI - Injeção de dependencia
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    
    
    @RequestMapping(value = "/olamundo/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String name) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	
    	usuarioRepository.save(usuario); // grava no banco de dados
    	
    	
        return "Hello " + name + "!";
    }

}
