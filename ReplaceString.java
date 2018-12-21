import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class ReplaceString extends JFrame implements ActionListener{

	
	JFrame fr;
	JTextField tx1,tx2;
	JButton bt;
	editor ed;
	ReplaceString(editor ed)
	{   this.ed=ed;
	    fr=new JFrame("replace");
	    fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    bt = new JButton("replace");
	    bt.addActionListener(this);
	    tx1=new JTextField(20);
	    tx2=new JTextField(20);
	    JPanel p=new JPanel();
	    p.add(tx1);
	    p.add(tx2);
	    p.add(bt);
	    fr.add(p);
	    fr.setSize(200, 200);
	    fr.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
	    String s=evt.getActionCommand();
	    if(s.equals("replace"))
	    {   
	    	String str=ed.t.getText();
        	String pat=tx1.getText();
        	String replace=tx2.getText();
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
        	Map<Integer,Integer> hashMap=new HashMap<>();
        	
        	Iterator it=list.iterator();
        	Highlighter h = ed.t.getHighlighter();
        	h.removeAllHighlights();
        	
        	while(it.hasNext()) {
        		int pos=(Integer)it.next();
        		hashMap.put(pos,1);
        	}
        	String text="";
        	i=0;
        	while(i<n)
        	{
        		if(hashMap.get(i)!=null)
        		{
        			text=text+replace;
        			i+=m;
        		}
        		else
        		{
        			text=text+str.charAt(i);
        			i++;
        		}
        	}
        	ed.t.setText(text);
	        fr.setVisible(false);
	    }
	}

}
