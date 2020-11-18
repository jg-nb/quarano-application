package quarano.account;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.ContactDelivery;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class ContactDeliveryTests {

	private final UUID id;
	private final String lastname;
	private final String firstname;
	private final Address address;
	private final PhoneNumber phonenumber;
	private final EmailAddress emailaddress;

	@Mock ContactDeliveryList contacts;
	ContactDelivery contact;

	@BeforeEach
	void setup() {
		contact = new ContactDelivery(id, lastname, firstname, address, phonenumber, emailaddress);
	}

	@Test
	void testContact() {

	}
}
