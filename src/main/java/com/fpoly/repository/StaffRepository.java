package com.fpoly.repository;

import com.fpoly.model.Depart;
import com.fpoly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
    Page<Staff> findAll(Pageable pageable);

    Staff findByName(String name);

    Page<Staff> findAllByNameContains(String name, Pageable pageable);

    Page<Staff> findAllByDepart(Depart depart, Pageable pageable);
}
