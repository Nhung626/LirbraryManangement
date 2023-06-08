package com.huce.thuvien.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "giosach")
public class GioSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maGio;
     @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "giosachDG")
    private Set<Sach> books = new HashSet<Sach>();
    @OneToOne
    @JoinColumn(name = "MaDG")
    private DocGia docGia;

    public GioSach(DocGia docGia) {
        this.docGia = docGia;
        this.books = new HashSet<Sach>();
    }
}
