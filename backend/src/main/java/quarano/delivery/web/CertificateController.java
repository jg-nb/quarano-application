package quarano.delivery.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;
import static quarano.tracking.Address.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.delivery.CertificateService;
import quarano.delivery.web.EmailAddressDto;
import quarano.delivery.web.EmailAddressMappingConfiguration;
import quarano.core.EmailAddress;
import quarano.core.web.MappedPayloads;

import java.util.Date;

import java.net.URI;
import java.util.stream.Collectors;

import java.security.*;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class Certificatecontroller {

	private final @NonNull CertificateService certificates;

	@PostMapping(path="/internal/createkey", consumes="application/json")
	HttpEntity<?> addCertificate(
		@Valid @RequestBody EmailAddressDto payload,
		Errors errors
	) {
		return MappedPayloads.of(payload, errors)
			.alwaysMap((it, nested) -> it.validate(nested, certificates))
			.map(it -> {

				try {
					return certificates.createCertificate(
						EmailAddress.of(it.getEmailaddress())
					);
				} catch(NoSuchAlgorithmException e) {
					return null;
				}

			}).concludeIfValid(it -> {

				return ResponseEntity.ok(String.valueOf(it.getAppId()));
						//.created(URI.create(fromMethodCall(it).toUriString()))
						//.body(representations.toRepresentation(it));
			});
	}
}
