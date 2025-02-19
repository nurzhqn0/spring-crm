package kz.project.spring_crm.repository;

import kz.project.spring_crm.models.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByIsHandled(boolean isHandled);

    List<ApplicationRequest> findByIsHandledFalse();

    List<ApplicationRequest> findByIsHandledTrue();

    @Query("SELECT a FROM ApplicationRequest a WHERE a.username LIKE %:keyword% OR a.courseName LIKE %:keyword%")
    List<ApplicationRequest> searchByKeyword(@Param("keyword") String keyword);
}
