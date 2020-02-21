package com.fpoly.service;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    Staff findById(Long id);

    Page<Staff> findAll(Pageable pageable);

    void save(Staff staff);

    boolean existName(String name);

    void delete(Long id);

    Page<Staff> findAllByNameContains(String name, Pageable pageable);

    Page<Staff> findAllByDepart(Depart depart, Pageable pageable);

    List<Staff> findByDepart(Depart depart);
}
