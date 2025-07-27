package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    //les méthodes roleRepository.<methode> sont désormais possibles car RoleRepository extends CrudRepository

    @Override
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int id){
        Role role = roleRepository.findById(id).orElse(null);
 		if(role == null){
			return null;
		} else {
			return role;
		}
    }

    @Override
    public void deleteRole( int id){
        roleRepository.delete(roleRepository.findById(id).get());
    }

    @Override
    public Role saveRole(Role role){
        roleRepository.save(role);
        return role;
    }

    @Override
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    @Override
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
