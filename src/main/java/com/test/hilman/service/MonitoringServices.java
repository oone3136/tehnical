package com.test.hilman.service;

import com.test.hilman.entity.Monitoring;
import com.test.hilman.exception.ResourceNotFoundException;
import com.test.hilman.repository.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class MonitoringServices {

    private final MonitoringRepository repository;

    public List<Monitoring> getAll(){
        return repository.findAll();
    }
    public Monitoring getByIdMonitoring(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("tidak ada data dengan "+ id +"ini"));
    }
    public List<Monitoring> getNamaLengkap(String nama_lengkap, Long nik) {
        List<Monitoring> result = repository.searchByNikOrNamaLengkap(nama_lengkap, nik);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Tidak ada data");
        }
        return result;
    }


    public Monitoring createMonitoring(Monitoring request)
    {
        try {
            Monitoring monitoring = new Monitoring();
            Monitoring cekNIK = repository.findById(request.getId()).orElse(new Monitoring());
            if (cekNIK.getId() != null)
            {
                throw new ResourceNotFoundException("Error nik tidak boleh sama");
            }
            monitoring.setId(cekNIK.getId());
            Monitoring cekNama = repository.findByNamaLengkap(request.getNamaLengkap()).orElse(new Monitoring());
            if (StringUtils.isBlank(request.getNamaLengkap()))
            {
                throw new ResourceNotFoundException("Error nama tidak boleh kosong");
            }
            monitoring.setNamaLengkap(cekNama.getNamaLengkap());
            monitoring.setJenisKelamin(request.getJenisKelamin());
            monitoring.setTanggalLahir(request.getTanggalLahir());
            monitoring.setAlamat(request.getAlamat());
            monitoring.setNegara(request.getNegara());
            Monitoring create = new Monitoring(request.getId(), request.getNamaLengkap(), request.getTanggalLahir(), request.getJenisKelamin(), request.getAlamat(), request.getNegara());
            return repository.save(create);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Monitoring updateMonitoring(Monitoring request, Long id)
    {
        repository.findById(id);
        try {
            Monitoring monitoring = new Monitoring();
            monitoring.setId(request.getId());
            Monitoring cekNama = repository.findByNamaLengkap(request.getNamaLengkap()).orElse(new Monitoring());
            if (StringUtils.isBlank(request.getNamaLengkap()))
            {
                throw new ResourceNotFoundException("Error nama tidak boleh kosong");
            }
            monitoring.setJenisKelamin(request.getJenisKelamin());
            monitoring.setTanggalLahir(request.getTanggalLahir());
            monitoring.setNegara(request.getNegara());
            Monitoring update = new Monitoring(request.getId(), request.getNamaLengkap(), request.getTanggalLahir(), request.getJenisKelamin(), request.getAlamat(), request.getNegara());
            return repository.save(update);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteMonitoring(Long id)
    {
        repository.deleteById(id);
    }

}
