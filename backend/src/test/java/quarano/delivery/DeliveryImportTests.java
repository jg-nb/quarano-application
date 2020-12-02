package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.DeliveryImport;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.DeliveryContact;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class DelviveryImportTests {

	private final DeliveryContact contact;
	private final DeliveryPlace place;

	DeliveryImport deliveryimport;

	@BeforeEach
	void setup() {
		deliveryimport = new DeliveryImport(contact, place);
	}

	@Test
	void testImport() {

	}
}
