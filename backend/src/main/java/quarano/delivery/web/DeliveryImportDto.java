package quarano.delivery.web;

import lombok.Data;
import lombok.Getter;

import quarano.delivery.DeliveryContact;
import quarano.delivery.DeliveryPlace;
import quarano.delivery.DeliveryImportService;

import org.springframework.validation.Errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
public class DeliveryImportDto {

	private DeliveryContact contact;
	private DeliveryPlace place;
	//private @Hash @NotBlank String hash;

	DeliveryImportDto validate(Errors errors, DeliveryImportService deliveryimports) {

		//validateContact(errors, this.contact.getHash(), deliveryimports);

		return this;
	}
}
