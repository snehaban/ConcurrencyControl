package cse505_hw1;

/**
 * @Title   CSE505 Assignment1 - Concurrency Control
 * @Name    Sneha Banerjee
 * @UBemail snehaban@buffalo.edu
 */

public class CDLCoarse<T> extends CDLList<T>
{
	private CDLCoarse<T> list; 
	private Cursor reader;
	
	public CDLCoarse(T v) 
	{		
		super(v);
		list = this;
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
		// To prevent list updates in the middle of the print operation
		synchronized(list)
		{
			super.printList();
		}
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
			synchronized(list)
			{
				super.previous();
			}
		}
		
		@Override
		public void next()
		{
			synchronized(list)
			{
				super.next();
			}
		}
				
		@Override		
		public Writer writer() 
		{
			writer = new Writer(current());
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
			synchronized(list)
			{
				return super.insertBefore(val);
			}
		}
		
		@Override
		public boolean insertAfter(T val) throws Exception
		{
			synchronized(list)
			{				
				return super.insertAfter(val);
			}
		}
		
		@Override
		public boolean delete() throws Exception
		{
			synchronized(list)
			{
				return super.delete();
			}
		}
	}		
}
