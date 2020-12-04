package quarano.delivery.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import quarano.delivery.CertificateService;

import java.util.Date;

import org.springframework.validation.Errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
@Setter
public class EmailAddressDto {

	private String emailaddress;

	EmailAddressDto validate(Errors errors, CertificateService certificates) {

		//validateEmailAddress(errors, this.getEmailaddress(), certificates);

		return this;
	}
}
