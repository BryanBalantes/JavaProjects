package com.commercial.logbook_app.controller;

import com.commercial.logbook_app.dto.LeaseDTO;
import com.commercial.logbook_app.service.CommercialUnitService;
import com.commercial.logbook_app.service.LeaseService;
import com.commercial.logbook_app.service.TenantPaymentRecordService;
import com.commercial.logbook_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/leases")
public class LeaseController {

    private final LeaseService leaseService;
    private final UserService userService;
    private final TenantPaymentRecordService paymentService;
    private final CommercialUnitService commercialUnitService;

    public LeaseController(LeaseService leaseService,
                           UserService userService,
                           TenantPaymentRecordService paymentService,
                           CommercialUnitService commercialUnitService) {
        this.leaseService = leaseService;
        this.userService = userService;
        this.paymentService = paymentService;
        this.commercialUnitService = commercialUnitService;
    }

    // ✅ LIST ALL LEASES
    @GetMapping
    public String list(Model model) {
        model.addAttribute("leases", leaseService.getAll());
        return "leases/list";
    }

    // ✅ VIEW SINGLE LEASE
    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("lease", leaseService.getById(id));
        return "leases/view";
    }


    // ✅ SHOW CREATE PAGE
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("lease", new LeaseDTO());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("units", commercialUnitService.getAvailableUnits());
        return "leases/add";
    }

    // ✅ CREATE LEASE (WITH IMAGE)
    @PostMapping("/add")
    public String create(
            @Valid @ModelAttribute("lease") LeaseDTO leaseDTO,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAll());
            return "leases/add";
        }

        leaseService.create(leaseDTO, file);
        return "redirect:/users/";
    }

    // ✅ SHOW UPDATE PAGE
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable int id, Model model) {

        LeaseDTO lease = leaseService.getById(id);

        model.addAttribute("lease", leaseService.getById(id));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("units", commercialUnitService.getUnitsForUpdate(lease.getCommercialUnitId())
        );
        return "leases/update";
    }

    // ✅ UPDATE LEASE (WITH IMAGE)
//    @PostMapping("/update")
//    public String update(
//            @Valid @ModelAttribute("lease") LeaseDTO leaseDTO,
//            BindingResult result,
//            @RequestParam(value = "file", required = false) MultipartFile file,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("users", userService.getAll());
//            return "leases/update";
//        }
//
//        leaseService.update(leaseDTO, file);
//        return "redirect:/leases";
//    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute("lease") LeaseDTO leaseDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        leaseService.update(leaseDTO, file);

        return "redirect:/users/view/" + leaseDTO.getUserId();
    }

    // ✅ DELETE LEASE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        leaseService.delete(id);
        return "redirect:/users/";
    }

    // ✅ VIEW LEASES BY USER
    @GetMapping("/user/{userId}")
    public String getByUser(@PathVariable int userId, Model model) {
        model.addAttribute("leases", leaseService.getByUser(userId));
        model.addAttribute("user", userService.getById(userId));
        return "leases/user-leases";
    }


}