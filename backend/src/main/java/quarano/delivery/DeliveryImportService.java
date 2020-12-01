package quarano.delivery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import quarano.delivery.DeliveryPlace.AppIdentifier;
import quarano.delivery.DeliveryPlace.ProcessIdentifier;
import quarano.core.EmailAddress;
import quarano.core.PhoneNumber;
import quarano.tracking.Address;

import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryImportService {

	private final @NonNull DeliveryContactRepository contacts;
	private final @NonNull DeliveryPlaceRepository places;
	//private final @NonNull AuthenticationManager authentication;

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
		var newcontact =  new DeliveryContact(
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

		var contact = contacts.save(newcontact);

		return contact;
	}

	public Optional<DeliveryContact> findByHash(String hash) {
		return contacts.findByHash(hash);
	}

	/**
	 * creates a new deliveryplace and stores it
	 *
	 * @param appId
	 * @param processnumber
	 * @param tag
	 * @param timestamp
	 * @param checkin
	 * @param checkout
	 * @param contact
	 * @return
	 */
	public DeliveryPlace createDeliveryPlace(
		String appId,
		String processnumber,
		String tag,
		Date timestamp,
		Date checkin,
		Date checkout,
		DeliveryContact contact
	) {
		var newplace =  new DeliveryPlace(
			appId,
			processnumber,
			tag,
			timestamp,
			checkin,
			checkout,
			contact
		);

		var place = places.save(newplace);

		return place;
	}

	public Optional<DeliveryPlace> findByProcessnumber(String processnumber) {
		return places.findByProcessnumber(processnumber);
	}

	/*
	public List<DeliveryPlace> findPlacesFor(DeliveryContact contact) {
		return places.findPlacesFor(contact)
				.filter(it -> hasContact(it))
				.collect(Collectors.toList());
	}
	*/
}
