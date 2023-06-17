package com.ep3.grupo5.repository;

import com.ep3.grupo5.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
