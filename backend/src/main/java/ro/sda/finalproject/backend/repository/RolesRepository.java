package ro.sda.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.finalproject.backend.entity.Roles;
import ro.sda.finalproject.backend.entity.RolesName;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findByRole(RolesName role);

}
