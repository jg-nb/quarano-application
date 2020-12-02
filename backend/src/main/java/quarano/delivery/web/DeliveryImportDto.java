package quarano.delivery.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.DeliveryImportService;

import java.util.Date;

import org.springframework.validation.Errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
@Setter
public class DeliveryImportDto {

	private String firstname, lastname, street, housenumber, city, zipcode, phonenumber, emailaddress, hash, tag;
	private Boolean verified;
	private Date covidPositive, checkin, checkout, timestamp;

	DeliveryImportDto validate(Errors errors, DeliveryImportService deliveryimports) {

		//validateContact(errors, this.getContact().getHash(), deliveryimports);

		return this;
	}
}
