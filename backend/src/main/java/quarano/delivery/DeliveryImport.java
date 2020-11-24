package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import quarano.core.QuaranoAggregate;
import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryImport.AppIdentifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;

import org.jmolecules.ddd.types.Identifier;

/**
 * An Import from 3rd party applications.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "deliveryimport", schema = "delivery")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class DeliveryImport extends QuaranoAggregate<DeliveryImport, AppIdentifier>{

	@Column(name = "deliveryimport_processnumber")
	private ProcessIdentifier processnumber;

	/**
	 * nested exception is org.hibernate.MappingException:
	 * Repeated column in mapping for entity:
	 * quarano.delivery.DeliveryImport column:
	 * app_id (should be mapped with insert="false" update="false")
	 **/
	@Column(name = "deliveryimport_appid")
	private AppIdentifier appId;

	@Column(name = "deliveryimport_timestamp")
	private Date timestamp;

	@OneToMany
	private List<DeliveryContact> contactlist = new ArrayList<>();

	public DeliveryImport(
		String processnumber,
		UUID appId,
		Date timestamp,

		List<DeliveryContact> contactlist
	) {
		super();

		this.processnumber = ProcessIdentifier.of(processnumber);
		this.appId = AppIdentifier.of(appId);
		this.setTimestamp(timestamp);

		contactlist.stream().forEach(it -> this.contactlist.add(it));
	}

	// for testing purposes
	DeliveryImport(
		String fixedProcessnumber,
		UUID fixedAppId,
		Date timestamp,

		DeliveryContact contact
	) {
		super();

		this.processnumber = ProcessIdentifier.of(fixedProcessnumber);
		this.appId = AppIdentifier.of(fixedAppId);
		this.setTimestamp(timestamp);

		this.add(contact);
	}


	/**
	 * Adds a DelvieryContact to the list of contacts if it not already exists
	 *
	 * @param contact
	 */
	public DeliveryImport add(DeliveryContact contact) {

		if (contactlist.contains(contact)) {
			return this;
		}

		contactlist.add(contact);

		return this;
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

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

	}
