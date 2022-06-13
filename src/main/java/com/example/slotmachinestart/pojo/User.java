package com.example.slotmachinestart.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "User.getbyName", query = "SELECT u FROM User u WHERE u.name = :name")
})
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @NonNull
    private String name;
    @NonNull
    private String pwd;
    private float balance=0;
}
