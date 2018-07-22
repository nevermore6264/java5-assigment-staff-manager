package com.fpoly.service.impl;

import com.fpoly.model.Depart;
import com.fpoly.repository.DepartRepository;
import com.fpoly.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartServiceImpl implements DepartService {

    private DepartRepository departRepository;

    @Autowired
    public DepartServiceImpl(DepartRepository departRepository) {
        this.departRepository = departRepository;
    }


    @Override
    public Depart save(Depart depart) {
        return this.departRepository.save(depart);
    }

    @Override
    public boolean existName(String name) {
        Depart depart = departRepository.findByName(name);
        return (depart != null);
    }

    @Override
    public Depart findById(Long id) {
        return departRepository.findOne(id);
    }

    @Override
    public Page<Depart> findAll(Pageable pageable) {
        return departRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        departRepository.delete(id);
    }
}

