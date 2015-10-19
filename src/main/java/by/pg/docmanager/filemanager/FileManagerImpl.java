package by.pg.docmanager.filemanager;

import by.pg.docmanager.exception.FileOperationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileManagerImpl implements FileManager {
	private final String storagePath;
	
	public FileManagerImpl(String storagePath) {
		this.storagePath = storagePath;
	}
	
	public File saveFile(File fileToSave) throws FileOperationException {

		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		String filePath = storagePath + fileToSave.getName();

		File newFile = new File(filePath);

		if(newFile.exists()) throw new FileOperationException("File with specified name already exists in storage!");

		newFile.getParentFile().mkdirs(); 
		
		try {
			inputStream = new FileInputStream(fileToSave);
			outputStream = new FileOutputStream(newFile);
			
			byte[] buff = new byte[1024];
	        int length;
	        
	        while ((length = inputStream.read(buff)) > 0) {
	            outputStream.write(buff, 0, length);
	        }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileOperationException("File cannot be found!");
		} catch(IOException e) {
			e.printStackTrace();
			throw new FileOperationException("Failed to copy file!!");
		}
		
		return newFile;
	}

	public File getFile(String name) throws FileOperationException {
		File f = new File(storagePath + name);
		
		if(!f.exists()) throw new FileOperationException("File not exists in storage!");
		
		return f;
	}


	public boolean removeFile(String name) {
		File f = new File(storagePath + name);
				
		return f.delete();
	}
	
}
