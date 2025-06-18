package com.autonexo.user.domain.model.entities;

import com.autonexo.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * Mechanic entity
 * <p>
 *     This entity represents the role of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public Driver(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public Driver(Long userId) {
        this.userId = userId;
    }

    public int getDriverUserId(int id) {
        return user.getId().intValue();
    }
}
