package quarano.delivery;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import quarano.tracking.Address;
import quarano.core.EmailAddress;
import quarano.core.PhoneNumber;

import java.util.UUID;


/**
 * A masterdata-entity, which can be assigned to a Concact.
 *
 * @author Johannes Griebenow
 */
//@Entity
@EqualsAndHashCode(of = "contactdelivery")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class ContactDelivery {
	private String lastname;
	private String firstname;
	private Address address;
	private PhoneNumber phonenumber;
	private EmailAddress emailaddress;

	public ContactDelivery(UUID id, String lastname, String firstname, Address address, PhoneNumber phonenumber, EmailAddress emailaddress) {

		UUID.randomUUID();
		this.lastname = this.getLastname();
		this.firstname = this.getFirstname();
		this.address = this.getAddress();
		this.phonenumber = this.getPhoneNumber();
		this.emailaddress = this.getEmailAddress();
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

	public String getHash() {
		// TODO Hash whole row for comparison value
		return firstname+lastname;
	}
};
