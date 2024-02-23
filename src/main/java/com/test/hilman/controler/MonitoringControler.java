package com.test.hilman.controler;

import com.test.hilman.entity.Monitoring;
import com.test.hilman.service.MonitoringServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class MonitoringControler {

    private final MonitoringServices services;

    @GetMapping("/monitoring")
    public List<Monitoring> getAllMonitoring(){
        return services.getAll();
    }

    @PostMapping("/monitoring/byNama")
    public List<Monitoring> getByNama(@RequestBody Map<String, String> requestBody) {
        String nama_lengkap = requestBody.get("nama_lengkap");
        Long nik = null;
        if (requestBody.containsKey("nik")) {
            String nikString = requestBody.get("nik");
            if (!nikString.isEmpty()) {
                nik = Long.valueOf(nikString);
            }
        }
        return services.getNamaLengkap(nama_lengkap, nik);
    }
    @GetMapping("/monitoring/{id}")
    public Monitoring getById(@PathVariable("id") Long id)
    {
        return services.getByIdMonitoring(id);
    }

    @PostMapping("/monitoring")
    public Monitoring createMonitoring(@RequestBody Monitoring request)
    {
        return services.createMonitoring(request);
    }
    @PutMapping("/monitoring/{id}")
    public Monitoring updateMonitoring(@PathVariable("id") Long id, @RequestBody Monitoring request)
    {
        return services.updateMonitoring(request, id);
    }
    @DeleteMapping("/monitoring/{id}")
    public void delleteMonitoring(@PathVariable("id") Long id)
    {
        services.deleteMonitoring(id);
    }
}

