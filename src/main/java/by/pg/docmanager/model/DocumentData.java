package by.pg.docmanager.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="document_data")
public class DocumentData {
	
	@Id
	private String documentId;
	

	private String documentName;
	private String authorName;
	private String uniqueToken;
	private String fileName;
	private Date creationDate;
	
	public DocumentData() {
		
	}
	
	public DocumentData(String documentId, String documentName, String authorName, Date creationDate) {
		this.documentId = documentId;
		this.documentName = documentName;
		this.authorName = authorName;
		this.creationDate = creationDate;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String id) {
		this.documentId = id;
	}
	
	public String getDocumentName() {
		return documentName;
	}
	
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
		
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUniqueToken() {
		return uniqueToken;
	}

	public void setUniqueToken(String uniqueToken) {
		this.uniqueToken = uniqueToken;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
