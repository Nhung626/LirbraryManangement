package com.huce.thuvien.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phieumuon")
public class PhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPM;
    @Column(name = "ngayMuon")
    private LocalDateTime ngayMuon;
    @Column(name = "trangThai")
    private String trangThai;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "phieuMuons")
    private Set<Sach> books = new HashSet<Sach>();
    @ManyToOne
    @JoinColumn(name = "MaDG")
    private DocGia docGia;

    public PhieuMuon(Set<Sach> books, DocGia docGia) {
        this.ngayMuon = LocalDateTime.now();
        this.trangThai= "Đợi duyệt";
        this.books = books;
        this.docGia = docGia;
    }
}
