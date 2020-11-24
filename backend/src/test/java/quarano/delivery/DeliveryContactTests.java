package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryContact;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@RequiredArgsConstructor
class DelviveryContactTests {

	private final String lastname;
	private final String firstname;
	private final Address address;
	private final PhoneNumber phonenumber;
	private final EmailAddress emailaddress;
	//private final String hash;

	@Mock DeliveryImport contactlist;
	DeliveryContact contact;

	@BeforeEach
	void setup() {
		contact = new DeliveryContact(lastname, firstname, address, phonenumber, emailaddress);
	}

	@Test
	void testContact() {

	}
}
