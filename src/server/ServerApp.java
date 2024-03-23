package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Main class for starting the RMI server.
 */
public class ServerApp {

  private static final ServerLogger logger = new ServerLogger();

  /**
   * Main method to start the RMI server.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    try {
      // Create and start the RMI registry on port 1099
      LocateRegistry.createRegistry(1099);
      logger.info("RMI Registry created on port 1099");

      // Create an instance of the KeyValueServiceImpl
      KeyValueService store = new KeyValueServiceImpl();

      // Bind the KeyValueService object to the RMI registry with the name "RMI"
      Naming.rebind("RMI", store);
      logger.info("KeyValueService bound to RMI registry");

      logger.info("Server started");
    } catch (Exception e) {
      logger.error("Server failed: " + e.getMessage());
    }
  }
}
