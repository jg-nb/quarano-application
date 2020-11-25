package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import quarano.tracking.Address;
import quarano.core.EmailAddress;
import quarano.core.PhoneNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.jmolecules.ddd.types.Identifier;

/**
 * A masterdata-entity, which can be assigned to an imported Concact.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "deliverycontact")
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class DeliveryContact {

	@Id
	@GeneratedValue
	@Column(name = "deliverycontact_id")
	private UUID id;

	private ProcessIdentifier processnumber;
	private AppIdentifier appId;

	private String lastname, firstname;

	private Address address;
	private PhoneNumber phonenumber;
	private EmailAddress emailaddress;

	/**
	 * ... more parameters can be added
	 * Do not forget to add Getter and Setter too.
	 * Do not forget do update "create table deliveryoncact" in
	 * ./main/resources/db/V1001__initial_schema.sql too.
	 */
	/*
	@Column(name = "deliverycontact_hash")
	private Hash hash;
	*/
	@Column(name = "deliverycontact_timestamp")
	private Date timestamp;

	public DeliveryContact(
		String processnumber,
		String appId,
		String lastname,
		String firstname,
		Address address,
		PhoneNumber phonenumber,
		EmailAddress emailaddress,
		//Hash hash,
		Date timestamp
	) {
		UUID.randomUUID();
		this.processnumber = ProcessIdentifier.of(processnumber);
		this.appId = AppIdentifier.of(appId);
		this.lastname = this.getLastname();
		this.firstname = this.getFirstname();
		this.address = this.getAddress();
		this.phonenumber = this.getPhoneNumber();
		this.emailaddress = this.getEmailAddress();
		//this.hash = this.getHash();
		this.timestamp = this.getTimestamp();
	}

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
	@Embeddable
	@EqualsAndHashCode
	@RequiredArgsConstructor(staticName = "of")
	@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
	public static class ProcessIdentifier implements Identifier, Serializable {

		final String processnumber;
	}

	@Embeddable
	@EqualsAndHashCode
	@RequiredArgsConstructor(staticName = "of")
	@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
	public static class AppIdentifier implements Identifier, Serializable {

		final String appId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PhoneNumber getPhoneNumber() {
		return phonenumber;
	}

	public void setPhoneNumber(PhoneNumber phonenumber) {
		this.phonenumber = phonenumber;
	}

	public EmailAddress getEmailAddress() {
		return emailaddress;
	}

	public void setEmailAddress(EmailAddress emailaddress) {
		this.emailaddress = emailaddress;
	}

	/*
	public Hash getHash() {
		// TODO Hash whole row for comparison value
		return this.hashCode();
	}

	public void setHash(Hash hash) {
		this.hash = hash;
	}
	*/

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
};
