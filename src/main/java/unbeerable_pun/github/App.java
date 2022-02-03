package unbeerable_pun.github;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author: chads
 *
 */
public class App {

    private static Logger log = LogManager.getLogger(App.class);


    public static void main(String[] args) throws IOException {

        

        try {
            log.info("death");
            log.debug("bruh");
            BeerAdmin.loadConfig();
            BeerAdmin.getBeerListForStyle(5);
            BeerAdmin.printBeerList();
            BeerAdmin.printBeer("xwYSL2");
        } catch(IOException e) {
            System.out.println(e);
            log.info("Error happened while reading config file");
            log.debug("Error happened while reading config file, more details: " + e);
        }

        log.debug("Exiting application.");

    }
}
