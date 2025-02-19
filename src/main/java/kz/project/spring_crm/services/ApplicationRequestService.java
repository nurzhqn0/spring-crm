package kz.project.spring_crm.services;

import kz.project.spring_crm.models.ApplicationRequest;
import kz.project.spring_crm.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationRequestService {
    private final ApplicationRequestRepository repository;

    @Autowired
    public ApplicationRequestService(ApplicationRequestRepository repository) {
        this.repository = repository;
    }

    public List<ApplicationRequest> getAllRequests() {
        return repository.findAll();
    }

    public List<ApplicationRequest> getHandledRequests() {
        return repository.findByIsHandledTrue();
    }

    public List<ApplicationRequest> getUnhandledRequests() {
        return repository.findByIsHandledFalse();
    }

    public ApplicationRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ApplicationRequest saveRequest(ApplicationRequest request) {
        return repository.save(request);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }

    public ApplicationRequest updateRequestStatus(Long id) {
        return repository.findById(id).map(request -> {
            request.setHandled(true);
            return repository.save(request);
        }).orElse(null);
    }
}
