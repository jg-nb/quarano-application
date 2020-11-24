package quarano.delivery;

import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryImport.ProcessIdentifier;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


interface DeliveryContactRepository extends CrudRepository<DeliveryContact, ProcessIdentifier> {

	static final String DEFAULT_SELECT = "select a from delivery.deliverycontact a ";

	//@Query(DEFAULT_SELECT + "where a.id = :id")
	//Optional<DeliveryContact> findByHash(String hash);
}
