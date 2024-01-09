package com.example.bookshop;

import com.example.bookshop.dao.AuthorDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.GenreDao;
import com.example.bookshop.dao.PublisherDao;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Genre;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.entity.util.IsbnGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;
    @Bean
    @Transactional // to have same transaction
    public ApplicationRunner runner(){
        return r ->{
          Author author1 = new Author("Charles Dickens","charles123@gmail.com");
          Author author2 = new Author("Thomas Hardy","thomashardy@gmail.com");

          Publisher publisher1 = new Publisher("New Era","newera@gmail.com");
          Publisher publisher2 = new Publisher("Sun","sun123@gmail.com");

          Genre genre1 = new Genre();
          genre1.setGenreName("Tragedy");
          Genre genre2 = new Genre();
          genre2.setGenreName("Adventure");

          Book book1 = new Book
                  (1,
                          IsbnGenerator.generate(),
                          "Oliver Twist",
                          "Excellent",
                          50.3,20,
                          "https://source.unsplash.com/400x300/?flower");
            Book book2 = new Book
                    (2,
                            IsbnGenerator.generate(),
                            "Great Expectations",
                            "Good Choice",
                            25.3,23,"https://source.unsplash.com/400x300/?nature");
            Book book3 = new Book
                    (3,
                            IsbnGenerator.generate(),
                            "Bleak House",
                            "Nice",
                            20.3,20,"https://source.unsplash.com/400x300/?ocean");
            Book book4 = new Book
                    (4,
                            IsbnGenerator.generate(),
                            "Under The Greenwood Tree",
                            "Nice",
                            20.3,20,"https://source.unsplash.com/400x300/?mountain");
            Book book5 = new Book
                    (5,
                            IsbnGenerator.generate(),
                            "Return of The Native",
                            "Excellent",
                            19.3,6,"https://source.unsplash.com/400x300/?girl");
            Book book6 = new Book
                    (6,
                            IsbnGenerator.generate(),
                            "Far From The Maddening Crowd",
                            "Excellent",
                            19.3,6,"https://source.unsplash.com/400x300/?ocean");
            //mapping
            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);

            author2.addBook(book4);
            author2.addBook(book5);
            author2.addBook(book6);

            Publisher pub1 = publisherDao.save(publisher1);

            pub1.addBook(book1);
            pub1.addBook(book2);
            pub1.addBook(book3);

            Publisher pub2 = publisherDao.save(publisher2);

            pub2.addBook(book4);
            pub2.addBook(book5);
            pub2.addBook(book6);

            Genre g1 =genreDao.save(genre1);
            Genre g2 =genreDao.save(genre2);



            book1.addGenre(g1);
            book2.addGenre(g1);
            book3.addGenre(g1);

            book4.addGenre(g2);
            book5.addGenre(g2);
            book6.addGenre(g2);

//            bookDao.save(book1);
//            bookDao.save(book2);
//            bookDao.save(book3);
//            bookDao.save(book4);
//            bookDao.save(book5);
//            bookDao.save(book6);

            authorDao.save(author1);
            authorDao.save(author2);

//            genreDao.save(genre1);
//            genreDao.save(genre2);

        };


    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
