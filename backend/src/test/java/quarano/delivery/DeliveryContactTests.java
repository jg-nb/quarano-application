package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryContact;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class DelviveryContactTests {

	private final String lastname;
	private final String firstname;
	private final Address address;
	private final PhoneNumber phonenumber;
	private final EmailAddress emailaddress;
	private final String hash;
	private final Boolean verified;
	private final Date covidPositive;
	private final Date timestamp;

	DeliveryContact contact;

	@BeforeEach
	void setup() {
		contact = new DeliveryContact(
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
	}

	@Test
	void testContact() {

	}
}
