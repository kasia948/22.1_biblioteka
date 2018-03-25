public class LibrarySave {

    public static void main(String[] args) {

        Book book=new Book(14L ,"ggg", "rrr", 122, 22);
        BookDao bookDao= new BookDao();
        bookDao.save(book);
        bookDao.close();
    }
}
