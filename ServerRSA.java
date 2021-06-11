//Bob
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerRSA {
    static int e,d,n,M,C,phin;
    //public n and e
	public static void main(String args[]){
	try{
			
			ServerSocket serverSocket = new ServerSocket(5000);

			System.out.println("\n=============/ BOB /=============");

			System.out.println("Waiting for Alice...");

			//establish connection
			Socket socket = serverSocket.accept();

			System.out.println("Alice Connected...");

            DataInputStream din = new DataInputStream(socket.getInputStream());

			n = (int)din.readInt();
			System.out.println("n = "+n);
			
			phin = (int)din.readInt();
			System.out.println("totient(n) = "+phin);

			e = (int)din.readInt();
			System.out.println("e = "+e);
            
			C=(int)din.readInt();
			System.out.println("Cipher text = "+C);

			System.out.println("\nCalculating....\n");
			d=mod_inv(e,phin);
            System.out.println("d = "+d);

			M=RSA(C,d,n);

            System.out.println("Message = "+M);

			System.out.println("Private Key { d , n } = { "+d+" , "+n+" }");
			
			//close connection
			socket.close();
			serverSocket.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	static int pow(int a,int b,int n)
	    {
	        int c=0,f=1;
	        String s=Integer.toBinaryString(b);
	        for(int i=0;i<s.length();i++)
	        {
	            c=c*2;
	            f=(f*f)%n;
	            if(s.charAt(i)=='1')
	            {
	                c=1+c;
	                f=(f*a)%n;
	            }
	        }
        	return f;
	    }
	static int mod_inv(int a, int m)
	{
    int m0 = m;
    int y = 0, x = 1;

    if (m == 1)
        return 0;
    a=a%m;

    if(a<0) a=a+m;

    while (a > 1) {
        int q = a / m;
        int t = m;
        m = a % m;
        a = t;
        t = y;
        y = x - q * y;
        x = t;
    }

    if (x < 0) x += m0;
    return x;
    }
	
	static int RSA(int C,int d,int n)
	{
	    return pow(C,d,n);
	}

}