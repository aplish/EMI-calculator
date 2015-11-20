
import java.util.*;

public class EMICalculator {
		
   /*
	* Function 1: Calculate the Installment Amount of a loan given the following terms of the loan
	*/
			public double InstallAmount(double LoanAmmount,double Rate,double tenure,double numberOI,double RV)
			{
				Double RateOfInt=(Rate)/100; //per month roi in decimals
				double num=0;
				

num=(LoanAmmount*(RateOfInt/numberOI)-(RV*(RateOfInt/numberOI))/Math.pow((1+(RateOfInt/numberOI)),tenure))/(1-(1/Math.pow((1+(RateOfInt/numberOI)),tenure)));
				return(num);
		
			}

	/*
	 * Function 2: Generate the Repayment Schedule for the entire loan period ie. calculate and return Principal
	 and Interest component of each Installment.
	 */
	public void RepSch(double LoanAmmount,double RateOfInt,double tenure,double numberOI,double RV)
	{
		double install=InstallAmount( LoanAmmount, RateOfInt, tenure, numberOI,RV);
		double In=0;
		double OPn=LoanAmmount;//OPENING BALANCE
		double Pn=0;
		double OPn1;
		double[][] arr= new double[(int)(numberOI*(tenure/12))][4];
		for ( int n=1; n<=numberOI*(tenure/12);n++)
			{ 		In=OPn*((RateOfInt/100)/numberOI);
					Pn=install-In;
					OPn1=OPn-Pn;
				//-------Passing Values to array-----
					arr[n-1][0]=n;
					arr[n-1][1]=OPn;
					arr[n-1][2]=In;		
					arr[n-1][3]=Pn;
					//arr[n-1][4]=install;
					//arr[n-1][5]=RV;
					OPn=OPn1;
			}
		System.out.println("installmentNO\topeningBalance\t\tInterestComponent\tPrincipleComponent\t");
		for (int j=0;j<(numberOI*(tenure/12));j++)
			{
			System.out.print(j+1+"\t\t");
			for (int y=1;y<=3;y++)
			//	{System.out.printf(Math.round(arr[j][y])+"\t");}
			{
		try{ System.out.printf("%10.2f" + "\t\t",arr[j][y]); }
		
		catch(Exception e)
		{System.out.println("ERROR !! PLEASE NOTE : EMI is special case of loan.Installment is always 12 in a year.");
		}
							}
			System.out.println(" "+" ");
}
		
	}
	/*
	 * Function 3: Calculate the Principal and Interest component of an Installment.
	 */
	public void PcompIcomp(double LoanAmmount,double RateOfInt,double tenure,double numberOI,double RV, double 

installNo)
	{
		double install=InstallAmount( LoanAmmount, RateOfInt, tenure, numberOI,RV);
		double In=0;
		double OPn=LoanAmmount;//OPENING BALANCE
		double Pn=0;
		double OPn1;
		double[][] arr= new double[(int)(numberOI*(tenure/12))][6];
		for ( int n=1; n<=numberOI*(tenure/12);n++)
			{ 	In=OPn*((RateOfInt/100)/numberOI);
				Pn=install-In;
				OPn1=OPn-Pn;
				arr[n-1][0]=n;
				arr[n-1][1]=OPn;
				arr[n-1][2]=In;		
				arr[n-1][3]=Pn;
				arr[n-1][4]=install;
				arr[n-1][5]=RV;
		        OPn=OPn1;
			}
		

System.out.println("installmentNO\t\topeningBalance\t\tInterestComponent\t\tPrincipleComponent\t\tInstallment\t\treturn value");
					for (int y=0;y<=5;y++)
					{System.out.print(arr[(int)installNo-1][y]+"\t\t");}
					System.out.println(" ");
		}
		
//---------------MAIN METHOD----------------------------	

