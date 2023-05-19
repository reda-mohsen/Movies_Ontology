package MoviePack;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;

/*
 *  loads the ontology and displays all the Persons
	(without using queries, without inference).
 * */

public class Jena1 {
    public static void main(String[] args) {
        // Load the ontology
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        FileManager fileManager = FileManager.get();
        String owlFile = "data/Movies.owl";
        model.read(fileManager.open(owlFile), null);

        // List all persons
        OntClass personClass = model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Person");
        ExtendedIterator<? extends OntResource> personIterator = personClass.listInstances();
        while (personIterator.hasNext()) {
            OntResource personResource = personIterator.next();
            
            // Retrieve the name of the person
            Property nameProperty = model.getProperty("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/name");
            StmtIterator personNameIterator = personResource.listProperties(nameProperty);
            while (personNameIterator.hasNext()) {
                Statement personNameStatement = personNameIterator.next();
                RDFNode personNameNode = personNameStatement.getObject();
                if (personNameNode != null && personNameNode.isLiteral()) {
                    String personName = personNameNode.asLiteral().getString();
                    System.out.println("Person: " + personName);
                }
            }

            // Check if the person is also an actor, director, or writer
            if (personResource.hasRDFType(model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Actor"))) {
                System.out.println(" - Actor");
            }
            if (personResource.hasRDFType(model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Director"))) {
                System.out.println(" - Director");
            }
            if (personResource.hasRDFType(model.getOntClass("http://www.semanticweb.org/redar/ontologies/2023/4/Movies/Writer"))) {
                System.out.println(" - Writer");
            }
        }
    }
}
