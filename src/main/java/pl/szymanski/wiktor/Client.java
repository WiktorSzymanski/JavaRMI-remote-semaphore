package pl.szymanski.wiktor;

import pl.szymanski.wiktor.remoteSemaphore.RemoteSemaphore;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String... args) {
        try {
            int numOfPermits = Integer.parseInt(args[0]);
            int delay = Integer.parseInt(args[1]);

            Registry registry = LocateRegistry.getRegistry();
            RemoteSemaphore server = (RemoteSemaphore) registry.lookup("RemoteSemaphore");

            System.out.println("Available permits: " + server.availablePermits());
            server.acquire(numOfPermits);
            System.out.println("Acquired " + numOfPermits + " permits");
            Thread.sleep(delay);
            server.release(numOfPermits);
        } catch (RemoteException | NotBoundException | InterruptedException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}