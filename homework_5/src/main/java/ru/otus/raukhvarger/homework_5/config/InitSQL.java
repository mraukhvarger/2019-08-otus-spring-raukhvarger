package ru.otus.raukhvarger.homework_5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class InitSQL {

    private final DataSource dataSource;

    @Autowired
    public InitSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    void init() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(
                new ClassPathResource("sql/createBook.sql"),
                new ClassPathResource("sql/createAuthor.sql"),
                new ClassPathResource("sql/createGenre.sql"),
                new ClassPathResource("sql/createAuthorAndBook.sql")
        );
        databasePopulator.execute(dataSource);
    }

}
