package com.huce.thuvien.repository;

import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.GioSach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioSachRepository extends JpaRepository<GioSach, Long> {
    GioSach findByDocGia(DocGia docGia);
}
