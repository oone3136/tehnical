package com.test.hilman.repository;

import com.test.hilman.entity.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {
    @Query(value = "SELECT * FROM monitoring WHERE LOWER(nama_lengkap) LIKE LOWER(CONCAT('%', :keywords, '%'))", nativeQuery = true)
    List<Monitoring> searchByNamaLengkap(@Param("keywords") String keywords);

    Optional<Monitoring> findByNamaLengkap(String namaLengkap);
}
