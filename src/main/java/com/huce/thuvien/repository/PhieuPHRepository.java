package com.huce.thuvien.repository;

import com.huce.thuvien.entity.PhieuPH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhieuPHRepository extends JpaRepository<PhieuPH,Long> {
    PhieuPH findByMaPT(Long id);
}
