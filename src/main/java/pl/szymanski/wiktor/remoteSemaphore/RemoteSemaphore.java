package pl.szymanski.wiktor.remoteSemaphore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public interface RemoteSemaphore extends Remote {
    public void acquire(int permits) throws InterruptedException, RemoteException;
    public void release(int permits) throws RemoteException;
    public int availablePermits() throws RemoteException;
}
