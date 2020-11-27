package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import quarano.tracking.Address;
import quarano.core.PhoneNumber;
import quarano.core.EmailAddress;
import quarano.delivery.DeliveryContact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.jmolecules.ddd.types.Identifier;

/**
 * A masterdata-entity, which can be assigned to an imported Concact.
 *
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "deliveryplace")
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class DeliveryPlace {

	@EmbeddedId
	private ProcessIdentifier processnumber;
	private AppIdentifier appId;

	@Column(name = "deliveryplace_tag")
	private String tag;

	@Column(name = "deliveryplace_timestamp")
	private Date timestamp;

	private Date checkin, checkout;

	@ManyToOne
	@JoinColumn(name = "deliverycontact_id")
	private DeliveryContact contact;

	public DeliveryPlace(
		String processnumber,
		String appId,
		String tag,
		Date timestamp,
		Date checkin,
		Date checkout,
		DeliveryContact contact
	) {
		this.processnumber = ProcessIdentifier.of(processnumber);
		this.appId = AppIdentifier.of(appId);
		this.tag = tag;
		this.timestamp = timestamp;
		this.checkin = checkin;
		this.checkout = checkout;

		this.contact = contact;
	}
	/*
	// for testing purposes
	DeliveryPlace(
		String processnumber,
		String appId,
		String tag,
		Date timestamp,
		Date checkin,
		Date checkout,
		Deliverycontact contact
	) {
		this.processnumber = ProcessIdentifier.of(processnumber);
		this.appId = AppIdentifier.of(appId);
		this.tag = tag;
		this.timestamp = timestamp;
		this.checkin = checkin;
		this.checkout = checkout;

		this.contact = contact;
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
};
