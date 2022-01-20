package unbeerable_pun.github;

import java.io.IOException;
import org.apache.log4j.Logger;  
import org.apache.log4j.LogManager;
/**
 * @author: chads
 *
 */
public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        log.trace("Entering application.");
        try {
            BeerAdmin.loadConfig();
            BeerAdmin.getBeerListForStyle(5);
            BeerAdmin.printBeerList();
            BeerAdmin.printBeer("xwYSL2");
        } catch(IOException e) {
            //log.info("Error happened while reading config file");
            //log.debug("Error happened while reading config file, more details: " + e);
        }

        log.trace("Exiting application.");

    }
}
