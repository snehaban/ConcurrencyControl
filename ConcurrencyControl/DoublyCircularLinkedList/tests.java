package cse505_hw1;

/**
 * @Title   CSE505 Assignment1 - Concurrency Control
 * @Name    Sneha Banerjee
 * @UBemail snehaban@buffalo.edu
 */

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import cse505_hw1.CDLList.Cursor;
import cse505_hw1.CDLList.Element;

public class tests 
{
	@Test
    public void CDLCoarse()
	{
        try
        {
        	CDLCoarse<String> list = new CDLCoarse<String>("hi");
        
	        Element head = list.head();
	        Cursor c = list.reader(list.head());
	        
	        for(int i = 74; i >= 65; i--) 
			{
			    char val = (char) i;
			    c.writer().insertBefore("" + val);
			}
	        
	        List<Thread> threadList = new ArrayList<Thread>();
	        for (int i = 0; i < 3500; i++) {
	            NormalThread nt = new NormalThread(list, i);
	            threadList.add(nt);
	        }
	        
	        RandomThread rt = new RandomThread(list);
	    	threadList.add(rt);
	            
	        try {
	            for(Thread t : threadList){
	            	t.start();
	            }
	            for (Thread t : threadList) {
	            	t.join();
	            }
	        } catch(InterruptedException e) {
	            System.err.println(e.getMessage());
	            e.printStackTrace();
	        }
	
	        System.out.print("\n\nCoarseList:");
	        list.printList();
        }
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
    }
	
	@Test
    public void CDLListFine()
	{
		try
		{
	        CDLListFine<String> list = new CDLListFine<String>("hi");
	        Element head = list.head();
	        Cursor c = list.reader(list.head());
	        
	        for(int i = 74; i >= 65; i--) 
			{
			    char val = (char) i;
			    c.writer().insertBefore("" + val);
			}
	        
	        List<Thread> threadList = new ArrayList<Thread>();
	        for (int i = 0; i < 3500; i++) {
	            NormalThread nt = new NormalThread(list, i);
	            threadList.add(nt);
	        }
	            
			RandomThread rt = new RandomThread(list);
			threadList.add(rt);
		
	        try {
	            for(Thread t : threadList){
	            	t.start();
	            }
	            for (Thread t : threadList) {
	            	t.join();
	            }
	        } catch(InterruptedException e) {
	            System.err.println(e.getMessage());
	            e.printStackTrace();
	        }
	        System.out.print("\n\nFineList:");
	        list.printList();
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
    }
   
	
	@Test
    public void CDLCoarseRW()
	{
        try
        {
        	CDLCoarseRW<String> list = new CDLCoarseRW<String>("hi");
        
	        Element head = list.head();
	        Cursor c = list.reader(list.head());
	        
	        for(int i = 74; i >= 65; i--) 
			{
			    char val = (char) i;
			    c.writer().insertBefore("" + val);
			}
	        
	        List<Thread> threadList = new ArrayList<Thread>();
	        for (int i = 0; i < 2000; i++) {
	            NormalThread nt = new NormalThread(list, i);
	            threadList.add(nt);
	        }
	        
	        RandomThread rt = new RandomThread(list);
	    	threadList.add(rt);
	            
	        try {
	            for(Thread t : threadList){
	            	t.start();
	            }
	            for (Thread t : threadList) {
	            	t.join();
	            }
	        } catch(InterruptedException e) {
	            System.err.println(e.getMessage());
	            e.printStackTrace();
	        }
	
	        System.out.print("\n\nCDLCoarseRW:");
	        list.printList();
        }
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
    }
	
    
	@Test
    public void CDLListFineRW()
	{
        try
        {
        	CDLListFineRW<String> list = new CDLListFineRW<String>("hi");
	        Element head = list.head();
	        Cursor c = list.reader(list.head());
	        
	        for(int i = 74; i >= 65; i--) 
			{
			    char val = (char) i;
			    c.writer().insertBefore("" + val);
			}
	        
	        List<Thread> threadList = new ArrayList<Thread>();
	        for (int i = 0; i < 3550; i++) {
	            NormalThread nt = new NormalThread(list, i);
	            threadList.add(nt);
	        }
	        
	        RandomThread rt = new RandomThread(list);
	    	threadList.add(rt);
	            
	        try {
	            for(Thread t : threadList){
	            	t.start();
	            }
	            for (Thread t : threadList) {
	            	t.join();
	            }
	        } catch(InterruptedException e) {
	            System.err.println(e.getMessage());
	            e.printStackTrace();
	        }
	
	        System.out.print("\n\nCDLListFineRW:");
	        list.printList();
        }
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
    }		
}



