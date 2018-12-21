import java.awt.*; 
import javax.swing.*; 
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.*; 
import javax.swing.plaf.metal.*; 
import javax.swing.text.*; 
public class FindString extends JFrame implements ActionListener{
  
	JFrame fr;
	JTextField tx;
	JButton bt;
	editor ed;
	FindString(editor ed)
	{   this.ed=ed;
	    fr=new JFrame("find");
	    fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    bt = new JButton("find");
	    bt.addActionListener(this);
	   
	    tx=new JTextField(20);
	    JPanel p=new JPanel();
	    p.add(tx);
	    p.add(bt);
	 
	    fr.add(p);
	    fr.setSize(200, 200);
	    fr.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
	    String s=evt.getActionCommand();
	    if(s.equals("find"))
	    {   
	    	String str=ed.t.getText();
        	String pat=tx.getText();
        	ArrayList list=new ArrayList();
        	int n=str.length();
        	int m=pat.length();
        	int[] lps=new int[m];
        	int i=1,j=0;
        	lps[0]=0;
        	while(i<m)
        	{
        		if(pat.charAt(i)==pat.charAt(j))
        		{
        			lps[i]=++j;
        			i++;
        		}
        		else
        		{
        			if(j>0)
        				j=lps[j-1];
        			else{
        				lps[i]=0;
        				i++;
        			}
        		}
        	}
        	i=0;
        	j=0;
        	while(i<n)
        	{
        		if(str.charAt(i)==pat.charAt(j))
        		{
        		     i++;
        		     j++;
        		     if(j==m)
        		     {
        		    	 list.add(i-j);
        		    	 j=lps[j-1];
        		   	 }
        		}
        		else
        		{
        			if(j>0)
        				j=lps[j-1];
        			else i++;
        		}
        	}
        	Iterator it=list.iterator();
        	Highlighter h = ed.t.getHighlighter();
        	h.removeAllHighlights();
        	
        	while(it.hasNext()) {
        		int pos=(Integer)it.next();
        		try {
					h.addHighlight(pos ,
					       pos + m,
					       DefaultHighlighter.DefaultPainter);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
	        fr.setVisible(false);
	    }
	}
	
}
