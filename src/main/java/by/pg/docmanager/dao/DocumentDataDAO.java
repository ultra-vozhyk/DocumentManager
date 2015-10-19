package by.pg.docmanager.dao;

import java.util.List;

import by.pg.docmanager.exception.DocumentDataException;
import by.pg.docmanager.model.DocumentData;

public interface DocumentDataDAO {
	
	 public void create(DocumentData data);
	 
	 public void update(DocumentData data);
     
	 public void delete(DocumentData data);
	 
	 public List<DocumentData> findAll();
	 
	 public void assignUniqueToken(String token, String fileName) throws DocumentDataException;
	 
	 public DocumentData findByToken(String token);
     
	 public DocumentData findByFileName(String fileName);
	 
}
