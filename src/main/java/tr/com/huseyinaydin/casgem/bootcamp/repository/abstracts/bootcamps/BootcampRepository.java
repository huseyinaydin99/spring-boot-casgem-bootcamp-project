package tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.bootcamps;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.huseyinaydin.casgem.bootcamp.entities.bootcamps.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Integer> {
}
