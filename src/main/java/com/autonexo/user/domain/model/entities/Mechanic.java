package com.autonexo.user.domain.model.entities;

import com.autonexo.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * Mechanic entity
 * <p>
 *     This entity represents the role of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public Mechanic(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public Mechanic(Long user_Id) {
        this.userId = user_Id;
    }

    /**
     * Get the name of the role as a string
     * @return the name of the role as a string
     */
    public int getMechanicUserId(int id) {
        return user.getId().intValue();
    }

}
