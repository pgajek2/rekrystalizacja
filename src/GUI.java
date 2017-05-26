import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantLocator;
import java.math.*;
import java.lang.Math;

public class GUI
{
	JFrame frame;
	JPanel sterowanie;
	JPanel show;
	JLabel info;
	JRadioButton vonneumann;
	JRadioButton moore;
	JRadioButton hexleft;
	JRadioButton hexright;
	JRadioButton hexrand;
	JRadioButton pentrand;
	ButtonGroup sasiedztwa;
	JRadioButton periodyczne;
	JRadioButton nieperiodyczne;
	ButtonGroup warunki;
	JRadioButton losowe;
	JRadioButton rownomierne;
	JRadioButton losowezR;
	JRadioButton klikniecie;
	JRadioButton ciaglelosowanie;
	ButtonGroup losowaniezarodkow;
	JTextField N;
	JTextField R;
	JTextField roz1, roz2;
	JLabel nap1;
	JLabel nap2;
    JButton[][] tab ;
    JButton pokaz;
	JButton reset;
	JButton ok;
    JRadioButton radio1;
	JRadioButton radio2;
	JRadioButton radio3;
	JRadioButton radio4;
	ButtonGroup group;
	Controller controller;

    int n,m;
	double k1=10,k2=10;
	int global = 0;
	int znumber;
	Map<Integer,Integer> ID = new HashMap<Integer,Integer>();
	Map<Integer,Color> ID_Color = new HashMap<Integer,Color>();

