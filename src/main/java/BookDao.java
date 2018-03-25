import java.sql.*;

public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Book book) {
        final String sql = "insert into books(id, title, author, year, isbn) values(?,?,?,?,?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, book.getId());
            prepStmt.setString(2, book.getTitle());
            prepStmt.setString(3, book.getAuthor());
            prepStmt.setInt(4, book.getYear());
            prepStmt.setInt(5, book.getIsbn());
            prepStmt.executeUpdate();/// dokończyć  execute.update
        } catch (SQLException e) {
            System.out.println("Nie można zapisać rekordu");
            e.printStackTrace();
        }
    }

    public void updateRecord(Book book) {
        final String sql = "UPDATE books SET title=?, author=?, year=?, isbn=? WHERE id=?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setInt(3, book.getYear());
            prepStmt.setInt(4, book.getIsbn());
            prepStmt.setLong(5, book.getId());
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nie można zaktualizować rekordu");
            e.printStackTrace();
        }
    }

    public void deleteRecord(long id) {
        final String sql = "delete from books where id=?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Nie można usunąć");
        }
    }

    public Book readRecord(int isbn) {
        final String sql = "select id, title, author, year, isbn from books where isbn=?";
        try {
            PreparedStatement prepStat = connection.prepareStatement(sql);
            prepStat.setLong(1, isbn);
            ResultSet result = prepStat.executeQuery();
            if (result.next()) {
                Book book = new Book();
                book.setId(result.getLong("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getInt("isbn"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Nie mozna znaleźć rekordu");
        }
        return null;
    }
}