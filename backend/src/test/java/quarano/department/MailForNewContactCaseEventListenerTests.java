package quarano.department;

import static org.assertj.core.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import quarano.QuaranoIntegrationTest;
import quarano.account.DepartmentDataInitializer;
import quarano.account.DepartmentRepository;
import quarano.department.TrackedCase.CaseCreated;
import quarano.department.TrackedCase.MailStatus;
import quarano.department.TrackedCaseEventListener.EmailSendingEvents;
import quarano.tracking.TrackedPersonDataInitializer;
import quarano.tracking.TrackedPersonRepository;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;

@RequiredArgsConstructor
@QuaranoIntegrationTest
class MailForNewContactCaseEventListenerTests {

	final EmailSendingEvents events;
	final TrackedPersonRepository persons;
	final DepartmentRepository departments;
	final GreenMail greenMail;

	@BeforeEach
	void setUp() throws Exception {
		greenMail.purgeEmailFromAllMailboxes();
	}

	@Test // CORE-61
	void sendMailCorrect() throws MessagingException {

		var trackedCase = trackedCase();

		assertThat(trackedCase.getNewContactCaseMailStatus()).isEqualTo(MailStatus.NOT_SENT);

		events.on(CaseCreated.of(trackedCase));

		// wait for max 5s for 1 email to arrive
		assertThat(greenMail.waitForIncomingEmail(1)).isTrue();

		Message[] messages = greenMail.getReceivedMessages();
		assertThat(messages).hasSize(1);

		Message message = messages[0];

		assertThat(message.getSubject()).isEqualTo("Information vom GA Mannheim");
		assertThat(GreenMailUtil.getBody(message)).startsWith("Sehr geehrte/geehrter Frau/Herr Mueller,")
				.contains("/client/enrollment/landing/contact/");
		assertThat(message.getRecipients(RecipientType.TO)[0].toString())
				.isEqualTo("Tanja Mueller <tanja.mueller@testtest.de>");
		assertThat(message.getFrom()[0].toString()).isEqualTo("GA Mannheim <contact-email@gesundheitsamt.de>");
		assertThat(trackedCase.getNewContactCaseMailStatus()).isEqualTo(MailStatus.SENT);
	}

	@Test
	void noMailAddress() throws MessagingException {

		var trackedCase = trackedCase();
		trackedCase.getTrackedPerson().setEmailAddress(null);

		assertThat(trackedCase.getNewContactCaseMailStatus()).isEqualTo(MailStatus.NOT_SENT);

		events.on(CaseCreated.of(trackedCase));

		// wait for email
		assertThat(greenMail.waitForIncomingEmail(1)).isFalse();

		Message[] messages = greenMail.getReceivedMessages();

		assertThat(messages).hasSize(0);
		assertThat(trackedCase.getNewContactCaseMailStatus()).isEqualTo(MailStatus.CANT_SENT);
	}

	private TrackedCase trackedCase() {

		var person = persons.findById(TrackedPersonDataInitializer.VALID_TRACKED_PERSON1_ID_DEP1).orElseThrow();
		var department = departments.findById(DepartmentDataInitializer.DEPARTMENT_ID_DEP1).orElseThrow();

		return new TrackedCase(person, CaseType.CONTACT, department);
	}
}