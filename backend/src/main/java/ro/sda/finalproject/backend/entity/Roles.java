package ro.sda.finalproject.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Roles implements Serializable {
    @Id
    @Column(name = "role_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesName roles = RolesName.ROLE_USER;

}
