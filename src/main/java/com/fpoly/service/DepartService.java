package com.fpoly.service;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartService {
    Depart save(Depart depart);

    boolean existName(String name);

    Depart findById(Long id);

    Page<Depart> findAll(Pageable pageable);

    void delete(Long id);

    Page<Depart> findAllByNameContains(String name, Pageable pageable);

}
