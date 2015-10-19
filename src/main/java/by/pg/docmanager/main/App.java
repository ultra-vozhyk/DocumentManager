package by.pg.docmanager.main;

import by.pg.docmanager.exception.FileOperationException;
import by.pg.docmanager.exception.NullArgumentException;
import by.pg.docmanager.service.DocumentService;
import by.pg.docmanager.model.DocumentData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DocumentService docService = context.getBean(DocumentService.class);
    }
}
