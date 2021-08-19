package com.iplume.fi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * fi authority对象.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fi_authority")
public class FiAuthority implements GrantedAuthority {

    /**
     * Id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    UserRoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }

}
