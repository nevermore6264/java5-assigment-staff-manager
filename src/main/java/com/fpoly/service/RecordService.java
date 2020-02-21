package com.fpoly.service;

import com.fpoly.model.Record;
import com.fpoly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {
    Record findById(Long id);

    Page<Record> findAll(Pageable pageable);

    void save(Record record);

    boolean existName(String name);

    void delete(Long id);

    Page<Record> findAllByReasonContains(String reason, Pageable pageable);

    Page<Record> findAllByStaff(Staff staff, Pageable pageable);

    List<Record> findByStaff(Staff staff);
}
