package com.huce.thuvien.repository;

import com.huce.thuvien.entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Long> {
    PhieuMuon findByMaPM(Long maPM);
}
