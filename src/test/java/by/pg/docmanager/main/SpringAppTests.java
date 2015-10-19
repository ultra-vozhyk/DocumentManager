package by.pg.docmanager.main;

import by.pg.docmanager.exception.DocumentDataException;
import by.pg.docmanager.exception.FileOperationException;
import by.pg.docmanager.exception.NullArgumentException;
import by.pg.docmanager.model.DocumentData;
import by.pg.docmanager.service.DocumentService;
import by.pg.docmanager.util.UUIDGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class SpringAppTests {
    @Autowired
    private DocumentService docService;
    private String currentToken;
    private DocumentData doc;
    private String fileName = "25hourbook.pdf";
    private String filePath = "D:/25hourbook.pdf";

    public SpringAppTests() {
        doc = new DocumentData();
        //doc.setFileName(fileName);
        doc.setAuthorName("Author");
        doc.setDocumentName("Some document name here");
        doc.setCreationDate(new Date());
    }

    @Test
    public void testSaveFile() {
        File file = new File(filePath);

        try {
            docService.saveFile(file, doc);
        } catch (NullArgumentException e) {
            e.printStackTrace();
        } catch (FileOperationException e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    public void testGetFileByName() {
        try {
            File f = docService.getFileByName(fileName);
            Assert.assertNotNull(f);
        } catch (FileOperationException e) {
            e.printStackTrace();
        } catch (DocumentDataException e) {
            e.printStackTrace();
        }
    }
*/
    @Test
    public void testAssignTokenToFile() {
        currentToken = UUIDGenerator.nextUUID();

        try {
            docService.assignTokenToFile(currentToken, fileName);
        } catch (DocumentDataException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileByToken() {
        try {
            File f = docService.getFileByToken(currentToken);

            Assert.assertNotNull(f);
        } catch (FileOperationException e) {
            e.printStackTrace();
        } catch (DocumentDataException e) {
            e.printStackTrace();
        }
    }
}
