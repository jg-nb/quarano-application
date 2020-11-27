package quarano.delivery;

import quarano.delivery.DeliveryPlace;
import quarano.delivery.DeliveryPlace.ProcessIdentifier;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


interface DeliveryPlaceRepository extends CrudRepository<DeliveryPlace, ProcessIdentifier> {

	static final String DEFAULT_SELECT = "select a from DeliveryPlace a ";

	@Query(DEFAULT_SELECT + "where a.processnumber = :processnumber")
	Optional<DeliveryPlace> findByProcessnumber(String processnumber);
}
