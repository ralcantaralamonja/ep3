package com.ep3.grupo5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(length = 100,unique=true)
    private String correo ;
    @Column(length = 9,unique=true)
    private String numero ;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @PrePersist
    protected void antesDeInsertar() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    protected void antesDeActualizar() {
        fechaModificacion = new Date();
    }
}
