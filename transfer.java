
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;


public class transfer {
	
	//scanner to read user inputs
	static Scanner sc = new Scanner(System.in);
	
	//main method
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
			
			//takes all files of specified type and makes array out of them
			File[] transferFiles = getNewTextFiles2(subfolders[i]);
			
			//forloop to transfer the found files to new location
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
		
		//returns all the .jar files in current subdirectory, change ".jar" to whatever filetypes needed
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
		
		//converts source directory to a File type
		File src1 = new File(src);
		//gets file name from the path
		String fileName = src1.getName();
		//destination will be destination directory + filename
		dest = dest+"//"+fileName;
		
		//CopyOption created to overwrite exsisting file in destination folder if it exists
		CopyOption[] options = new CopyOption[] {
				StandardCopyOption.REPLACE_EXISTING};
		
		//Moves file from source to destination and replaces if it already is in destination
		Path temp = Files.move(Paths.get(src), Paths.get(dest), options);

		//if file move is a success or not
			 if(temp != null)
			 {
			    System.out.println("File moved successfully");
			 }
			 else
			 {
			    System.out.println("Failed to move the file");
			 }
	}
	
}

