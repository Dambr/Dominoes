public class Domino{
	int MAXNUMBER;
	int COUNTPRESENTS;
	int countOfDominoes = 0;
	int sumOfDominoes   = 0;
	int[][] Dominoes;
	int[] PresentNumbers;

	public Domino(int MAXNUMBER, int COUNTPRESENTS){
		this.MAXNUMBER     = MAXNUMBER;
		this.COUNTPRESENTS = COUNTPRESENTS;
		initialCountOfDominoes();
		initialSumOfDominoes();
		initialDominoes();
		PresentNumbers = new int[this.COUNTPRESENTS];
		initialPresentNumbers();
	}

	void initialCountOfDominoes(){
		for (int i = 0; i <= this.MAXNUMBER; i ++){
			for (int j = 0; j <= i; j ++){
				this.countOfDominoes ++;
			}
		}
	}

	void initialSumOfDominoes(){
		for (int i = 0; i <= this.MAXNUMBER; i ++){
			for (int j = 0; j <= i; j ++){
				this.sumOfDominoes += i + j;
			}
		}
	}

	int numberForNewLine(){
		int count = 0;
		for (int i = 2; i <= Math.sqrt(this.countOfDominoes); i ++){
			if (this.countOfDominoes % i == 0){
				count ++;
			}
		}
		int[] numbers = new int[count];
		int index = 0;
		for (int i = 2; i <= Math.sqrt(this.countOfDominoes); i ++){
			if (this.countOfDominoes % i == 0){
				numbers[index] = i;
				index ++;
			}
		}
		return numbers[numbers.length - 1];
	}

	void initialDominoes(){
		this.Dominoes = new int[this.countOfDominoes][2];
		int index = 0;
		for (int i = 0; i <= this.MAXNUMBER; i ++){
			for (int j = 0; j <= i; j ++){
				this.Dominoes[index][0] = i;
				this.Dominoes[index][1] = j;
				index ++;
			}
		}
	}

	void showDominoes(){
		for (int i = 0; i < this.countOfDominoes; i ++){
			System.out.print("[" + this.Dominoes[i][0] + ":" + this.Dominoes[i][1] + "] ");
			if ((i + 1) % numberForNewLine() == 0){
				System.out.println();
			}
		}
	}

	boolean isPresent(int number){
		for (int i = 2; i <= Math.sqrt(number); i ++){
			if (number % i == 0){
				return false;
			}
		}
		return true;
	}

	int nextPresent(int number){
		int num = 1;
		while(true){
			if (isPresent(number + num)){
				return number + num;
			}
			num ++;
		}
	}

	int[] copyOfVector(int[] vector){
		int[] copy = new int[vector.length];
		for (int i = 0; i < vector.length; i ++){
			copy[i] = vector[i];
		}
		return copy;
	}

	int sumOfVector(int[] vector){
		int sum = 0;
		for (int i : vector){
			sum += i;
		}
		return sum;
	}

	void initialPresentNumbers(){
		int sum = 0;
		int[] _PresentNumbers = new int[this.COUNTPRESENTS];
		_PresentNumbers = copyOfVector(PresentNumbers);
		while(true){
			_PresentNumbers[0] = nextPresent(_PresentNumbers[0]);
			for (int i = 1; i < _PresentNumbers.length; i ++){
				_PresentNumbers[i] = nextPresent(_PresentNumbers[i - 1]);
			}
			if (sumOfVector(_PresentNumbers) > sumOfDominoes){
				break;
			}
			this.PresentNumbers[0] = nextPresent(this.PresentNumbers[0]);
			for (int i = 1; i < this.PresentNumbers.length; i ++){
				this.PresentNumbers[i] = nextPresent(this.PresentNumbers[i - 1]);
			}
		}
	}

	void showPresentNumbers(){
		System.out.print("[ " + this.PresentNumbers[0]);
		for (int i = 1; i < this.PresentNumbers.length; i ++){
			System.out.print(", " + this.PresentNumbers[i]);
		}
		System.out.println(" ]");
	}

	int[] dominoWithMaxSum(){
		int[] result = copyOfVector(this.Dominoes[0]);
		for (int i = 0; i < this.countOfDominoes; i ++){
			if (sumOfVector(this.Dominoes[i]) > sumOfVector(result)){
				result = copyOfVector(this.Dominoes[i]);
			}
		}
		return result;
	}

	int indexOfEnteredDomino(int[] vector){
		for (int i = 0; i < this.countOfDominoes; i ++){
			if (this.Dominoes[i][0] == vector[0] && this.Dominoes[i][1] == vector[1]){
				return i;
			}
		}
		return -1;
	}

	int indexOfDominoWithEnteredSum(int number){
		for (int i = 0; i < this.countOfDominoes; i ++){
			if (number == sumOfVector(this.Dominoes[i])){
				return i;
			}
		}
		return -1;
	}

	int sumOfNewDominoes(){
		int sum = 0;
		for (int i = 0; i < this.countOfDominoes; i ++){
			sum += sumOfVector(this.Dominoes[i]);
		}
		return sum;
	}

	int[] dominoWithMinSum(){
		int[] result = copyOfVector(dominoWithMaxSum());
		for (int i = 0; i < this.countOfDominoes; i ++){
			if (sumOfVector(this.Dominoes[i]) != 0 && sumOfVector(this.Dominoes[i]) < sumOfVector(result)){
				result = copyOfVector(this.Dominoes[i]);
			}
		}
		return result;
	}

	void showVectorWithSumOfEnteredPresentNumber(int number){
		while(number > 0){
			if (number > sumOfVector(dominoWithMaxSum())){
				if ((number - sumOfVector(dominoWithMaxSum())) >= sumOfVector(dominoWithMinSum())){
					int index = indexOfEnteredDomino(dominoWithMaxSum());
					System.out.print("[" + this.Dominoes[index][0] + ":" + this.Dominoes[index][1] + "] ");
					number -= sumOfVector(dominoWithMaxSum());
					this.Dominoes[index][0] = 0;
					this.Dominoes[index][1] = 0;
				}
				else{
					int index = indexOfEnteredDomino(dominoWithMaxSum());
					this.Dominoes[index][0] = 0;
					this.Dominoes[index][1] = 0;
				}
			}
			else{
				int index = indexOfDominoWithEnteredSum(number);
				System.out.print("[" + this.Dominoes[index][0] + ":" + this.Dominoes[index][1] + "] ");
				number = 0;
				this.Dominoes[index][0] = 0;
				this.Dominoes[index][1] = 0;
			}
		}
		System.out.println();
	}
}