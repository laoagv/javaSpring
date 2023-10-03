package aop;

import org.springframework.stereotype.Component;

@Component("uniLibraryBean")
public class UniLibrary extends AbstractLibrary {
    public void getBook(){

        System.out.println("Мы берем книгу из UniLabrary");
    }
    public void getBook(Book book){
        System.out.println("Мы берем книгу из UniLabrary " + book.getName());
    }
    public String returnBook(){
        System.out.println("Мы возвращаем книгу в UniLibrary");
        return "Идиот";
    }
    public void returnMagazine(){
        System.out.println("Мы возвращаем журнал в UniLibrary");
    }
    public void getMagazine(){
        System.out.println("Мы берем журнал из UniLibrary");
    }
    public void addBook(){
        System.out.println("Мы добавляем книгу в UniLibrary");
    }
    public void addMagazine(){
        System.out.println("Мы добавляем журнал в UniLibrary");
    }
}
