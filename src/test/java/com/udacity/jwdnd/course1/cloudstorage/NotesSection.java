package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesSection {

    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTabField;

    @FindBy(css = "#add-note")
    private WebElement addNote;

    @FindBy(css = "#note-title")
    private WebElement noteTitleField;

    @FindBy(css = "#note-description")
    private WebElement noteDescriptionField;

    @FindBy(css = ".note-title-displayed")
    private WebElement displayedNoteTitle;

    @FindBy(id = "save-note")
    private WebElement saveNote;

    @FindBy(id = "edit-note")
    private WebElement editNote;

    @FindBy(id = "delete-note")
    private WebElement deleteNote;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    public NotesSection(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 1000);
        js = (JavascriptExecutor) webDriver;
    }

    public void openNoteTabJS() {
        js.executeScript("arguments[0].click();", notesTabField);
    }

    public void openModalJS() {
        js.executeScript("arguments[0].click();", addNote);
    }

    public void createNoteJS(String title, String description) {
        js.executeScript("arguments[0].value='" + title + "';", noteTitleField);
        js.executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
    }

    public void saveNoteJS() {
        js.executeScript("arguments[0].click();", saveNote);
    }

    public void editNoteJS() {
        js.executeScript("arguments[0].click();", editNote);
    }

    public void deleteNoteJS() {
        js.executeScript("arguments[0].click();", deleteNote);
    }


//    -------------------------


    public String getCreatedNoteTitle() {
        return this.displayedNoteTitle.getText();
    }

}
