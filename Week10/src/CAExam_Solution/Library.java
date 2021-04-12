package CAExam_Solution;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> libraryCollection = new ArrayList<>();

    public static void main(String[] args) {
        Library library = new Library();
        Book book = new Book("Joe", "Murphy", "Ab11228", 2015);
        library.libraryCollection.add(book);
        Member member1 = new Member(library);
        Member member2 = new Member(library);
        member1.start();
        member2.start();
    }

    public synchronized int findBookIndex(String isbn){
        boolean found = false;
        int index = 0;

        while(index < libraryCollection.size() && !found){
            Book book = libraryCollection.get(index);

            if(book.getIsbn().equals(isbn)){
                return index;
            }
            index ++;
        }
        return -1;
    }

    public synchronized Book loanBook(int bookIndex){
        if(bookIndex < 0 || bookIndex > libraryCollection.size()){
            return null;
        }

        Book book = libraryCollection.get(bookIndex);
        System.out.println(book.getIsbn() + " on loan");
        libraryCollection.remove(bookIndex);
        return book;

    }

    public void returnBook(Book book){
        libraryCollection.add(book);
        System.out.println(book.getIsbn() + " returned");
    }
}

class Member extends Thread{

    Library library;
    String isbn = "Ab11228";

    public Member(Library library){
        this.library = library;
    }

    public void run(){
        int bookIndex = library.findBookIndex(isbn);

        if(bookIndex != -1){
            Book loandBook = library.loanBook(bookIndex);
            try{
                this.currentThread().sleep(2000);
            }catch(InterruptedException e){}
            library.returnBook(loandBook);
        }
    }

}
