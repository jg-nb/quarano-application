package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import quarano.tracking.Address;
import quarano.core.EmailAddress;
import quarano.core.PhoneNumber;

import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;

/**
 * A masterdata-entity, which can be assigned to an imported Concact.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "deliverycontact")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class DeliveryContact {

	@Id
	@GeneratedValue
	@Column(name = "deliverycontact_id")
	private UUID id;

	private String lastname, firstname;

	private Address address;
	public PhoneNumber phonenumber;
	public EmailAddress emailaddress;

	@Column(name = "deliverycontact_hash")
	private String hash;

	@Column(name = "deliverycontact_verified")
	private Boolean verified;

	@Column(name = "covid19_positive")
	private Date covidPositive;

	@Column(name = "deliverycontact_timestamp")
	private Date timestamp;

	public DeliveryContact(
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
		UUID.randomUUID();
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
		this.phonenumber = phonenumber;
		this.emailaddress = emailaddress;
		this.hash = hash;
		this.verified = verified;
		this.covidPositive = covidPositive;
		this.timestamp = timestamp;
	}
/* ERROR: constructor already defined
	// for testing purposes
	DeliveryContact(
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

		super();

		UUID.randomUUID();
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
		this.phonenumber = phoneNumber;
		this.emailaddress = emailaddress;
		this.hash = hash;
		this.verified = verified;
		this.covidPositive = covidPositive;
		this.timestamp = timestamp;
	}
*/
	/**
	 * Determines if DeliveryContact already exists
	 *
	 * @return
	 *
	public boolean isImported() {
		return hasAnyHash(DeliveryContact.HASH);
	}

	private boolean hasAnyHash(DeliveryContactHash... contactlist) {
		var newcontacts = List.of(contactlist);

		return this.contactlist.stream()
				.map(DeliveryContact::geHash).anyMatch(newcontacts::contains);
	}
	*/
};
