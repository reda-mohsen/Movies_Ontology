package MoviePack;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;

import java.util.Scanner;

public class Jena4 {
    public static void main(String[] args) {
        // Load the ontology
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        FileManager fileManager = FileManager.get();
        String owlFile = "data/Movies.owl"; // Replace with the actual filename
        model.read(fileManager.open(owlFile), null);

        // Read the title of a movie (replace with your desired movie title)
//        String movieTitle = "Joker";
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the movie title
        System.out.print("Enter the movie title: ");
        String movieTitle = scanner.nextLine();
        
        // Find the movie with the given title
        OntClass movieClass = model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Movie");
        ExtendedIterator<? extends OntResource> movieIterator = movieClass.listInstances();
        OntResource targetMovie = null;
        while (movieIterator.hasNext()) {
            OntResource movieResource = movieIterator.next();
            Property titleProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/title");
            RDFNode titleNode = movieResource.getPropertyValue(titleProperty);
            if (titleNode != null && titleNode.isLiteral() && titleNode.asLiteral().getString().equals(movieTitle)) {
                targetMovie = movieResource;
                break;
            }
        }

        // Check if the movie exists
        if (targetMovie == null) {
            System.out.println("Error: Movie not found");
        } else {
        	// Display the Movie Title
        	System.out.println("Movie: " + movieTitle);
        	
        	/////////////////////////////////
        	// Display the movie details //
        	/////////////////////////////////
        	
        	// Display the Year of Release
            Property yearProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/year");
            RDFNode yearNode = targetMovie.getPropertyValue(yearProperty);
            if (yearNode != null && yearNode.isLiteral()) {
                int year = yearNode.asLiteral().getInt();
                System.out.println("Year: " + year);
            }

            // Display the Country
            Property countryProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/country");
            StmtIterator countryIterator = targetMovie.listProperties(countryProperty);
            while (countryIterator.hasNext()) {
                Statement countryStatement = countryIterator.next();
                RDFNode countryNode = countryStatement.getObject();
                if (countryNode != null && countryNode.isLiteral()) {
                    String country = countryNode.asLiteral().getString();
                    System.out.println("Country: " + country);
                }
            }
            
            // Display the Genre
            Property genreProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/HasGenre");
            StmtIterator genreIterator = targetMovie.listProperties(genreProperty);
            while (genreIterator.hasNext()) {
                Statement genreStatement = genreIterator.next();
                RDFNode genreNode = genreStatement.getObject();
                if (genreNode != null && genreNode.isResource()) {
                    Resource genreResource = genreNode.asResource();
                    Property genreNameProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/genre");
                    StmtIterator genreGenreIterator = genreResource.listProperties(genreNameProperty);
                    while (genreGenreIterator.hasNext()) {
                        Statement genreGenreStatement = genreGenreIterator.next();
                        RDFNode genreGenreNode = genreGenreStatement.getObject();
                        if (genreGenreNode != null && genreGenreNode.isLiteral()) {
                            String genre = genreGenreNode.asLiteral().getString();
                            System.out.println("Genre: " + genre);
                        }
                    }
                }
            }
            
            // Display the Actor
            Property actorProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/HasActor");
            StmtIterator actorIterator = targetMovie.listProperties(actorProperty);
            while (actorIterator.hasNext()) {
                Statement actorStatement = actorIterator.next();
                RDFNode actorNode = actorStatement.getObject();
                if (actorNode != null && actorNode.isResource()) {
                    Resource actorResource = actorNode.asResource();
                    Property nameProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/name");
                    StmtIterator actorNameIterator = actorResource.listProperties(nameProperty);
                    while (actorNameIterator.hasNext()) {
                        Statement actorNameStatement = actorNameIterator.next();
                        RDFNode actorNameNode = actorNameStatement.getObject();
                        if (actorNameNode != null && actorNameNode.isLiteral()) {
                            String actorName = actorNameNode.asLiteral().getString();
                            System.out.println("Actor: " + actorName);
                        }
                    }
                }
            }
            
            // Display the Director
            Property directorProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/HasDirector");
            StmtIterator directorIterator = targetMovie.listProperties(directorProperty);
            while (directorIterator.hasNext()) {
                Statement directorStatement = directorIterator.next();
                RDFNode directorNode = directorStatement.getObject();
                if (directorNode != null && directorNode.isResource()) {
                    Resource directorResource = directorNode.asResource();
                    Property nameProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/name");
                    StmtIterator directorNameIterator = directorResource.listProperties(nameProperty);
                    while (directorNameIterator.hasNext()) {
                        Statement directorNameStatement = directorNameIterator.next();
                        RDFNode directorNameNode = directorNameStatement.getObject();
                        if (directorNameNode != null && directorNameNode.isLiteral()) {
                            String directorName = directorNameNode.asLiteral().getString();
                            System.out.println("Director: " + directorName);
                        }
                    }
                }
            }

            // Display the Writer
            Property writerProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/HasWriter");
            StmtIterator writerIterator = targetMovie.listProperties(writerProperty);
            while (writerIterator.hasNext()) {
                Statement writerStatement = writerIterator.next();
                RDFNode writerNode = writerStatement.getObject();
                if (writerNode != null && writerNode.isResource()) {
                    Resource writerResource = writerNode.asResource();
                    Property nameProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/name");
                    StmtIterator writerNameIterator = writerResource.listProperties(nameProperty);
                    while (writerNameIterator.hasNext()) {
                        Statement writerNameStatement = writerNameIterator.next();
                        RDFNode writerNameNode = writerNameStatement.getObject();
                        if (writerNameNode != null && writerNameNode.isLiteral()) {
                            String writerName = writerNameNode.asLiteral().getString();
                            System.out.println("Writer: " + writerName);
                        }
                    }
                }
            }
        }
    }
}
