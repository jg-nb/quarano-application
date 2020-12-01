package quarano.delivery.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryImport;
import quarano.delivery.DeliveryImportService;
import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.web.DeliveryImportRepresentations;
import quarano.delivery.web.DeliveryImportDto;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;
import quarano.core.web.MappedPayloads;

import java.util.Date;

import java.net.URI;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class DeliveryImportController {

	private final @NonNull DeliveryImportService deliveryimports;
	private final @NonNull DeliveryImportRepresentations representations;

	/**
	 * (non - Javadoc)
	 * Test URI:
	 curl -X POST http://localhost:8080/internal/deliveryimport/placelogg_ID/prcnr_test -H "Content-Type: application/json" -d '{"contact":{"firstname":"Max","lastname":"Mustermann"},"place":{"tag":"Platz1"}}'
	 */
	@PostMapping(path="/internal/deliveryimport/{appId}/{processnumber}", consumes="application/json")
	HttpEntity<?> addDeliveryImport(
		@PathVariable String appId, @PathVariable String processnumber,
		@Valid @RequestBody DeliveryImportDto payload,
		Errors errors
	) {
		return MappedPayloads.of(payload, errors)
			.alwaysMap((it, nested) -> it.validate(nested, deliveryimports))
			.map(it -> {
				var contact = it.getContact();
				var newcontact = deliveryimports.createDeliveryContact(
					contact.getLastname(),
					contact.getFirstname(),
					contact.getAddress(),
					contact.phonenumber,
					contact.emailaddress,
					contact.getHash(),
					contact.getVerified(),
					contact.getCovidPositive(),
					contact.getTimestamp()
				);

				var place = it.getPlace();
				var newplace = deliveryimports.createDeliveryPlace(
					appId,
					processnumber,
					place.getTag(),
					place.getTimestamp(),
					place.getCheckin(),
					place.getCheckout(),
					place.getContact()
				);

				return new DeliveryImport(newcontact, newplace);
			}).concludeIfValid(it -> {
				//var process = on(DeliveryImportController.class).getContact(it.getId(), it);

				return ResponseEntity.ok(processnumber);
				/*return ResponseEntity
						.created(URI.create(fromMethodCall(location).toUriString()))
						.body(representations.toSummary(it));*/
			});
	}
}
