package com.ep3.grupo5.listeners;

import com.ep3.grupo5.entity.Cliente;
import jakarta.persistence.PreUpdate;

public class ModificationDateListener {
    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Cliente) {
            ((Cliente) entity).preUpdate();
        }
    }
}