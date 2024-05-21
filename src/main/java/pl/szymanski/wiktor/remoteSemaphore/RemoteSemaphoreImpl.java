package pl.szymanski.wiktor.remoteSemaphore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteSemaphoreImpl implements RemoteSemaphore {
    private int permits;

    public RemoteSemaphoreImpl(int permits) {
        this.permits = permits;
    }

    public void createStubAndBind() throws RemoteException {
        RemoteSemaphore stub = (RemoteSemaphore) UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RemoteSemaphore", stub);
    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException, RemoteException{
        System.out.println("Acquiring " + permits + " permits...");
        if (permits <= 0)
            throw new IllegalArgumentException("Permits to acquire must be positive");
        while (this.permits < permits)
            wait();
        this.permits -= permits;
        System.out.println("Acquired " + permits + " permits");
    }

    @Override
    public synchronized void release(int permits) throws RemoteException {
        System.out.println("Releasing " + permits + " permits");
        if (permits <= 0)
            throw new IllegalArgumentException("Permits to release must be positive");
        this.permits += permits;
        notifyAll();
    }

    @Override
    public int availablePermits() throws RemoteException {
        return permits;
    }
}
