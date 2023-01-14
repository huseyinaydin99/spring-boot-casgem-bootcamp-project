package tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.blacklists;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.casgem.bootcamp.entities.blacklists.Blacklist;

public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
    boolean existsBlacklistByApplicantId(int id);
}
