package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The KeyValueService interface defines the remote methods for interacting with the key-value store server.
 */
public interface KeyValueService extends Remote {

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key       The key to retrieve the value.
     * @param requestId The unique identifier for the request.
     * @param clientID  The ID of the client making the request.
     * @return The value associated with the key, or null if the key does not exist.
     * @throws Exception if an error occurs during the retrieval process.
     */
    String getValue(String key, String requestId, String clientID) throws Exception;

    /**
     * Inserts or updates the value associated with the specified key.
     *
     * @param key       The key to be inserted or updated.
     * @param value     The value to be associated with the key.
     * @param requestId The unique identifier for the request.
     * @param clientID  The ID of the client making the request.
     * @return true if the operation is successful, false otherwise.
     * @throws Exception if an error occurs during the insertion or update process.
     */
    Boolean putValue(String key, String value, String requestId, String clientID) throws Exception;

    /**
     * Deletes the value associated with the specified key.
     *
     * @param key       The key to be deleted.
     * @param requestId The unique identifier for the request.
     * @param clientID  The ID of the client making the request.
     * @return true if the key is found and deleted, false otherwise.
     * @throws Exception if an error occurs during the deletion process.
     */
    Boolean deleteValue(String key, String requestId, String clientID) throws Exception;
}
