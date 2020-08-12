package com.udacity.jwdnd.course1.cloudstorage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private String username;
	private String password;
	private JavascriptExecutor js;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	public void setUp() {
		username = "Test";
		password = "testPassword";
		driver.get("http://localhost:" + port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Russell", "Westbrook", username, password);
		driver.get("http://localhost:" + port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@Test
	 public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	 }

	 @Test
	 public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	 }

	 @Test
	 public void unauthenticatedRedirectTestHomePage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	 }

	 @Test
	 public void unauthenticatedRedirectTestRandomPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/random");
		Assertions.assertEquals("Login", driver.getTitle());
	 }

	@Test
	public void createNoteTest() throws InterruptedException {
		Boolean createdNote = false;
		setUp();
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		createdNote = true;
		Assertions.assertEquals(true, createdNote);
	}

	@Test
	public void editNoteTest() throws InterruptedException {
		Boolean editedNote = false;
		setUp();
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		notesSection.editNoteJS();
		notesSection.createNoteJS("edited JS", "edited test Description");
		notesSection.saveNoteJS();
		editedNote = true;
		Assertions.assertEquals(true, editedNote);
	}

	@Test
	public void deleteNoteTest() throws InterruptedException {
		Boolean deletedNote = false;
		setUp();
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		notesSection.deleteNoteJS();
		notesSection.openNoteTabJS();
		deletedNote = true;
		Assertions.assertEquals(true, deletedNote);
	}

	@Test
	public void createCredentialTest() throws InterruptedException {
		Boolean createdCredential = false;
		setUp();
		CredentialsSection credentialsSection = new CredentialsSection(driver);
		credentialsSection.openCredentialTab();
		credentialsSection.openCredentialModal();
		credentialsSection.createCredential("test URL", "testUsername", "testPassword");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
		createdCredential = true;
		Assertions.assertEquals(true, createdCredential);
	}

	@Test
	public void editCredentialTest() throws InterruptedException {
		Boolean editedCredential = false;
		setUp();
		CredentialsSection credentialsSection = new CredentialsSection(driver);
		credentialsSection.openCredentialTab();
		credentialsSection.openCredentialModal();
		credentialsSection.createCredential("test URL", "testUsername", "testPassword");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
		credentialsSection.editCredential();
		credentialsSection.createCredential("test URL edited", "testUsername edited", "testPassword edited");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
		editedCredential = true;
		Assertions.assertEquals(true, editedCredential);
	}

	@Test
	public void deleteCredentialTest() throws InterruptedException {
		Boolean deletedCredential = false;
		setUp();
		CredentialsSection credentialsSection = new CredentialsSection(driver);
		credentialsSection.openCredentialTab();
		credentialsSection.openCredentialModal();
		credentialsSection.createCredential("test URL", "testUsername", "testPassword");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
		credentialsSection.deleteCredential();
		credentialsSection.openCredentialTab();
		deletedCredential = true;
		Assertions.assertEquals(true, deletedCredential);
	}


}
