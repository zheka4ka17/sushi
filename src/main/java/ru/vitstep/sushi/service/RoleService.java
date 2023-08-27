package ru.vitstep.sushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitstep.sushi.model.Role;
import ru.vitstep.sushi.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
@Autowired
    public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;

    }
@Transactional
    public Role findByName(String name){
    return roleRepository.findRoleByName(name).orElse(null);

    }

    public List<Role> findAll(){
    return roleRepository.findAll();
    }


}
