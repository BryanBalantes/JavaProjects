package com.commercial.logbook_app.controller;

import com.commercial.logbook_app.config.USER_TYPE;
import com.commercial.logbook_app.dto.CommercialUnitDTO;
import com.commercial.logbook_app.dto.LeaseDTO;
import com.commercial.logbook_app.dto.TenantPaymentRecordDTO;
import com.commercial.logbook_app.dto.UserDTO;
import com.commercial.logbook_app.repository.LeaseRepository;
import com.commercial.logbook_app.service.CommercialUnitService;
import com.commercial.logbook_app.service.LeaseService;
import com.commercial.logbook_app.service.TenantPaymentRecordService;
import com.commercial.logbook_app.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/users")
public class UserController {
  private UserService userService;
  private final TenantPaymentRecordService paymentService;
  private final LeaseService leaseService;
  private final CommercialUnitService commercialUnitService;

  public UserController(UserService userService, TenantPaymentRecordService paymentService,
                        LeaseService leaseService,
                        CommercialUnitService commercialUnitService) {
    this.userService = userService;
    this.paymentService = paymentService;
    this.leaseService = leaseService;
    this.commercialUnitService = commercialUnitService;
  }

  @GetMapping("/")
  public String listAll(Model model) {
    model.addAttribute("title", "Users");
    List<UserDTO> users = userService.getAll();
    model.addAttribute("users", users);
    return "users/list";
  }

  @GetMapping("/view/{id}")
  public String getViewPage(Model model, @PathVariable int id) {

    UserDTO userDTO = userService.getById(id);
    model.addAttribute("title", "Tenant");
    model.addAttribute("user", userDTO);

    if (USER_TYPE.ADMIN.getType().equalsIgnoreCase(userDTO.getType())) {
      model.addAttribute("title", "MyProfile");
      return "users/user-admin";
    }

    List<TenantPaymentRecordDTO> paymentRecords =
            paymentService.getByUserId(id);
    model.addAttribute("paymentRecords", paymentRecords);

    // 👇 PUT IT HERE (replace old lease code)
    LeaseDTO lease = leaseService.getActiveByUser(id)
            .stream()
            .findFirst()
            .orElse(null);

    model.addAttribute("lease", lease);

    List<CommercialUnitDTO> units = commercialUnitService.getAllUnits();
    model.addAttribute("units", units);

//    List<LeaseDTO> leases = leaseService.getActiveByUser(id);
//    model.addAttribute("leases", leases);
    return "users/user";
  }

//  @GetMapping("/update/{id}")
//  public String getUpdatePage(Model model, @PathVariable int id) {
//    UserDTO userDTO = userService.getById(id);
//    model.addAttribute("user", userDTO);
//    return "users/user";
//  }

  @GetMapping("/update/{id}")
  public String getUpdatePage(Model model, @PathVariable int id) {

    UserDTO userDTO = userService.getById(id);
    model.addAttribute("user", userDTO);

    List<TenantPaymentRecordDTO> paymentRecords =
            paymentService.getByUserId(id);
    model.addAttribute("paymentRecords", paymentRecords);

    LeaseDTO lease = leaseService.getActiveByUser(id)
            .stream()
            .findFirst()
            .orElse(null);

    model.addAttribute("lease", lease);

    List<CommercialUnitDTO> units = commercialUnitService.getAllUnits();
    model.addAttribute("units", units);

    return "users/user";
  }

  @GetMapping("/add")
  public String getAddPage(Model model) {
    model.addAttribute("title", "Tenants");
    model.addAttribute("user", new UserDTO());
    return "users/add";
  }

//  @PostMapping("/add")
//  public String create(
//      @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
//
//    if (result.hasErrors()) {
//      model.addAttribute(
//          "errors",
//          result.getAllErrors().stream()
//              .map(error -> error.getDefaultMessage())
//              .collect(Collectors.toUnmodifiableList()));
//      return "users/add";
//    }
//
//    userService.create(userDTO);
//    if (userDTO.hasErrors()) {
//      model.addAttribute("errors", userDTO.getErrors());
//      return "users/add";
//    }
//
//    return "redirect:/users/";
//  }

  @PostMapping("/add")
  public String create(
          @Valid @ModelAttribute("user") UserDTO userDTO,
          BindingResult result,
          Model model) {

    userDTO.setType(USER_TYPE.GENERAL.getType());

    if (result.hasErrors()) {
      model.addAttribute(
              "errors",
              result.getAllErrors().stream()
                      .map(error -> error.getDefaultMessage())
                      .collect(Collectors.toUnmodifiableList()));

      return "users/add";
    }

    userService.create(userDTO);

    if (userDTO.hasErrors()) {
      model.addAttribute("errors", userDTO.getErrors());
      return "users/add";
    }

    return "redirect:/users/";
  }

  /*
  * Ito ay bahagi ng create na kung saan merong image
  * This mapping is to be able to retrieve the image
  * but need to update the UserService by adding this method
  * public byte[] getUserImage(int id) throws IOException {
      return fileStorageService.get(getById(id).getProfilePicLocation(), "C:\\Users\\User\\Desktop\\JavaWebPractice\\JavaProjects\\logbook-app\\logbook-app\\src\\main\\resources\\static\\images");
  }
  * to retrieve the image base on the user id.
  * */
//  @GetMapping("/{id}/image")
//  @ResponseBody
//  public byte[] getUserImage(@PathVariable int id) throws IOException {
//    return userService.getUserImage(id);
//  }

//  @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
//  @ResponseBody
//  public byte[] getUserImage(@PathVariable int id) throws IOException {
//    return userService.getUserImage(id);
//  }

//  @PutMapping
//  public String update(@ModelAttribute("user") UserDTO userDTO) {
//    userService.update(userDTO);
//    return "redirect:/users/";
//  }

  @PostMapping("/update")
  public String update(@ModelAttribute("user") UserDTO userDTO) {
    userService.update(userDTO);
    return "redirect:/users/";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    userService.delete(id);
    return "redirect:/users/";
  }
}
