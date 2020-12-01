package quarano.delivery.web;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryImport;

import org.springframework.stereotype.Component;

/**
 * Component to provide mapping capabilities between {@link DeliveryImport} and their representations.
 *
 * @author Johannes Griebenow
 */
@Component
@RequiredArgsConstructor
class DeliveryImportRepresentations {

	DeliveryImportDto toRepresentation(DeliveryImport deliveryimport) {
		return new DeliveryImportDto(/*deliveryimport.getContact(), deliveryimport.getPlace()*/);
	}
}
