package com.bookMyShow.bookMyShow.preseed;

import com.bookMyShow.bookMyShow.models.Privilege;
import com.bookMyShow.bookMyShow.models.PrivilegeEnum;
import com.bookMyShow.bookMyShow.models.Role;
import com.bookMyShow.bookMyShow.models.RoleEnum;
import com.bookMyShow.bookMyShow.repositories.PrivilegeRepository;
import com.bookMyShow.bookMyShow.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Privilege readPrivilege
                = createPrivilegeIfNotFound(PrivilegeEnum.READ_PRIVILIGE);
        Privilege writePrivilege
                = createPrivilegeIfNotFound(PrivilegeEnum.WRITE_PRIVILIGE);

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound(RoleEnum.ADMIN, adminPrivileges);
        createRoleIfNotFound(RoleEnum.USER, Arrays.asList(readPrivilege));

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(PrivilegeEnum name) {

        Privilege privilege = privilegeRepository.findByName(name);

        if (privilege == null) {
            privilege = Privilege
                    .builder()
                    .name(name)
                    .build();

            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(RoleEnum name, List<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role
                    .builder()
                    .name(name)
                    .privileges(privileges)
                    .build();
            roleRepository.save(role);
        }
        return role;
    }


}
