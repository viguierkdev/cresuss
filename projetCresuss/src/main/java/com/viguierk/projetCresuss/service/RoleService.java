package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.model.Role;
import com.viguierk.projetCresuss.repository.RoleRepository;
import com.viguierk.projetCresuss.repository.RoleRepository;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleById(int id);

    public void deleteRole( int id);

    public Role saveRole(Role role);

    public RoleRepository getRoleRepository();

    public void setRoleRepository(RoleRepository roleRepository);
}
