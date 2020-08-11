package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

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
	public void createNoteTest() throws InterruptedException {
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		Thread.sleep(50000);
	}

	@Test
	public void editNoteTest() throws InterruptedException {
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		notesSection.editNoteJS();
		notesSection.createNoteJS("edited JS", "edited test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		Thread.sleep(50000);
	}

	@Test
	public void deleteNoteTest() throws InterruptedException {
		NotesSection notesSection = new NotesSection(driver);
		notesSection.openNoteTabJS();
		notesSection.openModalJS();
		notesSection.createNoteJS("test JS", "test Description");
		notesSection.saveNoteJS();
		notesSection.openNoteTabJS();
		notesSection.deleteNoteJS();
		notesSection.openNoteTabJS();
	}

	@Test
	public void createCredentialTest() throws InterruptedException {
		CredentialsSection credentialsSection = new CredentialsSection(driver);
		credentialsSection.openCredentialTab();
		credentialsSection.openCredentialModal();
		credentialsSection.createCredential("test URL", "testUsername", "testPassword");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
	}

	@Test
	public void editCredentialTest() throws InterruptedException {
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
		Thread.sleep(50000);
	}

	@Test
	public void deleteCredentialTest() throws InterruptedException {
		CredentialsSection credentialsSection = new CredentialsSection(driver);
		credentialsSection.openCredentialTab();
		credentialsSection.openCredentialModal();
		credentialsSection.createCredential("test URL", "testUsername", "testPassword");
		credentialsSection.saveCredential();
		credentialsSection.openCredentialTab();
		credentialsSection.deleteCredential();
		credentialsSection.openCredentialTab();
	}



}
