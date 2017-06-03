import java.util.Random;

public class Dyslokacje 
{
	double A = 0;
	double B = 0;
	public double t = 0;
	double ro = 0;
	double previousro = 0;
	double deltaro = 0;
	double ronapunkt = 0;
	double[][] tabdyslokacji;
	boolean[][] chceckboard;
	double sum = 0;
	double alldyslokacje = 0;
	int N,M;
	int k = 100;
	double beginro = 0;
	
	Dyslokacje(int n, int m)
	{
		N=n;
		M=m;
		tabdyslokacji = new double[N][M];
		chceckboard = new boolean[N][M];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				tabdyslokacji[i][j]=0;
				chceckboard[i][j]=false;
			}
		}
		A = 86710969050178.5;
		B = 9.41268203527779;
		t = 0;
		ro = 1;
		k= 10000;
		beginro = ro/(n*m);
		previousro = ro;
	}
	
	Dyslokacje() 
	{
		A = 8.6711e+13;
		B = 9.41e+00;
		t = 0;
		ro = 1;
		previousro = ro;
	}
	
	void countro(int nxm)
	{
		previousro = ro;
		t=t+0.001;
		ro = (A/B) + (1-(A/B))*Math.pow(Math.E,-B*t);
		deltaro = ro - previousro;
		ronapunkt = deltaro/(nxm);
		System.out.println("t: "+t);
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				check(i,j);
			}
		}
	}

	void check(int x, int y)
	{	
		if(chceckboard[x][y])
		{
			tabdyslokacji[x][y]+=0.8*ronapunkt;
			sum += 0.2*ronapunkt;
		}
		else
		{
			tabdyslokacji[x][y]+=0.2*ronapunkt;
			sum += 0.8*ronapunkt;
		}
	}

}
