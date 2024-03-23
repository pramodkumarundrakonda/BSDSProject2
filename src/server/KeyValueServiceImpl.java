package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the KeyValueService interface using ConcurrentHashMap and Locks for synchronization.
 */
public class KeyValueServiceImpl extends UnicastRemoteObject implements KeyValueService {
    private static final Logger LOGGER = Logger.getLogger(KeyValueServiceImpl.class.getName());
    private ConcurrentHashMap<String, String> keyValueStore;
    private Lock lock;

    /**
     * Constructs a KeyValueServiceImpl object.
     *
     * @throws RemoteException if an RMI-related exception occurs.
     */
    public KeyValueServiceImpl() throws RemoteException {
        keyValueStore = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }

    @Override
    public String getValue(String key, String requestId, String clientID) throws RemoteException, Exception {
        lock.lock(); // Acquire the lock
        try {
            LOGGER.log(Level.INFO, "Request to get value for key: " + key +
                    " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            String value = keyValueStore.get(key);
            LOGGER.log(Level.INFO, "Value retrieved successfully: Key - " + key +
                    ", Value - " + value + " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            return value;
        } finally {
            lock.unlock(); // Release the lock in a final block
        }
    }

    @Override
    public Boolean putValue(String key, String value, String requestId, String clientID) throws RemoteException, Exception {
        lock.lock(); // Acquire the lock
        try {
            LOGGER.log(Level.INFO, "Request to put value: Key - " + key + ", Value - " + value +
                    " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            keyValueStore.put(key, value);
            LOGGER.log(Level.INFO, "Value inserted successfully: Key - " + key + ", Value - " + value +
                    " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            return true;
        } finally {
            lock.unlock(); // Release the lock in a final block
        }
    }

    @Override
    public Boolean deleteValue(String key, String requestId, String clientID) throws RemoteException, Exception {
        lock.lock(); // Acquire the lock
        try {
            LOGGER.log(Level.INFO, "Request to delete value for key: " + key +
                    " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            boolean deleted = keyValueStore.remove(key) != null;
            LOGGER.log(Level.INFO, "Value deletion status: Key - " + key + ", Deleted - " + deleted +
                    " (Request ID: " + requestId + ")" + "(Client ID: " + clientID + ")");
            return deleted;
        } finally {
            lock.unlock(); // Release the lock in a final block
        }
    }
}
