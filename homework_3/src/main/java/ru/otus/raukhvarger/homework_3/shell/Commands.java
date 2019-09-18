package ru.otus.raukhvarger.homework_3.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.raukhvarger.homework_3.config.MessageSource;
import ru.otus.raukhvarger.homework_3.service.QuestionService;

@ShellComponent
public class Commands {

    private final QuestionService questionService;
    private final MessageSource ms;

    private Boolean checkLanguage = false;
    private Boolean checkFIO = false;
    private Boolean checkAnswer = false;

    @Autowired
    public Commands(QuestionService questionService, MessageSource ms) {
        this.questionService = questionService;
        this.ms = ms;
    }

    @ShellMethod(value = "Select language", key = "lng")
    public void pleaseSelectLanguage() {
        checkLanguage = true;
        questionService.pleaseSelectLanguage();
    }

    @ShellMethod(value = "Input FIO", key = "fio")
    @ShellMethodAvailability("checkLanguage")
    public void pleaseInputFIO() {
        checkFIO = true;
        questionService.pleaseInputFIO();
    }

    @ShellMethod(value = "Answer questions", key = "answ")
    @ShellMethodAvailability("checkLanguage")
    public void pleaseInputAnswer() {
        checkAnswer = true;
        questionService.pleaseInputAnswer();
    }

    @ShellMethod(value = "Print result", key = "rslt")
    @ShellMethodAvailability({"checkLanguageAndFIOAndAnswer"})
    public void printResults() {
        checkFIO = false;
        checkAnswer = false;
        questionService.printResults();
    }

    private Availability checkLanguage() {
        return checkLanguage ? Availability.available() : Availability.unavailable(ms.get("shell.language"));
    }

    private Availability checkLanguageAndFIOAndAnswer() {
        if (!checkLanguage)
            return Availability.unavailable(ms.get("shell.language"));
        if (!checkFIO)
            return Availability.unavailable(ms.get("shell.fio"));
        if (!checkAnswer)
            return Availability.unavailable(ms.get("shell.answers"));
        return Availability.available();
    }

}
