package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryContact.ProcessIdentifier;
import quarano.delivery.DeliveryContact.AppIdentifier;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class DelviveryContactTests {

	private final String processnumber;
	private final String appId;
	private final String lastname;
	private final String firstname;
	private final Address address;
	private final PhoneNumber phonenumber;
	private final EmailAddress emailaddress;
	//private final Hash hash;
	private final Date timestamp;

	DeliveryContact contact;

	@BeforeEach
	void setup() {
		contact = new DeliveryContact(
			processnumber,
			appId,
			lastname,
			firstname,
			address,
			phonenumber,
			emailaddress,
			//hash,
			timestamp
		);
	}

	@Test
	void testContact() {

	}
}
