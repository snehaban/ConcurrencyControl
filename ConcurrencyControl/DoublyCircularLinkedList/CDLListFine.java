package cse505_hw1;

/**
 * @Title   CSE505 Assignment1 - Concurrency Control
 * @Name    Sneha Banerjee
 * @UBemail snehaban@buffalo.edu
 */

public class CDLListFine<T> extends CDLList<T>
{
	private Cursor reader;
	
	public CDLListFine(T v) 
	{
		super(v);
	}			
		
	@Override
	public Cursor reader(Element from) 
	{				
		reader = new Cursor(from);		
		return reader;
	}
	
	@Override
	public void printList() 
	{
		super.printList();
	}
	
	public class Cursor extends CDLList<T>.Cursor
	{
		private Writer writer;
		
		public Cursor(Element from)
		{
			super(from);
		}
		
		@Override
		public Element current()
		{
			return super.current();
		}
		
		@Override
		public void previous()
		{
			if(current() == null || current().prev == null)
			{
				System.out.println("The cursor is invalid, can't move to previous element.");
			}
			else
			{
				synchronized(curr)
				{
					super.previous();
				}
			}
		}
		
		@Override
		public void next()
		{
			if(current() == null || current().next == null)
			{
				System.out.println("The cursor is invalid, can't move to next element.");
			}
			else
			{
				synchronized(curr)
				{
					super.next();
				}
			}
		}
		
		@Override		
		public Writer writer() 
		{
			writer = new Writer(curr);
			return writer;
		}
	}
		
	public class Writer extends CDLList<T>.Writer
	{	
		public Writer(Element curr)
		{
			super(curr);
		}
		
		@Override
		public boolean insertBefore(T val) throws Exception
		{		
			Element temp = null;
			boolean b = false;
			boolean acquired = false;
			
			if(current==null || current.prev == null)
			{
				System.out.println("Invalid cursor. Cannot insert "+val);
				return false;
			}
			
			while(!acquired)
			{
				synchronized(current.prev)
				{
					temp = current.prev;
					temp.isLocked = true;
					//System.out.print("\nAcquired outer lock on " + temp.value());
					
					if(current!= null && !current.isLocked) // if unlocked
					{
						synchronized(current)
						{													
							current.isLocked = true;
							acquired = true;
							//System.out.print("\nAcquired inner lock on " + current.value());
							
							if(current.prev!=null && current.prev == temp)
							{
								b = super.insertBefore(val);
							}
							else
							{
								System.out.println("Cursor position has changed. "+val+" inserted.");
								super.insertBefore(val);
							}							
							temp.isLocked = false;					
							current.isLocked = false;							
						} // end inner synchronized		
						
					}
					else
					{
						temp.isLocked = false; // release outer lock if unable to acquire inner lock
						//System.out.println("\nReleasing outer lock " + temp.value());
					}
						
				} // end outer synchronized
				Thread.sleep(17);
			} // end while
			return b;
		}
		
		@Override
		public boolean insertAfter(T val) throws Exception
		{
			Element temp = null;
			boolean b = false;
			boolean acquired = false;
			
			if(current==null || current.next == null)
			{
				System.out.println("Invalid cursor. Cannot insert "+val);
				return false;
			}
			
			while(!acquired)
			{
				synchronized(current)
				{	
					current.isLocked = true;
					//System.out.print("\nAcquired outer lock on " + current.value());					
					
					if(current.next==null)
					{
						System.out.println("\nInvalid cursor, cannot insert "+val);
						current.isLocked = false;
						return false;
					}
					temp = current.next;					
					if(!temp.isLocked) // if unlocked
					{
						synchronized(temp)
						{								
							temp.isLocked = true;
							//System.out.print("\nAcquired inner lock on " + temp.value());
							acquired = true;
							b = super.insertAfter(val);							
							current.isLocked = false;
							temp.isLocked = false;							
						}
						
					}
					else
					{
						current.isLocked = false; // release outer lock if unable to acquire inner lock
						//System.out.println("\nReleasing outer lock " + temp.value());
					}
					
				} // end outer synchronized
				Thread.sleep(35);
			} // end while
			return b;
		}
		
		@Override
		public boolean delete() throws Exception
		{			
			Element prev = null, next = null;
			boolean b = false;
			boolean acquired = false;
			
			if(current == null || !current.isValid() || current.prev == null || current.next == null)
			{
				System.out.println("Invalid cursor, the element no longer exists, it cannot be deleted.");
				return false;
			}
			if(current == head())
			{
				System.out.println("The head of the circular linkedlist cannot be deleted.");
				return false;
			}
			while(!acquired)
			{
				synchronized(current.prev)
				{
					prev = current.prev;
					prev.isLocked = true;
					//System.out.print("\nAcquired outer lock on " + prev.value());					
						
					if(!current.next.isLocked) // if unlocked
					{
						synchronized(current.next)
						{													
							next = current.next;
							next.isLocked = true;							
							acquired = true;
							//System.out.print("\nAcquired inner lock on " + current.value());
							
							if(current.prev == prev && current.next == next)
							{
								b = super.delete();									
							}
							else
							{
								System.out.println("Invalid cursor. Cannot delete " + current.value());
								b = false;
							}						
							prev.isLocked = false;
							next.isLocked = false;							
						} // end inner synchronized		
						
					}
					else
					{
						prev.isLocked = false; // release outer lock if unable to acquire inner lock
						//System.out.println("\nReleasing outer lock " + prev.value());
					}						
				} // end outer synchronized
				Thread.sleep(52);
			} // end while
			return b;			
		}
	}		
}
