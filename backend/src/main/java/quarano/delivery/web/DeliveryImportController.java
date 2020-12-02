package quarano.delivery.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;
import static quarano.tracking.Address.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryImport;
import quarano.delivery.DeliveryImportService;
import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.web.DeliveryImportDto;
import quarano.delivery.web.DeliveryImportRepresentations;
import quarano.tracking.Address;
import quarano.tracking.ZipCode;
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
	 * Test Data:
'{"firstname":"Max","lastname":"Mustermann","hash":"aA1bB2cC3","verified":"false","street":"fakestreet","housenumber":"123","city":"Berlin","zipcode":"12345","phonenumber":"012345678910","emailaddress":"max.mustermann@aol.com","timestamp":"2020-12-02T10:00:00.000+00:00","tag":"Platz1"}'
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

				Date timestamp;
				try {
					timestamp = it.getTimestamp();
				} catch(Exception e) {
					timestamp = new Date();
				}

				var contact = deliveryimports.createDeliveryContact(
					it.getLastname(),
					it.getFirstname(),
					new Address(it.getStreet(), HouseNumber.of(it.getHousenumber()), it.getCity(), ZipCode.of(it.getZipcode())),
					PhoneNumber.of(it.getPhonenumber()),
					EmailAddress.of(it.getEmailaddress()),
					it.getHash(),
					it.getVerified(),
					it.getCovidPositive(),
					timestamp
				);

				var place = deliveryimports.createDeliveryPlace(
					appId,
					processnumber,
					it.getTag(),
					timestamp,
					it.getCheckin(),
					it.getCheckout(),
					contact
				);

				return new DeliveryImport(contact, place);
			}).concludeIfValid(it -> {

				return ResponseEntity.ok(processnumber);
						//.created(URI.create(fromMethodCall(it).toUriString()))
						//.body(representations.toRepresentation(it));
			});
	}
}
