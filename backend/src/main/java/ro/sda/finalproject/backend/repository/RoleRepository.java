package ro.sda.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.finalproject.backend.entity.AppRole;
import ro.sda.finalproject.backend.entity.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<AppRole,Long> {

    AppRole findByRole(RoleName roles);

}
