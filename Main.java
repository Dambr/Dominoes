import java.io.*;
public class Main{
	static int InputMaxNumber(){
		int result = 6;
		try{
			System.out.print("Максимальное количество косточек на домино: ");
			result = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		}
		catch(Exception e){
			System.out.println("Ошибка ввода");
			result = InputMaxNumber();
		}
		return result;
	}
	static int InputCountPresents(){
		int result = 4;
		try{
			System.out.print("Количество групп: ");
			result = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		}
		catch(Exception e){
			System.out.println("Ошибка ввода");
			result = InputCountPresents();
		}
		return result;
	}
	public static void main(String[] args){
		int MAXNUMBER = InputMaxNumber();
		int COUNTPRESENTS = InputCountPresents();

		Domino $domino = new Domino(MAXNUMBER, COUNTPRESENTS);
		$domino.showDominoes();
		
		System.out.println("\nКоличество фишек домино = " + $domino.countOfDominoes);

		System.out.println("\nСумма точек фишек домино = " + $domino.sumOfDominoes);
		
		System.out.print("\nНабор простых чисел: ");
		
		$domino.showPresentNumbers();
		
		System.out.println("\nСумма простых чисел = " + $domino.sumOfVector($domino.PresentNumbers));
		
		System.out.println();
		for (int i : $domino.PresentNumbers){
			System.out.print(i + ": ");
			$domino.showVectorWithSumOfEnteredPresentNumber(i);
		}
	}
}