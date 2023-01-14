package tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.casgem.bootcamp.entities.users.Instructor;

import javax.transaction.Transactional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    boolean existsInstructortByNationalIdentity(String nationalIdentity);

    @Modifying
    @Query(value = "update Users set password = :newPassword where id = :id", nativeQuery = true)
    @Transactional
    void changePassword(String newPassword, int id);
}
