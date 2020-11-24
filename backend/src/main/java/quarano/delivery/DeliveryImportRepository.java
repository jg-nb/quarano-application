package quarano.delivery;

import quarano.delivery.DeliveryImport;
import quarano.delivery.DeliveryImport.AppIdentifier;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface DeliveryImportRepository extends CrudRepository<DeliveryImport, AppIdentifier> {

	static final String DEFAULT_SELECT = "select distinct a from delivery.deliveryimport a ";

	//@Query(DEFAULT_SELECT + "where a.processnumber = :processnumber and a.appid = :appid")
}
