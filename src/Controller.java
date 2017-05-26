import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Controller
{
	private int n, m;
	public int[][] board; 
	Map<Integer,Integer> ID = new HashMap<Integer,Integer>();
	int[][] tab = new int[3][3]; 
	boolean[][] tabbool = new boolean[3][3];
	double dyslo = 0;
	Dyslokacje dyslokacje;
	
	Controller(int n, int m)
	{
		 this.n = n;
		 this.m = m;
		 this.board = new int[this.n][this.m];
		 dyslokacje = new Dyslokacje(n,m);
		 for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++) 
			 {
				 this.board[i][j]=0;
			 }
		 }
	 }
	 
	public int moore(int x, int y,boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
			 return check(tab,x,y);
		 
	 }
	 
	public int neumann(int x, int y, boolean w)
	{	
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		tab[0][0]=0;
		tab[0][2]=0;
		tab[2][0]=0;
		tab[2][2]=0;
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
			 return max2;
	 }
	 
	public int hexleft(int x, int y, boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		tab[0][2]=0;
		tab[2][0]=0;
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
			 return max2;
	 }

	public int hexright(int x, int y, boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		tab[0][0]=0;
		tab[2][2]=0;
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
			 return max2;
	 }
	
	public int hexrand(int x, int y, boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		Random generator = new Random();
		int a = generator.nextInt(2);
		if(a==0)
		{
			tab[0][2]=0;
			tab[2][0]=0;
		}
		else if(a==1)
		{
			tab[0][0]=0;
			tab[2][2]=0;
		}
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
			 return max2;
		
	 }
	
	public int pentrand(int x, int y, boolean w)
	{
		if(w)
		{
			tab = period(x,y);
		}
		else 
		{
			tab = noperiod(x, y);
		}
		Random generator = new Random();
		int a = generator.nextInt(4);
		if(a==0)
		{
			tab[0][0]=0;
			tab[1][0]=0;
			tab[2][0]=0;
		}
		else if(a==1)
		{
			tab[0][2]=0;
			tab[1][2]=0;
			tab[2][2]=0;
		}
		else if(a==2)
		{
			tab[0][2]=0;
			tab[0][1]=0;
			tab[0][2]=0;
		}
		else if(a==3)
		{
			tab[2][0]=0;
			tab[2][1]=0;
			tab[2][2]=0;
		}
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
			 return max2;
	 }
	 
	public int[] sasiedzi(int[][] tab)
	{
		int[] pomid = new int[ID.size()];
		for(int i=0;i<ID.size();i++)
		{
			pomid[i]=0;
		} 
		 for(int i=0;i<3;i++){
			 for(int j=0;j<3;j++)
			 {
				 if(i==1 && j==1)
				 {  
					 //
				 }
				 else
				 {
					 for(int k=0;k<ID.size();k++)
					 {
						 if(tab[i][j]==ID.get(k))
						 {
							 pomid[k]++;
						 }
						 else
						 {	 
							 //
						 }
					 }
				 }
			 }
		 }
		 return pomid;
	}
	
	public int check(int[][] tab,int x,int y)
	{
		int[] pom = sasiedzi(tab);
		int max = 0;
		int max2 = 0;
		for(int i=0;i<ID.size();i++)
		{
			if(pom[i]>max && i!=0)
			{
				max = pom[i];
				max2 = i;
			}
		}
		return  max2;
	}
	
	public void clean(int x, int y)
	{
		 if(x==0 && y==0){
			 dyslokacje.tabdyslokacji[n-1][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][m-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y+1] = dyslokacje.beginro;
		 }
		 else if(x==0 && y==this.m-1){
			 dyslokacje.tabdyslokacji[n-1][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][m-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][0] = dyslokacje.beginro;	 
		 }
		 else if(x==this.n-1 && y==0){
			 dyslokacje.tabdyslokacji[x-1][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][m-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y+1] = dyslokacje.beginro;	
		 }
		 else if(x==this.n-1 && y==this.m-1){
			 dyslokacje.tabdyslokacji[x-1][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][0] = dyslokacje.beginro;	
		 }	
		 else if(x==0 && y>0 && y<this.m-1){
			 dyslokacje.tabdyslokacji[n-1][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[n-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y+1] = dyslokacje.beginro;	
		 }
		 else if(x>0 && x<this.n-1 && y==0){
			 dyslokacje.tabdyslokacji[x-1][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][m-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][m-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y+1] = dyslokacje.beginro;	
		 }
		 else if(x==this.n-1 && y>0 && y<this.m-1){
			 dyslokacje.tabdyslokacji[x-1][x-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][x-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[0][y+1] = dyslokacje.beginro;	
		 }
		 else if(y==this.m-1 && x>0 && x<this.n-1){
			 dyslokacje.tabdyslokacji[x-1][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][0] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][0] = dyslokacje.beginro;	
		 }
		 else{
			 dyslokacje.tabdyslokacji[x-1][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y-1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y-1] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y] = dyslokacje.beginro;
			 
			 dyslokacje.tabdyslokacji[x-1][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x][y+1] = dyslokacje.beginro;
			 dyslokacje.tabdyslokacji[x+1][y+1] = dyslokacje.beginro;	
		 }
	}
	
	public int[][] period(int x, int y)
	{
		//warunki periodczyne
		 if(x==0 && y==0){
			 tab[0][0] = this.board[n-1][m-1];  tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][y+1];
			 tab[1][0] = this.board[x][m-1];      tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][m-1];      tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];

		 }
		 else if(x==0 && y==this.m-1){
			 tab[0][0] = this.board[n-1][y-1];    tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][0];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][0];

		 }
		 else if(x==this.n-1 && y==0){
			 tab[0][0] = this.board[x-1][m-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][m-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[0][m-1];    tab[2][1] = this.board[0][y];	 tab[2][2] = this.board[0][y+1];

		 }
		 else if(x==this.n-1 && y==this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][0];
			 tab[1][0] = this.board[x][y-1];  	tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[0][y-1];  	tab[2][1] = this.board[0][y];	 tab[2][2] = this.board[0][0];

		 }	
		 else if(x==0 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[n-1][y-1]; tab[0][1] = this.board[n-1][y];  tab[0][2] = this.board[n-1][y+1];;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];

		 }
		 else if(x>0 && x<this.n-1 && y==0){
			 tab[0][0] = this.board[x-1][m-1]; 	tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][m-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][m-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];

		 }
		 else if(x==this.n-1 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[0][y-1];  	tab[2][1] = this.board[0][y];  	 tab[2][2] = this.board[0][y+1];

		 }
		 else if(y==this.m-1 && x>0 && x<this.n-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][0];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][0];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][0];

		 }
		 else{
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];

		 }
		 return tab;
	}
	
	public int[][] noperiod(int x, int y)
	{
		 if(x==0 && y==0){
			 tab[0][0] = 0;  	 tab[0][1] = 0;  				  tab[0][2] = 0;
			 tab[1][0] = 0;      tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;      tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x==0 && y==this.m-1){
			 tab[0][0] = 0;    					tab[0][1] = 0;  				 tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = 0;
		 }
		 else if(x==this.n-1 && y==0){
			 tab[0][0] = 0;    tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = 0;    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;    tab[2][1] = 0;				    tab[2][2] = 0;
		 }
		 else if(x==this.n-1 && y==this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];  	tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = 0;  					tab[2][1] = 0;	 				 tab[2][2] = 0;
		 }	
		 else if(x==0 && y>0 && y<this.m-1){
			 tab[0][0] = 0; 					tab[0][1] = 0;  				 tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
		 else if(x>0 && x<this.n-1 && y==0){
			 tab[0][0] = 0; 	tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = 0;    	tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;  	tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];	
		 }
		 else if(x==this.n-1 && y>0 && y<this.m-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = 0;  					tab[2][1] = 0;  	 			 tab[2][2] = 0;		
		 }
		 else if(y==this.m-1 && x>0 && x<this.n-1){
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = 0;
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = 0;
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = 0;
		 }
		 else{
			 tab[0][0] = this.board[x-1][y-1];  tab[0][1] = this.board[x-1][y];  tab[0][2] = this.board[x-1][y+1];
			 tab[1][0] = this.board[x][y-1];    tab[1][1] = this.board[x][y];    tab[1][2] = this.board[x][y+1];
			 tab[2][0] = this.board[x+1][y-1];  tab[2][1] = this.board[x+1][y];  tab[2][2] = this.board[x+1][y+1];
		 }
	
		 return tab;
	}
	
	public void rekrystalizacja()
	{
		int max = 0;
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				 	tab = period(i,j);
				 	
				 	int[] pom = sasiedzi(tab);
				 	max=0;
					for(int k=0;k<ID.size();k++)
					{
						if(pom[k]>max && k!=0)
						{
							max = pom[k];
						}
					}
					if(max==8)
					{
						dyslokacje.chceckboard[i][j]=false;
					}
					else
					{
						dyslokacje.chceckboard[i][j]=true;
					}
			 }
		 }
		
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				 this.board[i][j]=0; //czyszcze tablice
			 }
		 }
	}
	
	
	boolean help1 = false;
	boolean help2 = false;
	public void count(int a, boolean b)
	{
		int npoint = 100;
		if(help1)
		{
			dyslokacje.countro(n*m); //licze dyslokacje
			int t1,t2;
			int counter = 0;
			Random generator = new Random();
			while(counter < npoint)
			{
				t1 = generator.nextInt(n-1);
				t2 = generator.nextInt(m-1);
				if(dyslokacje.chceckboard[t1][t2])
				{
					dyslokacje.tabdyslokacji[t1][t2]+=dyslokacje.sum/dyslokacje.k;
					counter++;
				}
				else
				{  }
			}
			dyslokacje.sum=0.0;
		}
	
		int[][] apt = new int[this.n][this.m]; 
		int pomconter=0;
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {
				if(this.board[i][j]==0)
				{
					if(a==1)
					{	
						apt[i][j] = moore(i,j,b);	
					}
					else if(a==2)
					{
						apt[i][j] = neumann(i,j,b);
					}
					else if(a==3)
					{
						apt[i][j] = hexleft(i,j,b);
					}
					else if(a==4)
					{
						apt[i][j] = hexright(i,j,b);
					}
					else if(a==5)
					{
						apt[i][j] = hexrand(i,j,b);
					}
					else if(a==6)
					{
						apt[i][j] = pentrand(i,j,b);
					}
				}
				else
				{
					apt[i][j] = this.board[i][j];
					if(help2==false)
					{
						pomconter++;
					}
					
				}
			 }
		 }
		for(int i=0;i<n;i++)
		{
			 for(int j=0;j<m;j++)
			 {
				this.board[i][j] = apt[i][j];
			 }
		 }
			if(pomconter>=n*m)
			{
				System.out.println("ALL");
				rekrystalizacja();	
				help1=true;
				help2=true;
				pomconter=0;
			}
	 }

}