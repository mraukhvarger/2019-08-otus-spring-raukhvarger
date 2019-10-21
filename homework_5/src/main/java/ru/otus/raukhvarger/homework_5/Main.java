package ru.otus.raukhvarger.homework_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.raukhvarger.homework_5.dao.AuthorRepository;
import ru.otus.raukhvarger.homework_5.dao.BookRepository;
import ru.otus.raukhvarger.homework_5.dao.GenreRepository;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
//		System.setProperty(ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED, "false");
//		System.setProperty(InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED, "false");
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	AuthorRepository a;
	@Autowired
	BookRepository b;
	@Autowired
	GenreRepository g;

	@PostConstruct
	void run() {
		a.findAll();
		a.findAllByFirstNameLike("Иван");
		a.findOne(2L);
	}

}
