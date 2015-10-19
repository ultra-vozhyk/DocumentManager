package by.pg.docmanager.dao;

import java.util.List;

import by.pg.docmanager.exception.DocumentDataException;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import by.pg.docmanager.model.DocumentData;


@Repository
public class DocumentDataDAOImpl implements DocumentDataDAO {
	@Autowired
	private MongoOperations operations;

	public void create(DocumentData data) {
		operations.insert(data);
	}

	public void update(DocumentData data) {
		Query q = Query.query(Criteria.where("documentId").is(data.getDocumentId()));

		Update up = new Update();
		up.set("documentName", data.getDocumentName());
		up.set("authorName", data.getAuthorName());
		up.set("uniqueToken", data.getUniqueToken());
		up.set("fileName", data.getFileName());
		up.set("creationDate", data.getCreationDate());

		operations.updateFirst(q, up, DocumentData.class);
	}

	public void delete(DocumentData data) {
		operations.remove(data);
	}

	public void assignUniqueToken(String token, String fileName) throws DocumentDataException {
		Query q = Query.query(Criteria.where("fileName").is(fileName));

		if(!operations.exists(Query.query(Criteria.where("uniqueToken").is(token)), DocumentData.class)) {
			operations.updateFirst(q, Update.update("uniqueToken", token), DocumentData.class);
		} else {
			throw new DocumentDataException("Token is not unique");
		}
	}

	public DocumentData findByToken(String token) {
		Query q = Query.query(Criteria.where("uniqueToken").is(token));

		return operations.findOne(q, DocumentData.class);
	}

	public DocumentData findByFileName(String fileName) {
		Query q = Query.query(Criteria.where("fileName").is(fileName));

		return operations.findOne(q, DocumentData.class);
	}

	public List<DocumentData> findAll() {
		return operations.findAll(DocumentData.class);
	}
}
