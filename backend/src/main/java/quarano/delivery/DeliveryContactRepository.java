package quarano.delivery;

import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryContact.ProcessIdentifier;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


interface DeliveryContactRepository extends CrudRepository<DeliveryContact, ProcessIdentifier> {

	static final String DEFAULT_SELECT = "select a from DeliveryContact a ";

	@Query(DEFAULT_SELECT + "where a.processnumber = :processnumber")
	Optional<DeliveryContact> findByProcessnumber(String processnumber);
	//Optional<DeliveryContact> findByHash(String hash);
}
