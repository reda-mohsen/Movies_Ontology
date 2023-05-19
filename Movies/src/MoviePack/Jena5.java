package MoviePack;

import java.io.InputStream;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Jena5 {
    public static void main(String[] args) {
        // Load the ontology
        Model model = ModelFactory.createDefaultModel();
        FileManager fileManager = FileManager.get();
        String owlFile = "data/Movies.owl"; // Replace with the actual filename
        model.read(fileManager.open(owlFile), null);

        // Read the SPARQL query from file
        String queryFile = "data/movie_rules.txt";
        String queryString = readQueryFromFile(queryFile);

        // Execute the query and create a new model with the constructed triples
        Model resultModel = ModelFactory.createDefaultModel();
        try (QueryExecution qexec = QueryExecutionFactory.create(queryString, model)) {
            resultModel = qexec.execConstruct();
        }

        // Extract and display the persons who are both actors and directors
        System.out.println("Persons who are both actors and directors:");
        resultModel.listResourcesWithProperty(resultModel.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),
                resultModel.createResource("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/ActorDirector"))
                .forEachRemaining(resource -> System.out.println(resource.getURI().toString()));
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
