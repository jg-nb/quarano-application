package quarano.delivery;

import quarano.delivery.DeliveryContact;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


interface DeliveryContactRepository extends CrudRepository<DeliveryContact, String> {

	static final String DEFAULT_SELECT = "select a from DeliveryContact a ";

	@Query(DEFAULT_SELECT + "where a.hash = :hash")
	Optional<DeliveryContact> findByHash(String hash);
}
