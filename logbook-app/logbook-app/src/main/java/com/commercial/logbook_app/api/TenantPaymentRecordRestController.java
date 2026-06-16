package com.commercial.logbook_app.api;

import com.commercial.logbook_app.dto.TenantPaymentRecordDTO;
import com.commercial.logbook_app.service.TenantPaymentRecordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

    //@RestController
    //@RequestMapping("/api/records")
    //public class TenantPaymentRecordRestController {
    //
    //   private final TenantPaymentRecordService tenantPaymentRecordService;
    //
    //
    //   public TenantPaymentRecordRestController(TenantPaymentRecordService tenantPaymentRecordService){
    //       this.tenantPaymentRecordService = tenantPaymentRecordService;
    //   }
    //
    //   @GetMapping
    //   public List<TenantPaymentRecordDTO> getAll() {
    //       return tenantPaymentRecordService.getAll();
    //   }
    //
    //   @GetMapping("/{id}")
    //   public TenantPaymentRecordDTO getById(@PathVariable int id) {
    //      return tenantPaymentRecordService.getById(id);
    //   }
    //
    ////   @PostMapping
    ////    public void create(@RequestBody TenantPaymentRecordDTO tenantPaymentRecordDTO) {
    ////        tenantPaymentRecordService.create(tenantPaymentRecordDTO);
    ////   }
    ////
    ////   @PutMapping
    ////    public void update(@RequestBody TenantPaymentRecordDTO tenantPaymentRecordDTO) {
    ////       tenantPaymentRecordService.update(tenantPaymentRecordDTO);
    ////   }
    //
    //    @PostMapping
    //    public void create(@RequestBody TenantPaymentRecordDTO dto) {
    //        tenantPaymentRecordService.create(dto);
    //    }
    //
    //    @PutMapping
    //    public void update(@RequestBody TenantPaymentRecordDTO dto) {
    //        tenantPaymentRecordService.update(dto);
    //    }
    //
    //    @PostMapping("/upload/{id}")
    //    public void upload(@PathVariable Integer id,
    //                       @RequestParam("file") MultipartFile file) {
    //
    //        tenantPaymentRecordService.uploadImage(id, file);
    //    }
    //
    //   @DeleteMapping("/{id}")
    //    public void delete(@PathVariable int id) {
    //       tenantPaymentRecordService.delete(id);
    //   }
    //}

    @RestController
    @RequestMapping("/api/records")
    public class TenantPaymentRecordRestController {

        private final TenantPaymentRecordService tenantPaymentRecordService;

        public TenantPaymentRecordRestController(TenantPaymentRecordService service) {
            this.tenantPaymentRecordService = service;
        }

        @GetMapping
        public List<TenantPaymentRecordDTO> getAll() {
            return tenantPaymentRecordService.getAll();
        }

        @GetMapping("/{id}")
        public TenantPaymentRecordDTO getById(@PathVariable int id) {
            return tenantPaymentRecordService.getById(id);
        }

        @PostMapping
        public void create(@RequestBody TenantPaymentRecordDTO dto) {
            tenantPaymentRecordService.create(dto);
        }

        @PutMapping
        public void update(@RequestBody TenantPaymentRecordDTO dto) {
            tenantPaymentRecordService.update(dto);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable int id) {
            tenantPaymentRecordService.delete(id);
        }
    }