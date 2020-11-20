package quarano.delivery;

import quarano.delivery.ContactDeliveryList;
import quarano.delivery.ContactDeliveryList.AppIdentifier;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface ContactDeliveryListRepository extends CrudRepository<ContactDeliveryList, AppIdentifier> {

	static final String DEFAULT_SELECT = "select distinct d from Contactdelivery d ";

	@Query(DEFAULT_SELECT + "where d.processnumber = :processnumber")
	Optional<ContactDeliveryList> findByHash(String hash);
}
