package MoviePack;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.query.*;
import org.apache.jena.util.FileManager;

public class Jena4_Sparql {
    public static void main(String[] args) {
        // Load the ontology
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        FileManager fileManager = FileManager.get();
        String owlFile = "data/Movies.owl"; // Replace with the actual filename
        model.read(fileManager.open(owlFile), null);

        // Read the title of a movie (replace with your desired movie title)
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the movie title
        System.out.print("Enter the movie title: ");
        String movieTitle = scanner.nextLine();

        // Load the SPARQL query from file
        String queryFile = "data/movies_query.txt";
        String queryString = readQueryFromFile(queryFile);

        // Execute the query
        try (QueryExecution qexec = QueryExecutionFactory.create(queryString, model)) {
            ResultSet results = qexec.execSelect();

            boolean movieFound = false; // Track if the movie is found

            while (results.hasNext()) {
                QuerySolution solution = results.next();

                // Retrieve and display the movie details
                String movie = solution.get("title").toString();
                if (movieTitle.equals(movie)) {
                    movieFound = true;

                    String year = solution.get("year").toString();
                    String country = solution.get("country").toString();
                    String genre = solution.get("genre").toString();
                    String actor = solution.get("actor").toString();
                    String director = solution.get("director").toString();
                    String writer = solution.get("writer").toString();
                    String company = solution.get("company").toString();

                    System.out.println("Movie: " + movie);
                    System.out.println("Year: " + year);
                    System.out.println("Country: " + country);
                    System.out.println("Genre: " + genre);
                    System.out.println("Actor: " + actor);
                    System.out.println("Director: " + director);
                    System.out.println("Writer: " + writer);
                    System.out.println("Production Company: " + company);
                    System.out.println();
                }
            }

            if (!movieFound) {
                System.out.println("Error: Movie not found");
            }
        }
    }

    // Helper method to read the query from file
    private static String readQueryFromFile(String queryFile) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = FileManager.get().open(queryFile)) {
            if (inputStream != null) {
                String line;
                java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(inputStream, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
