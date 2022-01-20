package unbeerable_pun.github;

import java.io.IOException;

/**
 * @author: chads
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        BeerAdmin.loadConfig();
        BeerAdmin.getBeerListForStyle(5);
        BeerAdmin.printBeerList();
        BeerAdmin.printBeer("xwYSL2");
    }
}
