//Alice
import java.io.DataOutputStream;
import java.net.Socket;

class ClientRSA {
    static int p=17,q=11,e=7,d,phin,n,M=88,C;

    //public n and e

	public static void main(String args[]){
        phin=(p-1)*(q-1);
        n=p*q;
	try{
		
			Socket socket = new Socket("localhost", 5000);

			System.out.println("\n=============/ ALICE /=============");

			System.out.println("Connected with Bob...");

			C=RSA(M,e,n);

			//sending the message
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            
			System.out.println("Sending n...");
			dout.writeInt(n);
			dout.flush();
			
			System.out.println("Sending totient(n)...");
			dout.writeInt(phin);
			dout.flush();
            
			System.out.println("Sendig e...");
            dout.writeInt(e);
			dout.flush();
			
			System.out.println("Sending C...");
            dout.writeInt(C);
			dout.flush();

			System.out.println("Public Key { e , n } = { "+e+" , "+n+" }");
			
			//close connection
			dout.close();
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	    static int pow(int a,int b,int n)
	    {
	        int c=0,f=1;
	        String s=Integer.toBinaryString(b);
	        for(int i=0;i<s.length();i++)
	        {
	            c=2*c;
	            f=(f*f)%n;
	            if(s.charAt(i)=='1')
	            {
	                c=c+1;
	                f=(f*a)%n;
	            }
	        }
        return f;
	    }

	    static int RSA(int M,int e,int n)
	    {
	        return pow(M,e,n);
	    }

}