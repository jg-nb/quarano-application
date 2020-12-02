package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.DeliveryPlace.ProcessIdentifier;
import quarano.delivery.DeliveryPlace.AppIdentifier;
import quarano.delivery.DeliveryContact;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class DelviveryPlaceTests {

	private final String processnumber, appId, tag;
	private final Date checkin, checkout, timestamp;
	private final DeliveryContact contact;

	DeliveryPlace place;

	@BeforeEach
	void setup() {
		place = new DeliveryPlace(processnumber, appId, tag, timestamp, checkin, checkout, contact);
	}

	@Test
	void testPlace() {

	}
}
