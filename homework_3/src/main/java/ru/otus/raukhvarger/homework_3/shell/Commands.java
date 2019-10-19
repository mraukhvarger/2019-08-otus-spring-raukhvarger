package ru.otus.raukhvarger.homework_3.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.raukhvarger.homework_3.service.LocalizationService;
import ru.otus.raukhvarger.homework_3.service.QuestionService;

@ShellComponent
public class Commands {

    private final QuestionService questionService;
    private final LocalizationService ms;

    private Boolean languageChecked = false;
    private Boolean fullNameChecked = false;
    private Boolean answerChecked = false;

    @Autowired
    public Commands(QuestionService questionService, LocalizationService ms) {
        this.questionService = questionService;
        this.ms = ms;
    }

    @ShellMethod(value = "Select language", key = "lng")
    public void pleaseSelectLanguage() {
        languageChecked = true;
        questionService.askForLanguage();
    }

    @ShellMethod(value = "Input FIO", key = "fio")
    @ShellMethodAvailability("checkLanguage")
    public void pleaseInputFIO() {
        fullNameChecked = true;
        questionService.askForFullName();
    }

    @ShellMethod(value = "Answer questions", key = "answ")
    @ShellMethodAvailability("checkLanguage")
    public void pleaseInputAnswer() {
        answerChecked = true;
        questionService.askForAnswers();
    }

    @ShellMethod(value = "Print result", key = "rslt")
    @ShellMethodAvailability({"checkLanguageAndFIOAndAnswer"})
    public void printResults() {
        fullNameChecked = false;
        answerChecked = false;
        questionService.printResults();
    }

    private Availability checkLanguage() {
        return languageChecked ? Availability.available() : Availability.unavailable(ms.get("shell.language"));
    }

    private Availability checkLanguageAndFIOAndAnswer() {
        if (!languageChecked)
            return Availability.unavailable(ms.get("shell.language"));
        if (!fullNameChecked)
            return Availability.unavailable(ms.get("shell.fio"));
        if (!answerChecked)
            return Availability.unavailable(ms.get("shell.answers"));
        return Availability.available();
    }

}
