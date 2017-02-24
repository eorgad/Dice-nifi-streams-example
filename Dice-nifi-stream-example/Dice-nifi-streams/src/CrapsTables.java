/*
 *   CrapsTables calculate win or lose for two tables
 *  eorgad - Hortonworks.com 02/15/2017
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


class CrapsTables implements Runnable {
	private Thread t;
	private String threadName;

	CrapsTables( String name) {
		threadName = name;
		System.out.println("Creating " +  threadName );
	}

	public void run() {
		System.out.println("Running " +  threadName );
		String filepath = "//Users/eorgad/testnifi/"+threadName+".csv";

		FileWriter fw = null;
		BufferedWriter out;
		int resultofroll = 0;

		File file = new File(filepath);

		long startTime = (new Date()).getTime();

		try {

			CrapsTable table1 = new CrapsTable();

			for(int i = 1; i <= 1000; i++) {
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				fw = new FileWriter(file.getAbsoluteFile(), true);
				out = new BufferedWriter(fw);

				System.out.println("Thread: " + threadName + ", " + i);
				// Let the thread sleep for a while.
				int roll1 = roll(startTime/i + i);
				int roll2 = roll(startTime/i+1);
				int sumroll = roll1 + roll2;
				System.out.println( threadName + " " + i + "," + roll1 + "," + roll2 + "," + sumroll );

				if (table1.getMarker() > 0) {

					if (sumroll == 7) {
						table1.setMarker(0);
						table1.setSumofwinnings(table1.getSumofwinnings()-table1.getBet());
						table1.setFour(table1.getFour()-table1.getBetFourFiveNineTen());
						table1.setFive(table1.getFive()-table1.getBetFourFiveNineTen());
						table1.setSix(table1.getSix()-table1.getBetSixEight());
						table1.setEight(table1.getEight()-table1.getBetSixEight());
						table1.setNine(table1.getNine()-table1.getBetFourFiveNineTen());
						table1.setTen(table1.getTen()-table1.getBetFourFiveNineTen());
					} else if ((resultofroll == 2 ) || (resultofroll == 3 ) || (resultofroll == 11 ) || (resultofroll == 12 )) {
						//	 System.out.println ("        2, 3, 11, 12  " + markerroll);
					} else if ((sumroll == table1.getMarker()) && (table1.getMarker() != 0)) {
						System.out.println ("We Have a win: " + table1.getMarker());
						table1.setMarker(0);
						table1.setSumofwinnings(table1.getSumofwinnings()+table1.getBet());
					}
					//			markerroll = 0;


					if (sumroll == 6) {
						//	six+=(sixandeightbet*7)/sixandeightbet;
						table1.setSix(table1.getSix() + table1.getBetSixEight()*7/table1.getBetSixEight());
						System.out.println( "Six win markeron: " + table1.getMarker() );
					}
					if (sumroll == 8) {
						table1.setEight(table1.getEight()+ table1.getBetSixEight()*7/table1.getBetSixEight());
						System.out.println( "Eight win markeron: " + table1.getMarker() );
					}
					if (sumroll == 9) {
						//nine+=(ninefive4tenbet*7)/ninefive4tenbet;
						table1.setNine(table1.getNine()+table1.getBetFourFiveNineTen()*7/table1.getBetFourFiveNineTen());
						System.out.println( "Nine win markeron: " + table1.getMarker() );
					}
					if (sumroll == 5) {
						//five+=(ninefive4tenbet*7)/ninefive4tenbet;
						table1.setFive(table1.getFive()+table1.getBetFourFiveNineTen()*7/table1.getBetFourFiveNineTen());
						System.out.println( "Five win markeron: " + table1.getMarker() );
					}
					if (sumroll == 10) {
						//ten+=(ninefive4tenbet*9)/ninefive4tenbet;
						table1.setTen(table1.getTen()+table1.getBetFourFiveNineTen()*9/table1.getBetFourFiveNineTen());
						System.out.println( "Ten win markeron: " + table1.getMarker() + "Ten: " + table1.getTen());
					}
					if (sumroll == 4) {
						//four+=(ninefive4tenbet*9)/ninefive4tenbet;
						table1.setFour(table1.getFour()+table1.getBetFourFiveNineTen()*9/table1.getBetFourFiveNineTen());
						System.out.println( "Four win markeron: " + table1.getMarker() + "four: " + table1.getFour() );
					}

					System.out.println( threadName + " roll: " + i + " dice1: " + roll1 + " dice2:, " + roll2 + " sum:, " + sumroll + " sumofwinings:, " + table1.getSumofwinnings() + " markeron:, " + table1.getMarker() + " six: " + table1.getSix() + " eight: " + table1.getEight() + " five: " + table1.getFive() + " nine: " + table1.getNine() + " four: " + table1.getFour() + " ten: " +  table1.getTen() );
				}

				else if (table1.getMarker() == 0) {
					resultofroll = winPassLine(sumroll);
					if (resultofroll == 0) {
						// crap lost passline
						table1.setSumofwinnings(table1.getSumofwinnings()-table1.getBet());
					}
					else if (resultofroll == 1) {
						// won pass line
						table1.setSumofwinnings(table1.getSumofwinnings()+table1.getBet());
					}	else {
						table1.setMarker(resultofroll);
					}
	
				}	

				// ###################################### //
				out.write ( i + "," + roll1 + "," + roll2 + "," + sumroll + "," + table1.getSumofwinnings() + "," + table1.getMarker() + "," + table1.getSix() + "," + table1.getEight() + "," + table1.getFive() + "," + table1.getNine() + "," + table1.getFour() + "," + table1.getTen() + "\n");

				if (out != null)
					out.close();

				if (fw != null)
					fw.close();
				//out.close();

				Thread.sleep(1000);
			}
		}catch (InterruptedException e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting " +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}

	public static  int  roll(long seed) {


		Random dice = new java.util.Random(seed);

		int diceseed = (int)((Math.random() * 10000)) + 1;
		long startTime = (new Date()).getTime();
		Random rnd = new Random();
		rnd.setSeed(diceseed);
		int dice2 = rnd.nextInt(6) + 1; 

		// we want the value to be 1-6 and not 0-5

		return dice2;

	}
	public static int winPassLine(int thisRoll) {

		if (thisRoll == 7 || thisRoll == 11) {
			return 1;
		}
		if (thisRoll == 2 || thisRoll == 3 || thisRoll == 12)
		{
			return 0;
		}
		return thisRoll;

	}
}
