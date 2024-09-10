package br.com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.model.Usuario;
import br.com.spring.repository.UsuarioRepository;


@RestController //IC/CD ou CDI - Injeção de dependencia
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

    @RequestMapping(value = "/teste/{name}", method = RequestMethod.GET)
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
    
    @GetMapping(value = "listatodos")
    @ResponseBody // Retorna os dados pro corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // Retorna a lista em JSON
    }

    @PostMapping(value = "salvar") // mapeia a URL
    @ResponseBody // Descrição da Resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){   // Recebe os dados para salvar
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "delete") // mapeia a URL
    @ResponseBody // Descrição da Resposta
    public ResponseEntity<String> delete(@RequestParam Long iduser){   // Recebe os dados para salvar
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscaruserid") // mapeia a URL
    @ResponseBody // Descrição da Resposta
    public ResponseEntity<Usuario> buscaruserid(@RequestParam("iduser") Long iduser){   // Recebe os dados para colsultar
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "atualizar") // mapeia a URL
    @ResponseBody // Descrição da Resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){   // Recebe os dados para salvar
    	if (usuario.getId() == null) {
    		
    		return new ResponseEntity<String>("Id não informado para atualização.", HttpStatus.OK);
    		
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome") // mapeia a URL
    @ResponseBody // Descrição da Resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){   // Recebe os dados para colsultar
    	
    	List<Usuario> usuario = usuarioRepository.buscaPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
    
    
    
    
    
    
    
}
