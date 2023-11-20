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
import java.time.LocalTime;
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
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/add/employee")
    public String addEmployee(@RequestParam("userId") User user, @RequestParam String snils) {
        Employee employee = new Employee();
        employee.setName(user.getName());
        employee.setSnils(snils);
        employee.setSurname(user.getSurname());
        employee.setUser(user);
        if (!employeeService.saveEmployee(employee)) {
            return "redirect:/error";
        }
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/worker")
    public String addWorker(@RequestParam("userId") User user, @RequestParam String specialization) {
        Employee employee = employeeService.getEmployee(user.getId());
        Worker worker = new Worker();
        worker.setEmployee(employee);
        worker.setSpecialization(specialization);
        workerService.saveWorker(worker);
        return "redirect:/admin/user/edit/" + user.getId();
    }


    @PostMapping("/admin/add/manufacturer")
    public String addmanufacturer(@RequestParam("userId") User user, @RequestParam String detail_specialization) {
        Employee employee = employeeService.getEmployee(user.getId());
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setEmployee(employee);
        manufacturer.setDetail_specialization(detail_specialization);
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/admin/user/edit/" + user.getId();
    }

    @PostMapping("/admin/add/operator")
    public String addoperator(@RequestParam("userId") User user, @RequestParam String workingTimeStart,@RequestParam String workingTimeEnd) {
        Employee employee = employeeService.getEmployee(user.getId());
        Operator operator = new Operator();
        operator.setEmployee(employee);
        operator.setWorkingTimeStart(LocalTime.parse(workingTimeStart));
        operator.setWorkingTimeEnd(LocalTime.parse(workingTimeEnd));
        operatorService.saveOperator(operator);
        return "redirect:/admin/user/edit/" + user.getId();
    }


}
