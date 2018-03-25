public class LibraryRead {

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        System.out.println(bookDao.readRecord(444));
        bookDao.close();
    }
}
