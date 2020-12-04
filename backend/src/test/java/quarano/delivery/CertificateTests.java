package quarano.delivery;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.delivery.Certificate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class CertificateTests {

	private final String publicKey;

	Certificate certificate;

	@BeforeEach
	void setup() {
		certificate = new Certificate(publicKey);
	}

	@Test
	void testCertificate() {

	}
}
