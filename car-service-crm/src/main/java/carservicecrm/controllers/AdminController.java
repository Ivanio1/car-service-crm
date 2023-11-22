package carservicecrm.controllers;


import carservicecrm.models.*;
import carservicecrm.models.enums.Role;
import carservicecrm.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

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
        model.addAttribute("stos",stoService.list());
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

    @PostMapping("/delete/sto/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        stoService.deleteSto(id);
        return "redirect:/admin/stos";
    }

    @PostMapping ("/admin/sto/employees/{id}")
    public String stoemployees(Model model, Principal principal,@PathVariable Long id) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("employees", stoService.getStoEmployees(id));
        model.addAttribute("user", user);
        model.addAttribute("sto", stoService.getSto(id));
        return "sto-employees";
    }

    @PostMapping("/admin/add/employee/sto")
    public String addEmployee(@RequestParam("userId") User user, @RequestParam String snils,@RequestParam String sto) {
        Employee employee = new Employee();
        employee.setName(user.getName());
        employee.setSnils(snils);
        employee.setSurname(user.getSurname());
        employee.setUser(user);
        userService.getUserByEmail(user.getEmail()).setEmployee(employee);
        Sto sto1 = stoService.getStoByName(sto);
        stoService.addEmployeeToSto(sto1.getId(), employee);
        employeeService.saveEmployee(employee);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/sto/{stoid}/delete/employee/{id}")
    public String deleteEmployeeFromSto(@RequestParam("email") String email, @PathVariable Long id, @PathVariable Long stoid) {
        stoService.removeEmployeeFromSto(stoid,employeeService.getEmployeeById(id));
        return "redirect:/admin/stos";
    }

}
