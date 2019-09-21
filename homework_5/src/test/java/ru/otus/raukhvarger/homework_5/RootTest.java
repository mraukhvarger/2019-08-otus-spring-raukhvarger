package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
public class RootTest {

	@Autowired
	private Shell shell;

	protected String runCommand(String command) {
		return shell.evaluate(() -> command).toString();
	}

	protected Integer getIdFromResult(String text) {
		String regex = "^.*\\((\\d*)\\).*$";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);
		matcher.find();
		return Integer.parseInt(matcher.group(1));
	}

}
