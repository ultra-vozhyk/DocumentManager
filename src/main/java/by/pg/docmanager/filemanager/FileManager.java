package by.pg.docmanager.filemanager;

import by.pg.docmanager.exception.FileOperationException;

import java.io.File;

public interface FileManager {
	
	public File saveFile(File fileToSave) throws FileOperationException;

	public File getFile(String fileName) throws FileOperationException;
	
	public boolean removeFile(String fileName);
}
