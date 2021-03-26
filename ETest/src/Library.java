import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Library {
    List<Book> list = new ArrayList<>();

    public int findBookIndex(String isbn){
        int i = -1;
        while(i < list.size()){
            i++;
            if(list.get(i).getIsbn().equals(isbn)){
                return i;
            }
            }
        return -1; //doesnt get here unless it exists list
    }

    public Book loanBook(int index){
        Book book;
        if(index < list.size()) {
            book = list.get(index);
            list.remove(index);
        }else{
            return null;
        }
        return book;
    }

    public void returnBook(Book book){
        if(book != null) {
            list.add(book);
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        Book book = new Book("Darren", "ODonnell","Ab11228",2010);
        library.list.add(book);

        Member member = new Member(library);
        Member member2 = new Member(library);

        member.start();
        member2.start();


    }
}

class Member extends Thread{
    Object lock = new Object();
    Library library;
    String isbn = "Ab11228";
    Book book;

    public Member(Library library){
        this.library = library;
    }

    public void run(){

        try {
            takeFromLibrary();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            addToLibrary();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public synchronized void takeFromLibrary() throws InterruptedException{

        while(true){

            synchronized (lock) {

                //While list is empty, wait for addToLibrary to add to list
                while(library.list.size() != 1){
                    System.out.println("In take from library");
                    lock.notify();
                    lock.wait();

                }
                // book = to the book found with isbn
                book = library.loanBook(library.findBookIndex(isbn));
                System.out.println("Book on loan: " + book.toString());
                System.out.print("List size is: " + library.list.size());
                lock.notify();

                Thread.sleep(2000);
            }
        }
    }
    public synchronized void addToLibrary() throws InterruptedException{

        while(true){

            synchronized (lock) {
                //While list has a book, wait until take from library removes
                while(library.list.size() != 0) {
                    System.out.println("In add to library");
                    lock.notify();
                    lock.wait();
                }

                    library.returnBook(book);
                    System.out.println("Book on loan: " + book.toString());
                    lock.notify();
            }
        }
    }
}
