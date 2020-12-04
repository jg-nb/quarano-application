package quarano.delivery;

import quarano.delivery.DeliveryContact;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


interface CertificateRepository extends CrudRepository<Certificate, UUID> {

	static final String DEFAULT_SELECT = "select a from Certificate a ";

	@Query(DEFAULT_SELECT + "where a.appId = :appId")
	Optional<Certificate> findById(UUID appId);
}
