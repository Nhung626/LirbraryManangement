package com.huce.thuvien.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phieutra")
public class PhieuPH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPT;
    @Column(name = "ngayTra")
    private LocalDateTime ngayTra;
    @Column(name = "trangThai")
    private String trangThai;
    @OneToOne
    @JoinColumn(name = "MaPM")
    private PhieuMuon phieuMuon ;
    @ManyToOne
    @JoinColumn(name = "MaTT")
    private ThuThu thuThu;

    public PhieuPH(ThuThu thuThu,PhieuMuon pm) {
        this.phieuMuon = pm;
        this.trangThai = "Đang đợi";
        this.phieuMuon.setTrangThai("Đã duyệt");
        this.thuThu = thuThu;
    }
}
