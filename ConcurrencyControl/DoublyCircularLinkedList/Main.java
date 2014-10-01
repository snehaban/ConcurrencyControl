package cse505_hw1;

/**
 * @Title   CSE505 Assignment1 - Concurrency Control
 * @Name    Sneha Banerjee
 * @UBemail snehaban@buffalo.edu
 */

public class Main 
{			
	public static void main(String[] args) 
	{					
		/*final CDLListFine<String> f  = new CDLListFine<String>("0");
		final CDLListFine<String>.Cursor c = f.reader(f.head());
		try
		{
			c.writer().insertBefore("1");
			c.writer().insertBefore("2");
			c.writer().insertBefore("3");
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		final CDLListFine<String>.Cursor c1 = f.reader(f.head());
		c1.next();
		final CDLListFine<String>.Cursor c2 = f.reader(f.head());
		c2.next();c2.next();
				
		Runnable run1 = new Runnable()
		{
			public void run()
			{				
				try
				{
					c.writer().insertBefore("91"+Thread.currentThread().getName());
					c.writer().insertBefore("92"+Thread.currentThread().getName());
					c.previous();
					c.writer().insertBefore("12"+Thread.currentThread().getName());
					c.writer().insertBefore("13"+Thread.currentThread().getName());		
					c.previous();						
				}
				catch(Exception e)
				{ }
			}
		};		
			
		Runnable run2 = new Runnable()
		{
			public void run()
			{				
				try
				{
					c1.writer().insertBefore("93"+Thread.currentThread().getName());
					c1.writer().insertBefore("15"+Thread.currentThread().getName());
					c1.next();
					c1.writer().insertBefore("94"+Thread.currentThread().getName());
					c.writer().delete();
					c.writer().insertBefore("111"+Thread.currentThread().getName());
					c.previous();	
					c.writer().delete();			
					c.next();
				}
				catch(Exception e)
				{ }				
			}
		};
		
		Runnable run3 = new Runnable()
		{
			public void run()
			{				
				try
				{
					c2.writer().insertBefore("911"+Thread.currentThread().getName());
					c2.writer().insertBefore("922"+Thread.currentThread().getName());
					c2.previous();
					c.writer().delete();
					CDLListFine<String>.Cursor c3 = f.reader(f.head());
					c3.writer().insertBefore("123"+Thread.currentThread().getName());
					c3.writer().insertBefore("134"+Thread.currentThread().getName());		
					c3.previous();
				}
				catch(Exception e)
				{ }
			}
		};		
			
		Runnable run4 = new Runnable()
		{
			public void run()
			{				
				try
				{
					CDLListFine<String>.Cursor c = f.reader(f.head());				
					c.writer().insertBefore("393"+Thread.currentThread().getName());				
					c.writer().insertBefore("315"+Thread.currentThread().getName());
					c.next();
					c.writer().insertBefore("394"+Thread.currentThread().getName());		
					c = f.reader(f.head());
					c.writer().insertBefore("311"+Thread.currentThread().getName());
					c.previous();	
					c.writer().delete();			
					c.next();					
				}
				catch(Exception e)
				{ }
			}
		};		
		
		Thread t1 = new Thread(run1, "A");
		Thread t2 = new Thread(run2, "B");
		Thread t3 = new Thread(run3, "C");
		Thread t4 = new Thread(run4, "D");
		
		t1.start();
		t2.start();	
		t3.start();
		t4.start();
		try 
        {
            t1.join();
            t2.join();
            t3.join();
    		t4.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
		
		f.printList();
		
		run1.run();
		run2.run();
		run3.run();
		run4.run();*/

		tests d = new tests();
		d.CDLListFine();
		d.CDLCoarse();
		d.CDLCoarseRW();
		d.CDLListFineRW();

	}
}

