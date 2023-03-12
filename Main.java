import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.lang.Math;
public class Main {
	static void bubbleSort(int[] arr) {  //bubblesort algorithm which sorts an array
        int n = arr.length;  
        int temp = 0;  
        for(int i=0; i < n; i++){  
            for(int j=1; j < (n-i); j++){  
                if(arr[j-1] > arr[j]){  
                    temp = arr[j-1];  //swap elements  
                    arr[j-1] = arr[j];  
                    arr[j] = temp;  
                }  
                          
            }  
        }
	}	
	public static void reverse(int[] array) { //reverses the elements of an array makes the first element the last ..
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
	}
	public static double calsum(double[] array) { //calculates the sum of the elements of an array
		double sum = 0.0;
		for (double value : array) {
			sum += value;
		}
		return sum;
	}
    public static void main(String[] args) {
        int atoma = 5; 
		System.out.println("Population has 5 members");//22bits
		int max_val=1023;
		int min_val= -1024;
		int genia=0;
		String[] chr=new String[atoma]; //array with chromosomes values in binary
		String[] x1=new String[atoma]; //array with x1 values of every chromosome in binary
		String[] x2=new String[atoma];  //array with x2 values of every chromosome in binary
		int[] intx1=new int[atoma];  //array with x1 values of every chromosome in decimal
		int[] intx2=new int[atoma];  //array with x2 values of every chromosome in decimal
		double[] FITNESS= new double[atoma]; //array with fitness value of every chromosome
		double[] p= new double[atoma];   //array with position of every chromosome in sorting
		double[] ps= new double[atoma]; //array with propability of selection of every chromosome in sorting
		ThreadLocalRandom tlr = ThreadLocalRandom.current(); //used to take random numbers
		Random rand = new Random(); //used to take random numbers
		Scanner input = new Scanner(System.in);
		System.out.println("Give the number of generations: ");
		int numofgen= input.nextInt();  //user gives the number of generations that wants the program to run
		System.out.println("Choose a number from 1 to 2 for selective pressure (e.g. 1,5) ");
		double s= input.nextDouble(); //user gives a number for selective pressure
		while(s<1 || s>2){ //if number user gave is not correct, user must give a new number
			System.out.println("Choose a number from 1 to 2 for selective pressure (e.g. 1,5) ");
			s= input.nextDouble();
		}	
		int[] sinartisiakiTimi=new int[atoma]; //array with f(x1,x2) value of every chromosome
		int[] copy=new int[atoma]; //array with f(x1,x2) value of every chromosome
		System.out.println("");
		System.out.println("Population");
		System.out.println("------------------------------------------------------------------------------------------------------");
		for(int ii=0;ii<atoma;ii++){ //chromosomes values take random values 0 or 1
			String st="";
			String b1="";
			String b2="";
			for(int iii=0;iii<22;iii++){ //gia tin anaparastasi tou xi xrisimopoiountai 11 bits (2^11 =2048) ara 1023-(-1024)=2047<2^11
				int b=tlr.nextInt(0,2);  //ara kathe xi xreiazetai 11 bits ara gia tin anaparastasi kathe atomou xreiazintai 22 bits 
				if(iii<11){  //11 bitsgia to x1 aristera kai 11 bits gia to x2 dexia
					b1=b1+b;
				}else{
					b2=b2+b;
				}	
				st=st+b;
			}
			chr[ii]=st;
			System.out.print("Chromosome ");
			System.out.print(ii);
			System.out.print(" : ");
			System.out.println(chr[ii]);
			x1[ii]=b1;
			x2[ii]=b2;
		}	
		for(int i=0;i<atoma;i++){ //for every chromosome
			int z=Integer.parseInt(x1[i],2);   //makes x1 from binary to decimal 
			intx1[i]=-1024+z;	//convert binary to decimal
			int d=Integer.parseInt(x2[i],2);  //makes x2 from binary to decimal
			intx2[i]=-1024+d;  //convert binary to decimal
			sinartisiakiTimi[i]=intx1[i]*intx1[i]+intx2[i];	//euresi sinartisiakis timis
			copy[i]=intx1[i]*intx1[i]+intx2[i];	 //euresi sinartisiakis timis
			System.out.println("Chromosomes "+i+" with f("+i+")= "+copy[i]);
		}		
		while(numofgen!=genia){//oso den exoun ftasei oi epanalipseis ton aritho genion pou evale o xristis
			bubbleSort(sinartisiakiTimi); 	//taxinomo ton pinaka me tis sinartisiakes times gia na kano grammiki taxinomisi gia na vro ta fitness
			reverse(sinartisiakiTimi); //antistrefo ton pinaka me tis sinartisiakes times
			for(int i=0;i<atoma;i++){ //vrisko tin thesi tou atomou xi stin taxinomisi
				for(int j=0;j<atoma;j++){
					if(sinartisiakiTimi[j]==copy[i]){
						p[i]=j;
						break;
					}
				}
			}
			for(int i=0;i<atoma;i++){  
				FITNESS[i]=2-s+2*(s-1)*(p[i]-1)/(atoma-1); //vrisko ton vathmo ikanotitas kathe atomou
			}	
			double sumoffit=calsum(FITNESS); //calculate sum of fitness of chromosomes
			for(int i=0;i<atoma;i++){
				ps[i]=FITNESS[i]/sumoffit; //find the propability of selection of every chromosome
			}
			int parent1=0;
			int parent2=0;
			int g=genia+1;
			System.out.println("Generation "+g);	
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("Selection");
			double R1=tlr.nextDouble(0,1); //roulette-wheel selection 
			double R2=tlr.nextDouble(0,1); //take two random numbers one for every parent we want to select
			if(0<=R1 && R1<=ps[0]){ //if random number is here we choose first parent to be parent 0
				parent1=0;
			}else if(ps[0]<=R1 && R1<=ps[1]){	//if random number is here we choose first parent to be parent 1
				parent1=1;
			}else if(ps[1]<=R1 && R1<=ps[2]){	//if random number is here we choose first parent to be parent 2
				parent1=2;
			}else if(ps[2]<=R1 && R1<=ps[3]){	//if random number is here we choose first parent to be parent 3
				parent1=3;	
			}else if(ps[3]<=R1 && R1<=1){	//if random number is here we choose first parent to be parent 4
				parent1=4;	
			}
			if(0<=R2 && R2<=ps[0]){ //if random number is here we choose second parent to be parent 0
				parent2=0;
			}else if(ps[0]<=R2 && R2<=ps[1]){	 //if random number is here we choose second parent to be parent 1
				parent2=1;
			}else if(ps[1]<=R2 && R2<=ps[2]){	//if random number is here we choose second parent to be parent 2
				parent2=2;
			}else if(ps[2]<=R2 && R2<=ps[3]){	//if random number is here we choose second parent to be parent 3
				parent2=3;	
			}else if(ps[3]<=R2 && R2<=1){	//if random number is here we choose second parent to be parent 4
				parent2=4;		
			}
			while(parent1==parent2){  //if second parent choosed is the same with first parent then we take another random nummber and search where it is
				R2=tlr.nextDouble(0,1);
				if(0<=R2 && R2<=ps[0]){
					parent2=0;
				}else if(ps[0]<=R2 && R2<=ps[1]){	
					parent2=1;	
				}else if(ps[1]<=R2 && R2<=ps[2]){	
					parent2=2;
				}else if(ps[2]<=R2 && R2<=ps[3]){	
					parent2=3;	
				}else if(ps[3]<=R2 && R2<=1){	
					parent2=4;		
				}	
			}
			int ftimigonea1=copy[parent1]; //sinartisiaki timi protou gonea
			int ftimigonea2=copy[parent2]; //sinartisiaki timi deuterou gonea
			String binparent1 = chr[parent1]; //value first parent in binary
			String binparent2 = chr[parent2];  //value second parent in binary
			System.out.println("Chromosomes "+parent1+" ("+binparent1+")"+" and "+parent2+" ("+binparent2+")"+" are chosen to be parents.");
			System.out.println("End of Selection");
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("Crossover");
			int simioDias=rand.nextInt(22);  // diastaurosi enos simeiou dialego tixaia ena simeio diastaurosis
			String apogonos1=binparent1.substring(0,simioDias)+binparent2.substring(simioDias,binparent2.length()); //oi apogonoi exoun kapoia bits apo ton proto gonea
			String apogonos2=binparent2.substring(0,simioDias)+binparent1.substring(simioDias,binparent1.length());	 //kai kapoia apo ton deutero analoga me to simeio diastaurosis
			System.out.println("First Descendant : "+apogonos1); //dimiourgoume 2 apogonous
			System.out.println("Second Descendant : "+apogonos2);
			System.out.println("End of Crossover");
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("Mutation");  //metallaxi diadikou psifiou 
			int epilogiSinistosas1=rand.nextInt(22); // epilegoume tixaia tin sinistosa pou tha metalaxtei gia ton proto apogono
			int epilogiSinistosas2=rand.nextInt(22);  // epilegoume tixaia tin sinistosa pou tha metalaxtei gia ton deutero apogono
			String bit=apogonos1.substring(epilogiSinistosas1,epilogiSinistosas1+1); 
			if(bit.equals("0")==true){ //an to psifio einai 0 to kanoume 1 kai to antistrofo
				bit="1";
			}else{
				bit="0";
			}	
			String metalApogonos1=apogonos1.substring(0,epilogiSinistosas1)+bit+apogonos1.substring(epilogiSinistosas1+1,apogonos1.length());
			String binx1apogonou=metalApogonos1.substring(0,11); //vriskoume tin nea timi tou apogonou se binary opos kai to neo tou x1 kai x2
			String binx2apogonou=metalApogonos1.substring(11,22);
			int z1=Integer.parseInt(binx1apogonou,2);  
			int intx1apog=-1024+z1;	//ipologizoume tin timi tou xi se decimal
			int z2=Integer.parseInt(binx2apogonou,2);  
			int intx2apog=-1024+z2;	
			int sinartisiakitimiapog1=intx1apog*intx1apog+intx2apog; //vrisko tin sinartisiaki timi tou apogonou
			System.out.println("First Descendant that has been mutated: "+metalApogonos1+" with value of function f : "+sinartisiakitimiapog1);
			String bit1=apogonos2.substring(epilogiSinistosas2,epilogiSinistosas2+1); //kanoume tin idia diadikasia kai gia ton deutero apogono
			if(bit1.equals("0")==true){
				bit1="1";
			}else{
				bit1="0";
			}	
			System.out.println(epilogiSinistosas1);
			String metalApogonos2=apogonos2.substring(0,epilogiSinistosas2)+bit1+apogonos2.substring(epilogiSinistosas2+1,apogonos2.length());
			String binx1apogonou2=metalApogonos2.substring(0,11);
			String binx2apogonou2=metalApogonos2.substring(11,22);
			int z11=Integer.parseInt(binx1apogonou2,2);  
			int intx1apog2=-1024+z11;	
			int z22=Integer.parseInt(binx2apogonou2,2);  
			int intx2apog2=-1024+z22;	
			int sinartisiakitimiapog2=intx1apog2*intx1apog2+intx2apog2;
			System.out.println("Second Descendant that has been mutated: "+metalApogonos2+" with value of function f : "+sinartisiakitimiapog2);
			System.out.println("End of Mutation");
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("New population");//epilogi neon goneon
			int r1= rand.nextInt(5); //epeidi o arithmos ton apogonon einai mikroteros apo ton arithmo ton atomon tou plithismou
			int r2= rand.nextInt(5); //epilego tixaia 2 goneis kai tous antikathisto me tous apogonous
			System.out.println("First Descendant will replace parent "+r1+" and Second Descendant will replace parent "+r2);
			while(r1==r2){ //an epilextoun oi idioi goneis epilego allon 
				r2= rand.nextInt(5);
			}	
			genia=genia+1; //increase number of loops
			chr[r1]=metalApogonos1;//antikathisto tous palious goneis me tous apogonous kai tis sinartisiakes times tous
			copy[r1]=sinartisiakitimiapog1;
			chr[r2]=metalApogonos2;
			copy[r2]=sinartisiakitimiapog2;
			for(int i=0;i<atoma;i++){  //epanafero ton pinaka me tis sinartisiakes times stin arxiki katastasi gia na ton xrisimopoiiso stin epomeni epanalipsi
				sinartisiakiTimi[i]=copy[i];
			}
			sinartisiakiTimi[r1]=sinartisiakitimiapog1;
			sinartisiakiTimi[r2]=sinartisiakitimiapog2;
			for(int i=0;i<atoma;i++){
				System.out.println("Chromosomes "+i+":"+chr[i]+" with f("+i+")= "+copy[i]);
			}	
		}
    }
}