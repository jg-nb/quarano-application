package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import quarano.core.QuaranoAggregate;
import quarano.delivery.ContactDelivery;
import quarano.delivery.ContactDeliveryList.AppIdentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;

import org.jmolecules.ddd.types.Identifier;

/**
 * An List of contacts. Imported from 3rd party applications.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "deliverimports")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class ContactDeliveryList extends QuaranoAggregate<ContactDeliveryList, AppIdentifier>{

	@Column(name = "deliveryimports_processnumber")
	private String processnumber;

	@Column(name = "deliveryimports_id")
	private UUID appId;

	@Column(name = "deliveryimports_timestamp")
	private Date timestamp;

	@Column(name = "deliveryimports_hash")
	private String hash;
	//private List<ContactDelivery> contacts = new ArrayList<>();

	public ContactDeliveryList(
		String processnumber,
		Date timestamp,
		String hash
		//List<ContactDelivery> contacts
	) {
		super();

		this.id = AppIdentifier.of(UUID.randomUUID());
		this.setProcessnumber(processnumber);
		this.setTimestamp(timestamp);

		this.setHash(hash);
		//contacts.stream().forEach(it -> this.contacts.add(it));
	}

		// for testing purposes
	ContactDeliveryList(
		String processnumber,
		UUID appId,
		Date timestamp,
		String hash
		//ContactDelivery contact
	) {
		super();

		this.setProcessnumber(processnumber);
		this.id = AppIdentifier.of(appId);
		this.setTimestamp(timestamp);

		this.setHash(hash);
		//this.add(contact);
	}


	/**
	 * Adds a contact to the list of contacts if is not already included
	 *
	 * @param contact
	 *
	public ContactDeliveryList add(ContactDelivery contact) {

		if (contacts.contains(contact)) {
			return this;
		}

		contacts.add(contact);

		return this;
	}

		/**
		 * Determines if Import already exists
		 *
		 * @return
		 *
		public boolean isImported() {
			// for test purposes only
			return hasAnyHash(Contactdelivery.TEST_HASH);
		}

		private boolean hasAnyHash(ContactHash... contacts) {
			var newcontacts = List.of(contacts);

			return this.contacts.stream()
					.map(ContactDelivery::geContactHash).anyMatch(newcontacts::contains);
		}
		*/
		@Embeddable
		@EqualsAndHashCode
		@RequiredArgsConstructor(staticName = "of")
		@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
		public static class AppIdentifier implements Identifier, Serializable {

			private static final long serialVersionUID = 7871473225101042167L;

			final UUID appId;

			/*
			 * (non-Javadoc)
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return appId.toString();
			}
		}

		public String getProcessnumber() {
			return processnumber;
		}

		public void setProcessnumber(String processnumber) {
			this.processnumber = processnumber;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

	}
