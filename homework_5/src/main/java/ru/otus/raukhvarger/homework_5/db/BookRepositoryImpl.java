package ru.otus.raukhvarger.homework_5.db;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.utils.EntityConverter;
import ru.otus.raukhvarger.homework_5.utils.mapper.BookRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final NamedParameterJdbcTemplate template;

    public BookRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Book getBookById(Integer bookId) {
        String query = "select b.bookid, b.bookname, b.authorid, b.genreid, a.authorname, g.genrename " +
                "from table_book b " +
                "left join table_author a on b.authorid = a.authorid " +
                "left join table_genre g on b.genreid = g.genreid " +
                "where bookid = :bookId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookId", bookId);
        List<Book> bookEntities = template.query(query, queryParams, new BookRowMapper());
        if (bookEntities.size() > 0) return bookEntities.get(0);
        else return null;
    }

    @Override
    public List<Book> getBooksByName(String bookName) {
        String query = "select b.bookid, b.bookname, b.authorid, b.genreid, a.authorname, g.genrename " +
                "from table_book b " +
                "left join table_author a on b.authorid = a.authorid " +
                "left join table_genre g on b.genreid = g.genreid " +
                "where upper(b.bookname) = :bookName";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookName", bookName.toUpperCase());
        return template.query(query, queryParams, new BookRowMapper());
    }

    @Override
    public void insertBook(Book book) {
        String query = "insert into table_book(bookname, authorid, genreid) values (:bookName, :authorId, :genreId)";
        template.update(query, EntityConverter.convertBookToMap(book));
    }

    @Override
    public void deleteBookById(Integer bookId) {
        String query = "delete from table_book where bookid = :bookId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookId", bookId);
        template.update(query, queryParams);
    }

    @Override
    public List<Book> getAllBooks() {
        String query = "select b.bookid, b.bookname, b.authorid, b.genreid, a.authorname, g.genrename " +
                "from table_book b " +
                "left join table_author a on b.authorid = a.authorid " +
                "left join table_genre g on b.genreid = g.genreid";
        return template.query(query, new BookRowMapper());
    }

    @Override
    public void updateBookAuthor(Integer bookId, Integer authorId) {
        String query = "update table_book set authorid = :authorId where bookid = :bookId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookId", bookId);
        queryParams.put("authorId", authorId);
        template.update(query, queryParams);
    }

    @Override
    public void updateBookGenre(Integer bookId, Integer genreId) {
        String query = "update table_book set genreid = :genreId where bookid = :bookId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookId", bookId);
        queryParams.put("genreId", genreId);
        template.update(query, queryParams);
    }

    @Override
    public void updateBookName(Integer bookId, String bookName) {
        String query = "update table_book set bookname = :bookName where bookid = :bookId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("bookId", bookId);
        queryParams.put("bookName", bookName);
        template.update(query, queryParams);
    }
}
