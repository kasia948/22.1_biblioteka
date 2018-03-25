public class LibraryUpdate {
    public static void main(String[] args) {

        Book book = new Book(2L, "nowy tutuł", "nowy autor", 554, 444);
        BookDao bookDao = new BookDao();
        bookDao.updateRecord(book);
        bookDao.close();
    }
}
