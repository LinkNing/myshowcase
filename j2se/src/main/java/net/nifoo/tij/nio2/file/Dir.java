package net.nifoo.tij.nio2.file;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dir {

	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();

		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path dir : dirs) {
			System.err.println(dir);
		}

		FileSystems.getDefault().getFileStores();

		Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
		for (FileStore s : stores) {
			System.err.format("%s - %d \n", s, s.getTotalSpace());
		}

		Path tempDir = Files.createTempDirectory("nifoo");
		System.out.println(tempDir);
		System.out.println(Files.createTempDirectory("nifoo"));
		
		Path dir = Paths.get("C:\\AMD");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		        System.out.println(file.getFileName());
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
	}
}
