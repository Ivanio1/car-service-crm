package carservicecrm.controllers;


import carservicecrm.models.*;
import carservicecrm.models.enums.Role;
import carservicecrm.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.security.Provider;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final WorkerService workerService;
    private final ManufacturerService manufacturerService;
    private final OperatorService operatorService;
    private final StoService stoService;
    private final OfferService offerService;
    private final ReviewService reviewService;
    private final DetailService detailService;
    private final DetailProviderService detailProviderService;
    private final AdministratorService administratorService;


    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @GetMapping("/admin/users")
    public String adminusers(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-users";
    }

    @GetMapping("/admin/active/users")
    public String adminactiveusers(Model model, Principal principal) {
        model.addAttribute("users", userService.activeUsers());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-active-users";
    }

    @GetMapping("/admin/clients")
    public String adminclients(Model model, Principal principal) {
        model.addAttribute("clients", userService.listClients());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-clients";
    }

    @GetMapping("/admin/workers")
    public String adminworkers(Model model, Principal principal) {
        model.addAttribute("workers", workerService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-workers";
    }

    @GetMapping("/admin/manufacturers")
    public String adminmanufacturers(Model model, Principal principal) {
        model.addAttribute("manufacturers", manufacturerService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-manufacturers";
    }

    @GetMapping("/admin/employees")
    public String adminemployees(Model model, Principal principal) {
        model.addAttribute("employees", employeeService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-employees";
    }

    @GetMapping("/admin/operators")
    public String adminoperators(Model model, Principal principal) {
        model.addAttribute("operators", operatorService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-operators";
    }

    @GetMapping("/admin/stos")
    public String adminstos(Model model, Principal principal) {
        model.addAttribute("stos", stoService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-stos";
    }

    @GetMapping("/admin/detailproviders")
    public String admindetailproviders(Model model, Principal principal) {
        model.addAttribute("providers", detailProviderService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-detailproviders";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("user1", userService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.values());
        model.addAttribute("stos", stoService.list());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/add/worker")
    public String addWorker(@RequestParam("userId") User user, @RequestParam String specialization) {
        Employee employee = employeeService.getEmployee(user.getId());
        Worker worker = new Worker();
        worker.setEmployee(employee);
        worker.setSpecialization(specialization);
        employee.setWorker(worker);
        workerService.saveWorker(worker);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/admin")
    public String addAdmin(@RequestParam("userId") User user) {
        Employee employee = employeeService.getEmployee(user.getId());
        Administrator administrator = new Administrator();
        administrator.setEmployee(employee);
        employee.setAdministrator(administrator);
        administratorService.saveAdmin(administrator);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/manufacturer")
    public String addmanufacturer(@RequestParam("userId") User user, @RequestParam String detail_specialization) {
        Employee employee = employeeService.getEmployee(user.getId());
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setEmployee(employee);
        manufacturer.setDetail_specialization(detail_specialization);
        employee.setManufacturer(manufacturer);
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/operator")
    public String addoperator(@RequestParam("userId") User user, @RequestParam String workingTimeStart, @RequestParam String workingTimeEnd) {
        Employee employee = employeeService.getEmployee(user.getId());
        Operator operator = new Operator();
        operator.setEmployee(employee);
        operator.setWorkingTimeStart(LocalTime.parse(workingTimeStart));
        operator.setWorkingTimeEnd(LocalTime.parse(workingTimeEnd));
        employee.setOperator(operator);
        operatorService.saveOperator(operator);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/sto")
    public String addsto(@RequestParam("userId") User user, @RequestParam String name, @RequestParam String phone) {
        Sto sto = new Sto();
        sto.setPhone(phone);
        sto.setName(name);
        stoService.saveSto(sto);
        return "redirect:/admin/stos";
    }

    @PostMapping("/admin/add/provider")
    public String addProvider(@RequestParam("userId") User user, @RequestParam String name, @RequestParam String contact) {
        DetailProvider provider = new DetailProvider();
        provider.setContact(contact);
        provider.setName(name);
        detailProviderService.saveProvider(provider);
        return "redirect:/admin/detailproviders";
    }


    @PostMapping("/delete/sto/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        stoService.deleteSto(id);
        return "redirect:/admin/stos";
    }

    @PostMapping("/delete/provider/{id}")
    public String deleteProvider(@PathVariable Long id, Principal principal) {
        detailProviderService.deleteProvider(id);
        return "redirect:/admin/detailproviders";
    }

    @PostMapping("/admin/sto/employees/{id}")
    public String stoemployees(Model model, Principal principal, @PathVariable Long id) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("employees", stoService.getStoEmployees(id));
        model.addAttribute("user", user);
        model.addAttribute("sto", stoService.getSto(id));
        return "sto-employees";
    }

    @PostMapping("/admin/provider/details/{id}")
    public String providerdetails(Model model, Principal principal, @PathVariable Long id) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("details", detailProviderService.getProviderDetails(id));
        model.addAttribute("user", user);
        model.addAttribute("provider", detailProviderService.getProvider(id));
        return "provider-details";
    }

    @PostMapping("/admin/add/detail/provider")
    public String addDetail(@RequestParam("userId") User user, @RequestParam String name, @RequestParam Integer stock, @RequestParam Integer price, @RequestParam String provider) {
        Detail detail = new Detail();
        detail.setName(name);
        detail.setPrice(price);
        detail.setStock(stock);
        detail.setStoragestock(0);
        DetailProvider provider1 = detailProviderService.getProviderByName(provider);
        detailProviderService.addDetailToProvider(provider1.getId(), detail);
        detailService.saveDetail(detail);
        return "redirect:/admin/detailproviders";
    }

    @PostMapping("/admin/sto/{stoid}/delete/employee/{id}")
    public String deleteEmployeeFromSto(@RequestParam("email") String email, @PathVariable Long id, @PathVariable Long stoid) {
        stoService.removeEmployeeFromSto(stoid, employeeService.getEmployeeById(id));
        return "redirect:/admin/stos";
    }

    @PostMapping("/admin/provider/{providerid}/delete/detail/{id}")
    public String deleteDetailFromProvider(@RequestParam("email") String email, @PathVariable Long id, @PathVariable Long providerid) {
        detailProviderService.removeDetailFromProvider(providerid, detailService.getDetailById(id));
        return "redirect:/admin/detailproviders";
    }

    @PostMapping("/admin/buy/detail/from/{id}")
    public String buyDetailFromProvider(@RequestParam("email") String email, @PathVariable Long id, @RequestParam Long detail, @RequestParam Integer storagestock) {
        Detail detail1 = detailService.getDetailById(detail);
        Integer tmp = detail1.getStock();
        detailService.updateStorageStock(detail1.getId(), storagestock);
        return "redirect:/admin/details";
    }

    @GetMapping("/admin/details")
    public String admindetails(Model model, Principal principal) {
        model.addAttribute("details", detailService.listStorage());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin-details";
    }


    @PostMapping("/admin/delete/review/{id}")
    public String deleteReview(@RequestParam List<String> offers, @PathVariable Long id) {
        for (String offer : offers) {
            offerService.removeReviewFromOffer(offerService.getOfferByName(offer).getId(), reviewService.getReview(id));
        }
        return "redirect:/user/reviews";
    }


    @PostMapping("/admin/add/employee/sto")
    public String addEmployee(@RequestParam("userId") User user, @RequestParam String snils, @RequestParam MultiValueMap<String, String> form) {
        Employee employee = new Employee();
        employee.setName(user.getName());
        employee.setSnils(snils);
        employee.setSurname(user.getSurname());
        employee.setUser(user);
        userService.getUserByEmail(user.getEmail()).setEmployee(employee);

        for (String key : form.keySet()) {
            if (!(Objects.equals(key, "snils") || Objects.equals(key, "userId") || Objects.equals(key, "_csrf"))) {
                Sto sto1 = stoService.getStoByName(key);
                stoService.addEmployeeToSto(sto1.getId(), employee);
                employeeService.saveEmployee(employee);
            }
        }

        return "redirect:/admin/user/edit/" + user.getId();
    }

}
