package quarano.delivery;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryPlace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author Johannes Griebenow
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class DeliveryImport {

	private DeliveryContact contact;
	private DeliveryPlace place;

	public DeliveryImport(
		DeliveryContact contact,
		DeliveryPlace place
	) {
		this.contact = contact;
		this.place = place;
	}
/* ERROR: constructor already defined
	// for testing purposes
	DeliveryImport(
		DeliveryContact contact,
		DeliveryPlace place
	) {
		this.contact = contact;
		this.place = place;
	}
*/
};
