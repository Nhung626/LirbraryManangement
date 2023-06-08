package com.huce.thuvien.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "docgia")
public class DocGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaDG;
    @Column(name = "hoTenDG")
    private String hoTenDG;
    @Column(name = "SDT")
    private String SDT;
    @Column(name = "NS")
    private LocalDate NS;
    @Column(name = "CCCD")
    private String CCCD;
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;

    @OneToMany(mappedBy = "docGia", cascade = CascadeType.ALL)
    private List<PhieuMuon> phieuMuons = new ArrayList<>();
    public DocGia(String ho_tenDG, String SDT, LocalDate NS, String CCCD, Account account) {
        hoTenDG = ho_tenDG;
        this.SDT = SDT;
        this.NS = NS;
        this.CCCD = CCCD;
        this.account = account;
    }
}
