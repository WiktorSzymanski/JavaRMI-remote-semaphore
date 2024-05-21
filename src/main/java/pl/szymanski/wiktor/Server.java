package pl.szymanski.wiktor;

import pl.szymanski.wiktor.remoteSemaphore.RemoteSemaphoreImpl;

import java.rmi.RemoteException;

public class Server {
    public static void main(String... args) {
        try {
            System.out.println("Starting server...");
            RemoteSemaphoreImpl remoteSemaphore = new RemoteSemaphoreImpl(5);
            remoteSemaphore.createStubAndBind();
        } catch (RemoteException e) {
            System.err.println("Remote exception: " + e.getMessage());
        }
    }
}
