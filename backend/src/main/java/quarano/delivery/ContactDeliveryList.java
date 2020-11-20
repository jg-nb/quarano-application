package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.validation.Errors;

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
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.jmolecules.ddd.types.Identifier;

/**
 * An List of contacts. Imported from 3rd party applications.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "contactdelivery")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class ContactDeliveryList extends QuaranoAggregate<ContactDeliveryList, AppIdentifier>{

	private static final AppIdentifier APPIDENTIFIER = null;

	@Column(name = "contactdelivery_processnumber")
	private String processnumber;

	@Id
	@Column(name = "contactdelivery_id")
	private UUID appId;

	@Column(name = "timestamp")
	private Date timestamp;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<ContactDelivery> contacts = new ArrayList<>();

	public ContactDeliveryList(String processnumber, Date timestamp, List<ContactDelivery> contacts) {
			super();

			this.id = AppIdentifier.of(UUID.randomUUID());
			this.setProcessnumber(processnumber);
			this.setTimestamp(timestamp);

			contacts.stream().forEach(it -> this.contacts.add(it));
		}

		// for testing purposes
		ContactDeliveryList(String processnumber, UUID appId, Date timestamp, ContactDelivery contact) {

			super();

			this.setProcessnumber(processnumber);
			this.id = AppIdentifier.of(appId);
			this.setTimestamp(timestamp);

			this.add(contact);
		}


		/**
		 * Adds a contact to the list of contacts if is not already included
		 *
		 * @param contact
		 */
		public ContactDeliveryList add(ContactDelivery contact) {

			if (contacts.contains(contact)) {
				return this;
			}

			contacts.add(contact);

			return this;
		}

		/**
		 * Determines if App is authenticated
		 *
		 * @return
		 */
		public boolean isAuthenticated() {
			return hasAnyAppIdentifier(APPIDENTIFIER);
		}

		private boolean hasAnyAppIdentifier(AppIdentifier appId) {
			// TODO
			var res = false;

			if(appId != null) {
				res = true;
			}

			return res;
		}

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
