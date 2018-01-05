package fileTransfer;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;


public class transfer {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException
    {

		//get source folder

		System.out.println("Source Folder:");
		String source = sc.nextLine();
		
		//get Destination folder
		System.out.println("Destination Folder:");
		String destination = sc.nextLine();
		
		
		//take source string and convert to file obj
		File inp = new File(source);
		
		//all subfolders from source folder
		File[] subfolders = getNewTextFiles(inp);
		
		//takes all the .jar files in subdirectories (level 1) and transfers them
		for(int i=0;i<subfolders.length;i++){
			
			File[] transferFiles = getNewTextFiles2(subfolders[i]);
			
				for(int j=0;j<transferFiles.length;j++){
					move(transferFiles[j].toString(),destination);
				}
		}
    }
	
	/**
	 * 
	 * @param source: the source folder from which you want files
	 * @return array of subdirectories within the folder
	 */
	private static File[] getNewTextFiles(File source) {
		
		//returns all the subdirectories in current directory
	    return source.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	        	 return new File(dir, name).isDirectory();
	        }
	    });	
	}
	
	
	/**
	 * 
	 * @param source subdirectory from which you wnat .jar files
	 * @return array of jar files
	 */
	private static File[] getNewTextFiles2(File source) {
		
		//returns all the .jar files in current subdirectory
	    return source.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	        	return name.toLowerCase().endsWith(".jar");
	        }
	    });	
	}
	
	
	/**
	 * 
	 * @param src source file
	 * @param dest folder
	 * @throws IOException
	 */
	public static void move(String src, String dest) throws IOException{
		
		//moves the file
		File src1 = new File(src);
		String fileName = src1.getName();
		dest = dest+"//"+fileName;
		
		CopyOption[] options = new CopyOption[] {
				StandardCopyOption.REPLACE_EXISTING};
		
		Path temp = Files.move(Paths.get(src), Paths.get(dest), options);
	
		//if file move is a success or not
			 if(temp != null)
			 {
			    System.out.println("File renamed and moved successfully");
			 }
			 else
			 {
			    System.out.println("Failed to move the file");
			 }
	}
	
}

