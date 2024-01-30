package com.test.hilman.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data @Setter @Getter @NoArgsConstructor
@Entity @Table(name = "Monitoring")
public class Monitoring {

    @Id
    @Column(name = "NIK", unique = true)
    private Long id;
    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;
    @Column(name = "tanggal_lahir", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggalLahir;
    @Column(name = "alamat", nullable = false)
    private String alamat;
    @Enumerated(EnumType.STRING)
    @Column(name = "negara", nullable = false)
    private Negara negara;

    public Monitoring(Long id, String namaLengkap, Date tanggalLahir, String jenisKelamin, String alamat, Negara negara) {
        this.id =id;
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.negara = negara;
    }
}
