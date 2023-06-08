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
@Table(name = "thuThu")
public class ThuThu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTT;
    @Column(name = "hoTenTT")
    private String hoTenTT;
    @Column(name = "SDT")
    private String SDT;
    @Column(name = "NS")
    private LocalDate NS;
    @Column(name = "CCCD")
    private String CCCD;
    @Column(name = "DiaChi")
    private String DiaChi;
    @OneToMany(mappedBy = "thuThu", cascade = CascadeType.ALL)
    private List<PhieuPH> phieuTras = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;
}
