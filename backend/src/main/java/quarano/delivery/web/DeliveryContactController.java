package quarano.delivery.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryContact;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;

import java.util.Date;

import java.net.URI;

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
class DeliveryContactController {

	@PostMapping("/deliveryimport/{appId}/{processnumber}")
	HttpEntity<?> addDeliveryContact(
		@PathVariable String appId, @PathVariable String processnumber,
		@Valid @RequestBody DeliveryContact payload,
		Errors errors
	) {
		return ResponseEntity
			.created(URI.create(fromMethodCall(processnumber).toUriString()))
			.body(createDeliveryContact(
				//processnumber,
				//appId,
				payload.getLastname(),
				payload.getFirstname(),
				payload.getAddress(),
				payload.phonenumber,
				payload.emailaddress,
				payload.getHash(),
				payload.getVerified(),
				payload.getCovidPositive(),
				payload.getTimestamp()
			));
	}

	/**
	 * creates a new deliverycontact and stores it
	 *
	 * @param lastname
	 * @param firstname
	 * @param address
	 * @param phonenumber
	 * @param emailaddress
	 * @param hash
	 * @param verified
	 * @param covidPositive
	 * @param timestamp
	 * @return
	 */
	public DeliveryContact createDeliveryContact(
		//String processnumber,
		//String appId,
		String lastname,
		String firstname,
		Address address,
		PhoneNumber phonenumber,
		EmailAddress emailaddress,
		String hash,
		Boolean verified,
		Date covidPositive,
		Date timestamp
	) {
		var contact =  new DeliveryContact(
			//processnumber,
			//appId,
			lastname,
			firstname,
			address,
			phonenumber,
			emailaddress,
			hash,
			verified,
			covidPositive,
			timestamp
		);

		return contact;
	}
}
