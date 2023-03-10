package tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.casgem.bootcamp.entities.users.Applicant;

import javax.transaction.Transactional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    @Modifying
    @Query(value = "insert into Applicants (about,id) VALUES (:about,:id)", nativeQuery = true)
    @Transactional
    void beAnApplicant(@Param("about") String about, @Param("id") int id);

    @Modifying
    @Query(value = "delete from Applicants where id = :id", nativeQuery = true)
    @Transactional
    void removeAnApplicant(@Param("id") int id);

    @Modifying
    @Query(value = "delete from Applications where applicant_id = :id", nativeQuery = true)
    @Transactional
    void removeApplicantFromApplication(@Param("id") int id);

    @Modifying
    @Query(value = "delete from Blacklists where applicant_id = :id", nativeQuery = true)
    @Transactional
    void removeApplicantFromBlacklist(@Param("id") int id);

    boolean existsApplicantByNationalIdentity(String nationalIdentity);
    @Modifying
    @Query(value = "update Users set password = :newPassword where id = :id", nativeQuery = true)
    @Transactional
    void changePassword(String newPassword, int id);
}
