package com.ep3.grupo5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_cliente")
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String nombre ;
    @Column(length = 150)
    private String apellido ;

    @Column(length = 150,unique=true)
    private String correo ;
    @Column(length = 9,unique=true)
    private String numero ;

}
