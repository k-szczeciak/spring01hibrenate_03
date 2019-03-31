package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

@Controller
public class BookController {
    @Autowired
    private BookDao bookDao;
    @RequestMapping(path = "/hello", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String hello(){
        Book book = new Book(); book.setTitle("Thinking in Java"); book.setAuthor("Bruce Eckel");
        bookDao.saveBook(book);
        return "Id dodanej książki to:" + book.getId();
    }


    @RequestMapping(path = "/addbook/{author}/{title}")
    @ResponseBody
    private String requestParamsUri(@PathVariable("author") String author,
                                    @PathVariable("title") String title) {

        Book book = new Book(title, author);

        bookDao.saveBook(book);
        return "zapisana " + bookDao.findById(book.getId());

    }

}