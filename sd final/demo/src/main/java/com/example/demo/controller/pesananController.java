package com.example.demo.controller;
import com.example.demo.entitas.pesanan;
import com.example.demo.service.pesananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequestMapping("/api/pesanan")
public class pesananController {

    @Autowired
    private pesananService pesananService;

    // Create pesanan
    @PostMapping
    public ResponseEntity<?> buatPesanan(@RequestBody PesananRequest pesananRequest) {
        try {
            pesanan pesanan = pesananService.buatPesanan(pesananRequest.getMenuId(), pesananRequest.getCustomerId());
            return ResponseEntity.ok(pesanan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update pesanan
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePesanan(@PathVariable int id, @RequestParam String status) {
        try {
            pesanan updatedPesanan = pesananService.updatePesanan(id, status);
            return ResponseEntity.ok(updatedPesanan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Delete pesanan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePesanan(@PathVariable int id) {
        try {
            pesananService.deletePesanan(id);
            return ResponseEntity.ok("Pesanan dengan ID " + id + " berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get pesanan by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPesananById(@PathVariable int id) {
        try {
            pesanan pesanan = pesananService.getPesananById(id);
            return ResponseEntity.ok(pesanan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all pesanan
    @GetMapping
    public ResponseEntity<List<pesanan>> getAllPesanan() {
        List<pesanan> pesananList = pesananService.getAllPesanan();
        return ResponseEntity.ok(pesananList);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<pesanan>> getAllPesananByCustomerId(@PathVariable int customerId) {
        List<pesanan> pesananList = pesananService.getAllPesananByCustomerId(customerId);
        return ResponseEntity.ok(pesananList);
    }



}
