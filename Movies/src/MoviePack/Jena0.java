package MoviePack;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Jena0 {
	
    public static void main(String[] args) {
    	
        // Load the ontology
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        FileManager fileManager = FileManager.get();
        String owlFile = "data/Movies.owl";
        model.read(fileManager.open(owlFile), null);
        
        // List all movie titles
        OntClass movieClass = model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Movie");
        ExtendedIterator<? extends OntResource> movieIterator = movieClass.listInstances();
        while (movieIterator.hasNext()) {
            OntResource movieResource = movieIterator.next();
            
            // Get the title property of the movie
            Property titleProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/title");
            
            // Get the value of the title property
            RDFNode titleNode = movieResource.getPropertyValue(titleProperty);
            
            if (titleNode != null && titleNode.isLiteral()) {
                // Extract the title value as a string
                String title = titleNode.asLiteral().getString();
                
                // Print the movie title
                System.out.println("Movie Title: " + title);
            }
        } 
    } 
}
