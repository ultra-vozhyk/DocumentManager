package by.pg.docmanager.service;

import java.io.File;

import by.pg.docmanager.exception.DocumentDataException;
import by.pg.docmanager.exception.FileOperationException;
import by.pg.docmanager.exception.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.pg.docmanager.dao.DocumentDataDAOImpl;
import by.pg.docmanager.filemanager.FileManager;
import by.pg.docmanager.model.DocumentData;

@Service
public class DocumentService {
	@Autowired
	private DocumentDataDAOImpl documentDAO;
	
	@Autowired
	private FileManager fileManager;
	
	public void saveFile(File file, DocumentData doc) throws NullArgumentException, FileOperationException {
		if(file == null || doc == null) {
			new NullArgumentException("Null reference passed as argument!");
		}
		
		File savedFile = fileManager.saveFile(file);

		doc.setFileName(savedFile.getName());

		documentDAO.create(doc);

		//try {

		//} catch (DocumentDataException e) {
		//	System.out.println(e.getMessage());
		//	fileManager.removeFile(savedFile.getName());
		//}
	}
		
	public File getFileByName(String fileName) throws FileOperationException, DocumentDataException {
		DocumentData obj = documentDAO.findByFileName(fileName);

		if(obj == null) throw new DocumentDataException("Object with specified name doesn't exists!");

		return fileManager.getFile(obj.getFileName());
	}
		
	public void assignTokenToFile(String token, String fileName) throws DocumentDataException {
		documentDAO.assignUniqueToken(token, fileName);
	}
	
	public File getFileByToken(String token) throws FileOperationException, DocumentDataException {
		DocumentData obj = documentDAO.findByToken(token);

		if(obj == null) throw new DocumentDataException("Object with specified name doesn't exists!");
		
		return fileManager.getFile(obj.getFileName());
	}
		
}
 