    ActionListener actioncreate = new ActionListener() {
		@Override
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(radio1.isSelected())
			{
				uruchom(25,25);
			}
			else if(radio2.isSelected())
			{
				uruchom(50,50);
			}
			else if(radio3.isSelected())
			{
				uruchom(100,100);
			}
			else if(radio4.isSelected())
			{
				uruchom(150,150);
			}
			else
			{
				n=Integer.parseInt(roz1.getText());
				m=Integer.parseInt(roz2.getText());;
				uruchom(n,m);
			}
			pokaz.setEnabled(false);
			show.setVisible(true);
			radio1.setEnabled(false);
			radio2.setEnabled(false);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			losowe.setEnabled(true);
			losowezR.setEnabled(true);
			rownomierne.setEnabled(true);
			klikniecie.setEnabled(true);
			ciaglelosowanie.setEnabled(true);
			reset.setEnabled(true);
		}
	};
	
	ActionListener actionreset = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<m;j++)
					{
						controller.board[i][j]=0;
						tab[i][j].setBackground(Color.WHITE);
					}
				}
				global=0;
				info.setText("-- Informations --");
			}
		};
		
	ActionListener actionlosowe = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			N.setEnabled(true);
			R.setEnabled(false);
			ok.setEnabled(true);
		}
	};
	
	ActionListener actionlosowezR = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			N.setEnabled(true);
			R.setEnabled(true);
			ok.setEnabled(true);
		}
	};
	
	ActionListener actionrownomierne = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			N.setEnabled(true);
			R.setEnabled(false);
			ok.setEnabled(true);
		}
	};
	
	ActionListener actionklik = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ok.setEnabled(false);
			N.setEnabled(false);
			R.setEnabled(false);
		}
	};
	
	ActionListener actionstart = new ActionListener() {
		int a=0,b=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(losowe.isSelected()) //losowanie zarodków
			{
				Random generator = new Random();
				znumber = Integer.parseInt(N.getText());
				int i = 0;
				while(i<znumber)
				{
					a = generator.nextInt(n);
					b = generator.nextInt(m);
					if(controller.board[a][b]==0)
					{
						global++;
						System.out.println(a+" "+b);
			            System.out.println("ID: "+global);
						controller.board[a][b]=global;	
						ID.put(global, global);
						controller.ID.put(global, global);
						Color color = randcolor();
						tab[a][b].setBackground(color); 
					    ID_Color.put(global, color);
						i++;
					}
					else
					{
						i=i;
					}
				}
			}
			else if(losowezR.isSelected()) //losowanie zarodków poza podanym promieñ
			{
				int r;
				Random generator = new Random();
				znumber = Integer.parseInt(N.getText());
				r = Integer.parseInt(R.getText());
				a = generator.nextInt(n);
				b = generator.nextInt(m);
				int rownanie = 0;
				int[][] tabR = new int[n][2];
				for(int i=0;i<znumber;i++)
				{
					tabR[i][0]=-1;
					tabR[i][1]=-1;
				}
				int count=0;
				while(count<znumber)
				{		
					if(controller.board[a][b]==0)
					{
						tabR[count][0]=a;
						tabR[count][1]=b;
						global++;
						controller.board[a][b]=global;	
						ID.put(global, global);
						controller.ID.put(global, global);
						Color color = randcolor();
						tab[a][b].setBackground(color); 
						ID_Color.put(global, color);
						count++;
						for(int i=0;i<n;i++)
						{
							for(int j=0;j<m;j++)
							{
								rownanie = ((int)Math.pow(i-a,2)+(int)Math.pow(j-b,2));
								if(rownanie==(int)Math.pow(r,2))
								{
										tab[i][j].setBackground(Color.RED); 
								}
							}								
						}
					}	
					else
					{
						//
					}	
					boolean pomm = false;
					int counterpom=0;
					while(true)//losuje dop
					{
						a = generator.nextInt(n);
						b = generator.nextInt(m); 
						for(int i=0;i<znumber;i++)
						{
							rownanie = ((int)Math.pow(a-tabR[i][0],2)+(int)Math.pow(b-tabR[i][1],2));
							//(x-a)^2-(y-b)^2<r^2
							if(rownanie<=(int)Math.pow(r,2))
							{
								pomm=true; //losowane zarodki znajduja sie w obrebie kola! blad, nalezy wylosowac nowe punkty
							}
						}
						if(pomm==false)//jesli znaleziono punkt przerwanie pêtli
						{	
							break;
						}
						if(counterpom>1000000) // gdy nie mozna znalezc punktow spelniajacych zalozenia
						{	
							break;
						}
						pomm=false;
						counterpom++;
					}
					if(counterpom>1000000) //Przerywamy dzialanie poniewaz Za du¿y R lub za du¿o n
					{
						info.setText("Za du¿y R lub za du¿o n");
						break;
					}
				}
			}
			else if(rownomierne.isSelected())//rownomierny rozklad ziaren
			{
				znumber = Integer.parseInt(N.getText());
				double l = Math.sqrt(znumber);
				int ll = (int) l;
				a=n/(2*ll);
				b=m/(2*ll);
				for(int i=a;i<n;i=i+2*a)
				{
					for(int j=b;j<m;j=j+2*b)
					{
						global++;
						controller.board[i][j]=global;	
						ID.put(global, global);
						controller.ID.put(global, global);
						Color color = randcolor();
						tab[i][j].setBackground(color); 
						ID_Color.put(global, color);
					}
				}
			}
			else if(ciaglelosowanie.isSelected())
			{
				Random generator = new Random();
				znumber = Integer.parseInt(N.getText());
				int i = 0;
				int counter = 0; 
				while(i<znumber)
				{
					a = generator.nextInt(n);
					b = generator.nextInt(m);
					if(controller.board[a][b]==0)
					{
						global++;
						controller.board[a][b]=global;	
						ID.put(global, global);
						controller.ID.put(global, global);
						Color color = randcolor();
						tab[a][b].setBackground(color); 
					    ID_Color.put(global, color);
						i++;
					}
					else
					{
						//
					}
					counter++;
					if(counter>1000000)
					{
						info.setText("Nie mo¿na wykonaæ operacji");
						break;
					}
				}
			}
		}
	};
	
	MouseListener actionpress = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(klikniecie.isSelected())
				{ 
					 String command = ((JButton) e.getSource()).getActionCommand();
					 Integer pom = Integer.parseInt(command);
					 int a=0,b=0;
					 int counter = 0;
					 int i=0;
					 int j=0;
					 for(i = 0;i<n;i++)
					 {
						 for(j=0;j<m;j++)
						 {
							 if(counter == pom)
							 {
								 a=i;
								 b=j;
							 }
							 counter++;
						 }
					 }
					 Object source = e.getSource();
					 global++;	
					 ID.put(global, global);
					 controller.ID.put(global, global);
					 source = e.getSource();
					 Color color = randcolor();
					 ((Component)source).setBackground(color);
					 info.setText(" x: "+a+"   y: "+b);
				     ID_Color.put(global, color);
					 controller.board[a][b]=global;
				}
				String command = ((JButton) e.getSource()).getActionCommand();
				Integer pom = Integer.parseInt(command);
				int a=0,b=0;
				 int counter = 0;
				 int i=0;
				 int j=0;
				for(i = 0;i<n;i++)
				 {
					 for(j=0;j<m;j++)
					 {
						 if(counter == pom)
						 {
							 a=i;
							 b=j;
						 }
						 counter++;
					 }
				 }
				System.out.println(controller.dyslokacje.tabdyslokacji[a][b]);
				System.out.println("id:"+ID.get(a));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		};
    
	MouseWheelListener l = new MouseWheelListener() { //krecac kólkiem spowodujemy rozrost ziaren
			@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					startcount();					
				}
			};
		
	GUI()//konstruktor GUI
	{
	
		frame = new JFrame("Rozrost ziaren");
		sterowanie = new JPanel();
		show = new JPanel();	
		vonneumann = new JRadioButton("Von Neumann");
		moore = new JRadioButton("Moore");
		hexleft = new JRadioButton("Hexagonal left");
		hexright = new JRadioButton("Hexagonal right");
		hexrand = new JRadioButton("Hexagonal rand");
		pentrand = new JRadioButton("Pentagonal rand");
		sasiedztwa = new ButtonGroup();
		periodyczne = new JRadioButton("Periodical");
		nieperiodyczne = new JRadioButton("Non-perdiodical");
		warunki = new ButtonGroup();
		losowe = new JRadioButton("Randomly");
		rownomierne = new JRadioButton("Evenly");
		losowezR = new JRadioButton("Randomly with R");
		klikniecie = new JRadioButton("Click");
		losowaniezarodkow = new ButtonGroup();
		ciaglelosowanie = new JRadioButton("Still draw");
		radio1 = new JRadioButton("25 x 25");
		radio2 = new JRadioButton("50 x 50");
		radio3 = new JRadioButton("100 x 100");
		radio4 = new JRadioButton("150 x 150");
		group = new ButtonGroup();
		N = new JTextField("10");
		R = new JTextField("5");
		roz1 = new JTextField("50");
		roz2 = new JTextField("50");
		nap1 = new JLabel("n:");
		nap2 = new JLabel("R:");
		info = new JLabel("Informations");
		pokaz = new JButton("Start");
		reset = new JButton("Resetuj");
		ok = new JButton("Run");
		global = 0;	
		vonneumann.setSize(120, 20);
		vonneumann.setLocation(50, 20);
		moore.setSize(120, 20);
		moore.setLocation(50, 40);
		moore.doClick();
		hexleft.setSize(120, 20);
		hexleft.setLocation(50, 60);
		hexright.setSize(120, 20);
		hexright.setLocation(50, 80);
		hexrand.setSize(120, 20);
		hexrand.setLocation(50, 100);
		pentrand.setSize(120, 20);
		pentrand.setLocation(50, 120);
		sasiedztwa.add(vonneumann);
		sasiedztwa.add(moore);
		sasiedztwa.add(hexleft);
		sasiedztwa.add(hexright);
		sasiedztwa.add(hexrand);
		sasiedztwa.add(pentrand);
		periodyczne.setSize(120, 20);
		periodyczne.setLocation(180, 20);
		periodyczne.doClick();
		nieperiodyczne.setSize(120,20);
		nieperiodyczne.setLocation(180, 40);
		warunki.add(periodyczne);
		warunki.add(nieperiodyczne);
		losowe.setSize(120, 20);
		losowe.setLocation(310, 20);
		losowe.addActionListener(actionlosowe);
		losowe.setEnabled(false);
		rownomierne.setSize(120, 20); 
		rownomierne.setLocation(310, 40);
		rownomierne.addActionListener(actionrownomierne);
		rownomierne.setEnabled(false);
		losowezR.setSize(120, 20);
		losowezR.setLocation(310, 60);
		losowezR.addActionListener(actionlosowezR);
		losowezR.setEnabled(false);
		klikniecie.setSize(120, 20);
		klikniecie.setLocation(310, 80);
		klikniecie.doClick();
		klikniecie.addActionListener(actionklik);
		klikniecie.setEnabled(false);
		ciaglelosowanie.setSize(120, 20);
		ciaglelosowanie.setLocation(310, 100);
		ciaglelosowanie.addActionListener(actionlosowe);
		ciaglelosowanie.setEnabled(false);
		N.setSize(60, 20);
		N.setLocation(230, 80);
		N.setEnabled(false);
		nap1.setSize(60,20);
		nap1.setLocation(200, 80);
		R.setSize(60, 20);
		R.setLocation(230, 100);
		R.setEnabled(false);
		nap2.setSize(60, 20);
		nap2.setLocation(200, 100);
		ok.setSize(60,20);
		ok.setLocation(215,130);
		ok.addActionListener(actionstart);
		ok.setEnabled(false);
		losowaniezarodkow.add(losowe);
		losowaniezarodkow.add(rownomierne);
		losowaniezarodkow.add(losowezR);
		losowaniezarodkow.add(klikniecie);
		losowaniezarodkow.add(ciaglelosowanie);
		radio1.setSize(100, 20);
		radio1.setLocation(440, 30);	
		radio2.setSize(100, 20);
		radio2.setLocation(440, 50);	
		radio3.setSize(100, 20);
		radio3.setLocation(440, 70);
		radio4.setSize(100, 20);
		radio4.setLocation(440, 90);
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
		roz1.setSize(60,20);
		roz1.setLocation(440, 130);
		roz2.setSize(60,20);
		roz2.setLocation(500, 130);
		pokaz.setSize(100, 50);
		pokaz.setLocation(580, 20);
		pokaz.addActionListener(actioncreate);
		reset.setSize(100, 50);
		reset.setLocation(580, 70);
		reset.addActionListener(actionreset);
		reset.setEnabled(false);
		info.setSize(200,20);
		info.setLocation(300, 160);
		info.setFont(new Font( null, 1, 14));
		sterowanie.add(vonneumann);
		sterowanie.add(moore);
		sterowanie.add(hexleft);
		sterowanie.add(hexright);
		sterowanie.add(hexrand);
		sterowanie.add(pentrand);
		sterowanie.add(periodyczne);
		sterowanie.add(nieperiodyczne);
		sterowanie.add(losowe);
		sterowanie.add(rownomierne);
		sterowanie.add(losowezR);
		sterowanie.add(klikniecie);
		sterowanie.add(N);
		sterowanie.add(R);
		sterowanie.add(ciaglelosowanie);
		sterowanie.add(radio1);
		sterowanie.add(radio2);
		sterowanie.add(radio3);
		sterowanie.add(radio4);
		sterowanie.add(pokaz);
		sterowanie.add(reset);
		sterowanie.add(nap1);
		sterowanie.add(nap2);
		sterowanie.add(ok);
		sterowanie.add(info);
		sterowanie.add(roz1);
		sterowanie.add(roz2);
		sterowanie.setLayout(null);
		sterowanie.setSize(800,200);
		sterowanie.setLocation(0, 0);
		show.setLayout(null);
		show.setSize(800,800);
		show.setLocation(100, 220);
		show.addMouseWheelListener(l);
		frame.setLayout(null);
		frame.add(sterowanie);
		frame.setSize(800, 900);
		frame.setLocation(200, 50);
		frame.setVisible(true);
	}
	
	void uruchom(int a, int b)
	{
		n=a;
		m=b;
		k1=(25.0/n)*24.0;
		k2=(25.0/m)*24.0;
		tab = new JButton[n][m];
		controller = new Controller(n,m);
		controller.ID.put(global, global);
		ID.put(global, global);
		ID_Color.put(global,Color.WHITE);
		int helpvariable = 0;
		 
		long start, stop;
		start = System.currentTimeMillis();
		for( int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{			
				tab[i][j] = new JButton();
				tab[i][j].setSize((int)k1,(int) k2);
				tab[i][j].setBackground(Color.WHITE);
				tab[i][j].setLocation((int)k1*i,(int)k2*j);
				//tab[i][j].setBorder(null);
				tab[i][j].addMouseListener(actionpress);
				String StringCommand = Integer.toString(helpvariable);
				tab[i][j].setActionCommand(StringCommand);
				show.add(tab[i][j]);		
				helpvariable++;
			}
		}	
		stop = System.currentTimeMillis(); 
		System.out.println("Time: "+(stop-start));
		show.setVisible(false);
		frame.add(show);
	}

	Color randcolor()//metoda losujaca kolor zarodka
	{
		int r=0,g=0,b=0;
		Color col;
		Random generator = new Random();
		r = generator.nextInt(254);
		g = generator.nextInt(254);
		b = generator.nextInt(254);
		col = new Color(r, g, b);
		return col;
	}
	
	void startcount()
	{			
		boolean w = true;
		
		if(periodyczne.isSelected())
		{
		    w = true;
		}
		else if(nieperiodyczne.isSelected())
		{
			w = false;
		}
	    if(moore.isSelected())
		{
			controller.count(1, w);
	    }
	    else if(vonneumann.isSelected())
		{
	    	controller.count(2, w);
	    }
		else if(hexleft.isSelected())
		{
			controller.count(3, w);
	    }
		else if(hexright.isSelected())
		{
			controller.count(4, w);
		}
		else if(hexrand.isSelected())
	    {
			controller.count(5, w);
		}
	    else if(pentrand.isSelected())
		{
			 controller.count(6, w);
		}
		
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				if(controller.dyslokacje.tabdyslokacji[i][j]>(4.21584E+12/(n*m)) && controller.board[i][j]==0)
				{
					 global++;	
					 ID.put(global, global);
					 controller.ID.put(global, global);
					 Color color = randcolor();
				     ID_Color.put(global, color);
					 controller.board[i][j]=global;
				     controller.dyslokacje.tabdyslokacji[i][j]=0;
				     info.setText("REKRYSTALIZACJA");
				}
				//if(controller.board[i][j]!=0)
				{
					tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));
				}
			 }
		 }
	}

}
