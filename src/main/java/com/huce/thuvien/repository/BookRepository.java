package com.huce.thuvien.repository;

import java.util.List;
import com.huce.thuvien.entity.Sach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface BookRepository extends JpaRepository<Sach, Long>{
    @Query("SELECT b FROM Sach b WHERE b.tensach LIKE %?1%" +
            " OR b.tacgia LIKE %?1%" +
            " OR b.theloai LIKE %?1%")
    List<Sach> search(String keyword);
    Sach findByMaSach(Long id);
}
