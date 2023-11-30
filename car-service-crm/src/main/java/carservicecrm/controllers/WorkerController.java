package carservicecrm.controllers;

import carservicecrm.models.WorkerRequest;
import carservicecrm.services.UserService;
import carservicecrm.services.WorkerRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_WORKER','ROLE_ADMIN')")
public class WorkerController {
    private final WorkerRequestService workerRequestService;
    private final UserService userService;

    @GetMapping("/worker/panel")
    public String panel(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
//        model.addAttribute("questions", questionService.list());
        return "worker";
    }


    @PostMapping("/worker/add/request")
    public String saveRequest(@RequestParam("email") String email, @RequestParam String questionText) {
        WorkerRequest workerRequest = new WorkerRequest();
        workerRequest.setRequestText(questionText);
        workerRequest.setWorker(userService.getUserByEmail(email).getEmployee().getWorker());
        workerRequestService.save(workerRequest);
        return "redirect:/profile";
    }
}
