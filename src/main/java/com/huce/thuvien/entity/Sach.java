package com.huce.thuvien.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sach")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSach;
    @Column(name = "tensach" )
    private String tensach;
    @Column(name = "tacgia" )
    private String tacgia;
    @Column(name = "theloai" )
    private String theloai;
    @Column(name = "soluong" )
    private int soluong;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "SachMuon",
            joinColumns = { @JoinColumn(name = "MaSach") },
            inverseJoinColumns = {@JoinColumn(name = "MaPM") })
    private Set<PhieuMuon> phieuMuons = new HashSet<PhieuMuon>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "SachCho",
            joinColumns = { @JoinColumn(name = "maSach") },
            inverseJoinColumns = {@JoinColumn(name = "maGio") })
    private Set<GioSach> giosachDG = new HashSet<GioSach>();

    public Sach(String tensach, String tacgia, String theloai, int soluong) {
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.theloai = theloai;
        this.soluong = soluong;
    }
}
