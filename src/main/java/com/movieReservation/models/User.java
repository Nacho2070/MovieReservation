package com.movieReservation.models;

import com.movieReservation.models.enums.RoleEnum;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name= "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String name;

    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String Password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_roles" , joinColumns = @JoinColumn(name = "userId")
                                    ,inverseJoinColumns = @JoinColumn(name = "roleId") )
    private Set<Roles> rol = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    public void setTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public boolean isAccountNoExpired() {
        return true;
    }

    public boolean isAccountNoLocked() {
        return true;
    }

    public boolean isCredentialNoExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }


}

