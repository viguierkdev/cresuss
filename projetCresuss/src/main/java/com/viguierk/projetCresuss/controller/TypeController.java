package com.viguierk.projetCresuss.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.service.TypeService;


@RestController
@RequestMapping("/types")// toutes les méthodes de cette classe se basent sur l'URL "/types"
public class TypeController {

    @Autowired
    TypeService typeService;

		@GetMapping
    public List<Type> showAllTypes(){
		return this.typeService.getAllTypes();
	}
	
	record NewTypeRequest(String name, double seuil) {}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Type> addType(@RequestBody NewTypeRequest type){
		Type newType = Type.builder()
			.name(type.name())
			.seuil(type.seuil())
			.build();
		Type savedType = this.typeService.saveType(newType);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Type updateType(@RequestBody Type type){
        return this.typeService.saveType(type);
    }

    /**
	 * Update - Update an existing type
	 * @param id - The id of the type to update
	 * @param type - The new type object
	 * @return
	 */
	@PutMapping(path="{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Type updateTypeFromId(@PathVariable("id") final Integer id,@RequestBody Type type){
		Type currentType = typeService.getTypeById(id);

		if(currentType == null){
			return null;
		} else {
			
			String nom = type.getName();
			if(nom != null) {
				currentType.setName(nom);
			}
			double seuil = type.getSeuil();
			if(nom != null) {
				currentType.setSeuil(seuil);
			}
			typeService.saveType(currentType);
			return currentType;
		}
    }
	
	@GetMapping("{id}")
	public Type getTypeById(@PathVariable int id){
        Type optType = typeService.getTypeById(id);
		if(optType == null){
			return null;
		} else {
			return optType;
		}
			
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteType(@PathVariable("id") final Integer usrId){
        try {
            this.typeService.deleteType(usrId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
