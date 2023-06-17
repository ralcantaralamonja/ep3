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
    private String nombre ;
    private String apellido ;
    private String correo ;
    private String numero ;

}
