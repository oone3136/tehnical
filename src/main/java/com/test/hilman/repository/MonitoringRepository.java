package com.test.hilman.repository;

import com.test.hilman.entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {
//    @Query(value = "SELECT * FROM monitoring WHERE " +
//            "(:nama_lengkap IS NULL OR LOWER(nama_lengkap) LIKE LOWER(CONCAT('%', :nama_lengkap, '%'))) AND " +
//            "(:nik IS NULL OR nik = :nik);",
//            nativeQuery = true)
    @Query(value = "SELECT * " +
            "FROM monitoring " +
            "WHERE " +
            "    (:nama_lengkap IS NULL OR LOWER(nama_lengkap) LIKE LOWER(CONCAT('%', :nama_lengkap, '%'))) AND \n" +
            "    (:nik IS NULL OR CAST(nik AS VARCHAR) LIKE CONCAT('%', CAST(:nik AS VARCHAR), '%'));",
    nativeQuery = true)
    List<Monitoring> searchByNikOrNamaLengkap(@Param("nama_lengkap") String nama,
                                              @Param("nik") Long nik);


    Optional<Monitoring> findByNamaLengkap(String namaLengkap);
}
