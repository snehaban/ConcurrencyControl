package cse505_hw1;

/**
 * @Title   CSE505 Assignment1 - Concurrency Control
 * @Name    Sneha Banerjee
 * @UBemail snehaban@buffalo.edu
 */

public class RWLock 
{
	private volatile int readers; // current readers
	private volatile int writer;  // current writer - will be 0 or 1
	private volatile int waitingWriters;
	
	public RWLock()
	{
		readers = 0;
		writer = 0;
		waitingWriters = 0;
	}
	
	public void lockRead()
	{
		try
		{
			synchronized(this)
			{
				while(waitingWriters > 0 || writer > 0)
				{
					wait();  
				}
				readers++;
			}
		}
		catch(InterruptedException e){}		
	}
	
	public void unlockRead()
	{
		synchronized(this)
		{
			readers--;
			notifyAll();
		}
	}
	
	public void lockWrite()
	{		
		try
		{
			synchronized(this)
			{
				waitingWriters++;
				while(readers > 0 || writer > 0)
				{			
					wait();  
				}
				writer++;
				waitingWriters--;
			}
		}
		catch(InterruptedException e){}		
	}
	
	public void unlockWrite()
	{
		synchronized(this)
		{
			writer--;
			notifyAll();
		}
	}
}
