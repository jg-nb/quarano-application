package quarano.delivery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import quarano.delivery.Certificate;
import quarano.delivery.CertificateRepository;
import quarano.core.EmailAddress;

import java.util.UUID;
import java.util.Base64;
import java.util.Optional;

import java.security.*;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@Component
@RequiredArgsConstructor
@Slf4j
public class CertificateService {

	private final @NonNull CertificateRepository certificates;

	/**
	 * creates a new RSA private / public Pair, mails both Keys and stores the PublicKey
	 *
	 * @param emailaddress
	 * @return
	 */
	public Certificate createCertificate(EmailAddress emailaddress) throws NoSuchAlgorithmException {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);

		KeyPair keyPair = keyGen.genKeyPair();
		String privateKey = bytesToEncodedString(keyPair.getPrivate().getEncoded());
		String publicKey = bytesToEncodedString(keyPair.getPublic().getEncoded());

		var newcertificate =  new Certificate(
			publicKey
		);

		var certificate = certificates.save(newcertificate);
		mailKeys(privateKey, emailaddress, publicKey);

		return certificate;
	}

	public Optional<Certificate> findById(UUID appId) {
		return certificates.findById(appId);
	}

	private static String bytesToEncodedString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

	public void mailKeys(String privateKey, EmailAddress emailaddress, String publicKey) {
		System.out.println("MAILING...");
		//TODO: ADD MAILING HERE
	}
}
