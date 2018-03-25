public class LibraryDelete {

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        bookDao.deleteRecord(14L);
        bookDao.close();
    }
}
