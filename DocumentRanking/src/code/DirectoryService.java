package code;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;

public class DirectoryService {

	private final WatchService watcher;
	private Path dir;
	private HashMap<String, String> optionsValues;
	private TfIdf tfIdf;
	
	/**
	 * Creates a service that listens for new plain text files in a given directory (which is 
	 * included in the HashMap 'optionsValues')
	 * @see http://www.java2s.com/example/java/file-path-io/monitor-a-specified-directory-for-new-files-arrived.html
	 * @see http://tutorials.jenkov.com/java-nio/path.html
	 * @param optionsValues - the HashMap with the options to create the service
	 */
	DirectoryService(HashMap<String, String> optionsValues) throws Exception {
		this.optionsValues = optionsValues;
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dir = Paths.get(this.optionsValues.get(Parser.OPTION_D));
        this.dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
        this.tfIdf = new TfIdf(optionsValues);
	}
	
	/**
	 * Listens for new plain text files in the directory 'dir'. If a new file is found the tf-idf process is
	 * started using the info provided in 'this.optionsValues'
	 */
	public void listen() throws Exception {
        for (;;) {
        	// Prepare 'key' and watch for changes
            WatchKey key;
            key = watcher.take();
            // Process events (new files in directory 'dir')
            for (WatchEvent<?> event : key.pollEvents()) {
                Kind<?> kind = event.kind();
                if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                	WatchEvent<Path> ev = (WatchEvent<Path>) event;
                	Path filename = ev.context();
                	Path child = dir.resolve(filename);
                	if (!Files.probeContentType(child).equals("text/plain")) {
                		System.err.format("New file " + filename + " is not a plain text file.%n");
                	} else {
                		tfIdf.addNewFile(filename);
                	}
                }
            }
            // Prepare 'key' again for new changes
            boolean valid = key.reset();
            if (!valid) {
        		System.err.format("Invalid key, can not continue listening to directory " + this.dir + ".%n");
                break;
            }
        }
	}
}
