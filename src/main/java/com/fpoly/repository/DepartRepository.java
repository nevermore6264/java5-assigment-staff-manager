package com.fpoly.repository;

import com.fpoly.model.Depart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartRepository extends PagingAndSortingRepository<Depart, Long> {
    Depart findByName(String name);

    Page<Depart> findAll(Pageable pageable);
}
