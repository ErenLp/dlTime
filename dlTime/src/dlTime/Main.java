package dlTime;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;
public class Main {
	public static void main(String[] args) {
		JFrame f=new JFrame("Download Time");
		JPanel p=new JPanel();JPanel pd=new JPanel();
		JTextField tf=new JTextField(10);
		JTextField tf2=new JTextField(".85",5);
		JButton conf=new JButton("Confirm");
		JLabel l=new JLabel("");
		String measurements[]= {"GB","MB"};
		JComboBox<String> cb=new JComboBox<>(measurements);
		Border br=BorderFactory.createTitledBorder("Size"),br2=BorderFactory.createTitledBorder("Speed");
		p.setBackground(Color.GRAY);
		pd.setBackground(Color.YELLOW);
		l.setFont(new Font("ComicSan",Font.BOLD,40));
		l.setForeground(Color.red);
		tf.setBorder(br);tf2.setBorder(br2);
		p.add(tf);p.add(tf2);
		p.add(cb);
		p.add(conf);
		pd.add(l);
		f.add(p);
		f.add(pd,BorderLayout.SOUTH);
		f.setVisible(true);
		f.setSize(600,150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		tf.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					conf.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		tf2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					conf.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		conf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double siz = 0;
				double result=0;
				double vals[]=new double[10];
				char []ops=new char[10];
				boolean operation=false;boolean move=false;
				boolean first=true,second=false,opp=false;
				String fi="";
				char op = 0;
				String x="",y="";
				char []c=tf.getText().toCharArray();
				for(int i=0;i<tf.getText().length();i++) {
					if(c[i]=='+'||c[i]=='-')
						operation=true;
				}
				for(int i=0;i<tf.getText().length();i++) {
					if(first) {
						if(c[i]=='+'||c[i]=='-') {
							opp=true;
						}
						else
						{System.out.println(x);
							x+=c[i];}
					}
					
					if(opp) {
						op=c[i];
						if(first) {
							second=true;
							first=false;
						}
						opp=false;
						continue;
					}
					if(second) {
						if(c[i]=='+'||c[i]=='-') {
							op=c[i];
							y="";
							x=Double.toString(result);
							System.out.println("X ="+x+" Y= "+y);
						}
						
						else {
							y+=c[i];
							System.out.println(x+"  "+y);
							if(op=='+')
								result=Double.parseDouble(x)+Double.parseDouble(y);
							else if(op=='-')
								result=Double.parseDouble(x)-Double.parseDouble(y);
						}
					}
				}
					if(cb.getSelectedItem()=="MB") {
						if(operation) {
							siz=result;	
							tf.setText(Double.toString(result));
						}
						else {
							siz=Double.parseDouble(tf.getText());	
						}
					}
					else{
						if(operation) {
							siz=result*1000;	
							tf.setText(Double.toString(result));
						}
						else {
							siz=Double.parseDouble(tf.getText())*1000;
						}
					}
					int []ar=new int[2];
					double sp=Double.parseDouble(tf2.getText());
					int full=(int)(siz/sp);
					int hr=full/3600;
					int ll=0;
					int min=(full/60-hr*60);
					int sec=full-min*60-hr*3600;
					ar[0]=hr;ar[1]=min;
					for(int i=0;i<ar.length;i++) {
						if(ar[i]==0) {
							ll++;
						}
					}
					switch(ll) {
					case 0:
						l.setText(hr+" hrs "+min+" mins "+sec+" sec ");
						break;
					case 1:
						if(hr==0) {
						l.setText(min+" mins "+sec+" sec ");
						break;}
						else if(hr!=0&&sec!=0){
							l.setText(hr+" hrs "+sec+" sec ");
							break;
						}
						else if(hr!=0&&sec==0){
							l.setText(hr+" hrs ");
							break;
						}
					case 2:
						l.setText(sec+" sec ");
						break;
					}
					}
			
			
		});
		/*conf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double siz;
				double vals[]=new double[10];
				char []ops=new char[10];
				boolean operation=false;boolean move=false;
				char op = 0;
				String x="",y="";
				char []c=tf.getText().toCharArray();
				for(int i=0;i<tf.getText().length();i++) {
					if(c[i]=='+'||c[i]=='-') {
						operation=true;
						op=c[i];
						
						break;
					}
				}
				if(operation) {
					for(int i=0;i<tf.getText().length();i++) {
						if(c[i]!=op&&!move) {
							x+=c[i];
						}
						else if(!move){
							move=true;
							continue;
						}
						if(move) {
							y+=c[i];
						}
					}
					double a=Double.parseDouble(x);
					double b=Double.parseDouble(y);
					double result=0;
					if(op=='+')
						result=a+b;
					else if(op=='-') {
						result=a-b;
					}
					String finalResult=String.format("%.2f", result);
					tf.setText(finalResult);
				}
				else {
					if(cb.getSelectedItem()=="MB")
						siz=Double.parseDouble(tf.getText());	
					else{
						siz=Double.parseDouble(tf.getText())*1000;	
					}
					int []ar=new int[2];
					double sp=Double.parseDouble(tf2.getText());
					int full=(int)(siz/sp);
					int hr=full/3600;
					int ll=0;
					int min=(full/60-hr*60);
					int sec=full-min*60-hr*3600;
					ar[0]=hr;ar[1]=min;
					for(int i=0;i<ar.length;i++) {
						if(ar[i]==0) {
							ll++;
						}
					}
					switch(ll) {
					case 0:
						l.setText(hr+" hrs "+min+" mins "+sec+" sec ");
						break;
					case 1:
						if(hr==0) {
						l.setText(min+" mins "+sec+" sec ");
						break;}
						else if(hr!=0&&sec!=0){
							l.setText(hr+" hrs "+sec+" sec ");
							break;
						}
						else if(hr!=0&&sec==0){
							l.setText(hr+" hrs ");
							break;
						}
					case 2:
						l.setText(sec+" sec ");
						break;
					}
					}
			}
			
		});*/
		
	}

}
