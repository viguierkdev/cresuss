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

import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.service.RoleService;
import com.viguierk.projetCresuss.service.RoleService;

@RestController
@RequestMapping("/roles")// toutes les méthodes de cette classe se basent sur l'URL "/roles"
public class RoleController {

    @Autowired
    RoleService roleService;

	@GetMapping
    public List<Role> showAllRoles(){
		return this.roleService.getAllRoles();
	}
	
	record NewRoleRequest(String name) {}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> addRole(@RequestBody NewRoleRequest role){
		Role newRole = Role.builder()
			.name(role.name())
			.build();
		Role savedRole = this.roleService.saveRole(newRole);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Role updateRole(@RequestBody Role role){
        return this.roleService.saveRole(role);
    }

    /**
	 * Update - Update an existing role
	 * @param id - The id of the role to update
	 * @param role - The new role object
	 * @return
	 */
	@PutMapping(path="{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Role updateRoleFromId(@PathVariable("id") final Integer id,@RequestBody Role role){
		Role currentRole = roleService.getRoleById(id);

		if(currentRole == null){
			return null;
		} else {
			String nom = role.getName();
			if(nom != null) {
				currentRole.setName(nom);
			}
			roleService.saveRole(currentRole);
			return currentRole;
		} 
    }
	
	@GetMapping("{id}")
	public Role getRoleById(@PathVariable int id){
        Role optRole = roleService.getRoleById(id);
		if(optRole == null){
			return null;
		} else {
			return optRole;
		}
			
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") final Integer usrId){
        try {
            this.roleService.deleteRole(usrId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
