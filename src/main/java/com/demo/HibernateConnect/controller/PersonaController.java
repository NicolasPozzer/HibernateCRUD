package com.demo.HibernateConnect.controller;

import com.demo.HibernateConnect.model.Persona;
import com.demo.HibernateConnect.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired //Primero de todo Hacemos que el Controller llame -> Service
    private IPersonaService persoServ;

    /*Declaramos cada uno de los EndPoints de mi CRUD*/
    @GetMapping("/personas/traer")/*<- esto que va del url se llama path*/
    public List<Persona> getPersonas(){
        return persoServ.getPersonas();
    }

    @PostMapping("/personas/crear")
    public String savePersona(@RequestBody Persona perso){/*Usamos @RequestBody ya que la */
          persoServ.savePersona(perso);                  /*persona a crear llega mediante JSON*/
        return "La persona fue creada correctamente";
    }

    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        persoServ.deletePersona(id);
        return "la persona fue eliminada correctamente";
    }

    @PutMapping("/personas/editar/{id_original}")/*requiered es si necesita!
    osea si hay un cambio de id va a mostrar por la url*/
    public Persona editPersona(@PathVariable Long id_original,
                      @RequestParam(required = false,
                              name = "id")Long nuevaId,
                      @RequestParam(required = false,
                              name = "nombre")String nuevoNombre,
                      @RequestParam(required = false,
                              name = "apellido")String nuevoApellido,
                      @RequestParam(required = false,
                              name = "edad")int nuevaEdad){

        persoServ.editPersona(id_original,nuevaId,nuevoNombre,
                nuevoApellido,nuevaEdad);

        Persona perso = persoServ.findPersona(nuevaId);
        return perso;
    }
}








