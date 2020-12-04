package quarano.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import quarano.core.EmailAddress;

import java.util.UUID;

import javax.persistence.GeneratedValue;

/**
 * @author Johannes Griebenow
 */
@Entity
@Table(name = "certificate")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PACKAGE)
public class Certificate {

	@Id
	@GeneratedValue
	@Column(name = "app_id")
	private UUID appId;

	@Column(name = "rsa_pub")
	private String publicKey;

	public Certificate(String publicKey) {
		UUID.randomUUID();
		this.publicKey = publicKey;
	}
/* ERROR: constructor already defined
	// for testing purposes
	Certificate(String rsa_pub) {

		UUID.randomUUID();
		this.rsa_pub = rsa_pub;
	}
*/
};