	public static void main(String[] args)
	{
		EMICalculator e1=new EMICalculator();
		Scanner in = new Scanner (System.in);
		double loan=0;
		double roi=0;
		double ten=0;
		double noi=0;
		double rval=0;
		double inom=0;
		char sh='y';
		do{
						
//----------User Input Exception Handling------------

		//----------------------LOAN AMOUNT-----------			
					do {
				        System.out.println("\nEnter your loan amount : ");
				        while (!in.hasNextDouble()) {
				            System.out.println("ERROR !! Enter valid Loan Amount : ");
				            in.next(); 
				        }
				        loan = in.nextDouble();
				        if (loan<=0)
				        {System.out.println("ERROR !! Enter a positive value.");}
				    } while (loan <= 0);
					
//-------------------------Rate Of Interest---------------------
					do {
				        System.out.println("Enter Annual Rate of interest : ");
				        while (!in.hasNextDouble()) {
				            System.out.println("ERROR !! Enter valid Rate of Interest : ");
				            in.next(); 
				        }
				        roi = in.nextDouble();
				        if (roi<=0)
				        {System.out.println("ERROR !! Enter a positive value.");}
				    } while (roi < 0);
//------------------------------------TENNURE-------------------
					do {
				        System.out.println("Enter your tennure of loan in months: ");
				        while (!in.hasNextDouble()) {
				            System.out.println("Enter valid number value : ");
				            in.next(); 
				        }
				        ten = in.nextDouble();
				        if (ten<=0)
				        {System.out.println("ERROR !! Enter a positive value.");}
				    } while (ten <= 0);
//---------------------------------------NUMBER OF INSTALLMENTS-----------------------
					do {
				        System.out.println("Enter Number of installments per year: \n12 for Yearly ");
				        while (!in.hasNextDouble()) {
				            System.out.println("ERROR !! Enter valid Number : ");
				            in.next(); 
				        }
				        noi = in.nextDouble();
				        if (noi<=0||noi>12)
				        {System.out.println("ERROR !! Enter a positive value less than 12.");}
				    } while (noi <= 0);
//-----------------------------------------------					
					
	
	  
	   	   
	   		
		int choice;	
		char ch='y';
		do
		{
			System.out.println("Following services are provided : \n1. Know installment amount \n2. Know "+
"about Repay Schedule \n3. to know the  principle and intrest part of loan. for a particular installment \n4.Exit");
			String str4=in.next();
		 	
			choice=Integer.parseInt(str4); 
		 if(choice<=0)
		 { System.out.println ("ERROR !! enter a positive ammount");
		 continue;
		 }
		 switch (choice)
			{
			case 1:
				System.out.println("Enter Return Value if there is any otherwise enter 0");
			    rval=in.nextDouble();
				System.out.println("monthly installment is " +Math.round(e1.InstallAmount(loan, roi, ten, noi, rval)*100.0)/100.0); //2296.073
				break;
			case 2:
				while(true)
				{
				 System.out.println("Enter Return Value if there is any otherwise enter 0. ");
			     rval=in.nextDouble();
			    if (rval>loan)
			    {
			    		System.out.println("ERROR !! Return Value should be less than loan amount.");
			    		continue;
			    }
	            e1.RepSch(loan, roi, ten, noi, rval);
				
	            break;
				}
				break;
			case 3:
				do {System.out.println("enter the installment number for which you want to installment details");
				 	inom=in.nextDouble();
				 	if (inom>((noi*ten)/12))
				 	{
					System.out.println("ERROR !! Installment number should be less than or equal to your total payble installments i.e. "+((noi*ten)/12));
				 	}
				   }
					while (inom<=0||inom>((noi*ten)/12) );
				
				
				System.out.println("Enter Return Value if there is any otherwise enter 0");
			    rval=in.nextDouble();
				e1.PcompIcomp(loan, roi, ten, noi, rval,inom);
				break;
			case 4:
				System.out.println("You chose to exit !");
				break;
				default:
					System.out.println("Enter a valid Choice between 1 to 4.");
		}
         System.out.print("Do you want to continue with same data press y for yes and n for no.");
       ch=in.next().charAt(0);
       System.out.println(" ");
		 
		} while(ch=='y'||ch=='Y');
		 System.out.print("Do you want to continue Transactions, press y for yes and n for exit.");
		sh=in.next().charAt(0);
		}while(sh=='y'||sh=='Y');
				
	System.out.print("You have exited sucessfully.");
	in.close();
	
		
		
				
				
		
		
	}
	}

