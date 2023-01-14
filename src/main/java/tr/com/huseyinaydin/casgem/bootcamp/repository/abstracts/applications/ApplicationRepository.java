package tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.applications;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.casgem.bootcamp.entities.applications.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    boolean existsApplicationsByApplicantId(int userId);
    Application findApplicationByApplicantId(int userId);
}
