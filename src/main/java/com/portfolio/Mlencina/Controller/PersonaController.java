package com.portfolio.Mlencina.Controller;

import com.portfolio.Mlencina.Entity.Persona;
import com.portfolio.Mlencina.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.GetPersona();
    }
    
    @PreAuthorize("ashRole('ADMIN')") 
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("ashRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}") 
    public String DeletePersona(@PathVariable Long id){
    ipersonaService.deletePersona(id);
    return "La persona fue eliminada correctamente";
    } 
    
    @PreAuthorize("ashRole('ADMIN')")
    @PutMapping("/persona/editar/{id}") 
       public Persona ediPersona(@PathVariable Long id,
                                 @RequestParam("nombre") String nuevoNombre,
                                 @RequestParam("apellido") String nuevoApellido,
                                 @RequestParam("titulo") String nuevoTitulo){
           
       Persona persona = ipersonaService.findPersona(id);
       persona.setNombre(nuevoNombre);
       persona.setApellido(nuevoApellido);
       persona.setTitulo(nuevoTitulo);
       
       ipersonaService.savePersona(persona);
       return persona;
       }  
       
        @GetMapping("/personas/trer/perfil")
        public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
        }         
                
       
}