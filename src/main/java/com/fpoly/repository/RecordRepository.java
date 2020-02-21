package com.fpoly.repository;

import com.fpoly.model.Record;
import com.fpoly.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

    Page<Record> findAll(Pageable pageable);

    Record findByReason(String reason);

    Page<Record> findAllByReasonContains(String reason, Pageable pageable);

    Page<Record> findAllByStaff(Staff staff, Pageable pageable);

    List<Record> findByStaff(Staff staff);

}