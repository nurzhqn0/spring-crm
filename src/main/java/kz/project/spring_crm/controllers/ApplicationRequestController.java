package kz.project.spring_crm.controllers;

import kz.project.spring_crm.models.ApplicationRequest;
import kz.project.spring_crm.services.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class ApplicationRequestController {

    private final ApplicationRequestService requestService;

    @Autowired
    public ApplicationRequestController(ApplicationRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public String getRequests(@RequestParam(required = false) Boolean isHandled, Model model) {
        List<ApplicationRequest> requests;

        if (Boolean.FALSE.equals(isHandled)) {
            requests = requestService.getUnhandledRequests();
        } else if (Boolean.TRUE.equals(isHandled)) {
            requests = requestService.getHandledRequests();
        } else {
            requests = requestService.getAllRequests();
        }

        model.addAttribute("requests", requests);
        return "index";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        return "request-form";
    }

    @PostMapping("/add")
    public String createRequest(@ModelAttribute ApplicationRequest request) {
        requestService.saveRequest(request);
        return "redirect:/";
    }


    @GetMapping("/requests/{id}")
    public String getRequestById(@PathVariable Long id, Model model) {
        ApplicationRequest request = requestService.getRequestById(id);
        if (request == null) {
            return "error/404";
        }
        model.addAttribute("request", request);
        return "request-detail";
    }

    @PostMapping("/requests/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/";
    }


    @PostMapping("/requests/{id}/status")
    public String updateRequestStatus(@PathVariable Long id, Model model) {
        ApplicationRequest updatedRequest = requestService.updateRequestStatus(id);

        if (updatedRequest == null) {
            model.addAttribute("errorMessage", "Запрашиваемая заявка не найдена.");
            return "error/404";
        }

        return "redirect:/";
    }

    @PostMapping("/requests/{id}/edit")
    public String editRequest(@PathVariable Long id, @ModelAttribute ApplicationRequest updatedRequest) {
        ApplicationRequest existingRequest = requestService.getRequestById(id);

        if (existingRequest != null) {
            existingRequest.setUsername(updatedRequest.getUsername());
            existingRequest.setCourseName(updatedRequest.getCourseName());
            existingRequest.setComment(updatedRequest.getComment());
            existingRequest.setPhone(updatedRequest.getPhone());
            existingRequest.setHandled(existingRequest.isHandled());

            requestService.saveRequest(existingRequest);
        }

        return "redirect:/";
    }

}

