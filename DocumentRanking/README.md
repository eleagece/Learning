# **Document Ranking with Tf/Idf**

## Keywords
* Java, Algorithms, Tf/Idf, Boyer-Moore, CLI, Daemon.

## Dev notes
* [**Tf/Idf (term frequency / inverse document frequency)**](https://en.wikipedia.org/wiki/Tf%E2%80%93idf) is an statistic that reflects the importance of a term T in a document D (or the relevance of a document for a searched term) relative to a document set S.
* Tf/Idf can be extended to a set of terms TT adding the Tf/Idf for each term.
* Assume that we have a directory D containing a document set S, with one file per document. Documents will be added to that directory by external agents, but they will never be removed or overwritten. We are given a set of terms TT, and asked to compute the Tf/Idf of TT for each document in D, and report the N top documents sorted by relevance. The program will run as a daemon/service that is watching for new documents, and dynamically updates the computed Tf/Idf for each document and the inferred ranking.
* The program will run with the parameters:
    * The directory D where the documents will be written.
    * The terms TT to be analyzed.
    * The count N of top results to show.
    * The period P to report the top N. For example: `./tfIdf -d dir -n 5 -p 300 -t "password try again"`
* Result examples:
    * doc1.txt 0.78, doc73.txt 0.76...
* [**Boyer-Moore algorithm**](https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm) is important for efficient execution.
* Developed purely in [**Java**](https://www.java.com/es/).

## Setup
* **Code and JAR**:
    * From your git client prompt, go to a folder of your choice and clone the repository: `git clone https://github.com/eleagece/Learning.git`. 
    * The project is inside `DocumentRanking`.
    * The JAR needs to be generated from the code. If you use Eclipse follow these steps to create the JAR file:
        * Right click in the project and then "Export...".
	    * Choose "Java/Runnable JAR file" in the next window and then click on "Next".
        * In "Launch configuration" check for `src/code/Principal/` class and click on "Finish".

## Usage
* **cmd**:
    * From your `cmd` and where the jar (named `tfIdf.jar`) file is located, the CLI application can be used like this: `java -jar tfIdf -d "c:/folderThatDaemonListensTo" -n 3 -p 300 -t "fox a dog water"`
    * `-p` functionality not developed yet but must be used in the order.
* **Tests**:
    * There are no tests but the application can be used with the documents in the folder `files/`.
