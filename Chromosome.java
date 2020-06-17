package geneticAlgorithm;

import java.util.Arrays;

public class Chromosome { // 염색체 클래스
	
	private boolean isFitnessChanged = true; // fitness값이 변경되었음을 정의
	private int fitness = 0; // 유전자와 동일한 유전자의 수
	private int[] genes; // 유전자를 나타내는 배열
	
	public Chromosome(int length) { // 유전자 배열 생성자
		genes = new int[length]; // genes배열의 길이를 정하고 주어진 집단에 대해 해당 배열을 인스턴스화
	}
	
	public Chromosome initializeChromosome() { // 주어진 염색체를 초기화 하기 위해 길이가 주어진 염색체로 돌아가는 배열
		for(int i=0; i<genes.length; i++) {
			// 임의의 수를 가지는 방법으로 무작위로 할당하는 염색체를 초기화
			if(Math.random() >= 0.9) genes[i] = 9;
			else if(Math.random() >= 0.8 && Math.random() < 0.9) genes[i] = 8;
			else if(Math.random() >= 0.7 && Math.random() < 0.8) genes[i] = 7;
			else if(Math.random() >= 0.6 && Math.random() < 0.7) genes[i] = 6;
			else if(Math.random() >= 0.5 && Math.random() < 0.6) genes[i] = 5;
			else if(Math.random() >= 0.4 && Math.random() < 0.5) genes[i] = 4;
			else if(Math.random() >= 0.3 && Math.random() < 0.4) genes[i] = 3;
			else if(Math.random() >= 0.2 && Math.random() < 0.3) genes[i] = 2;
			else if(Math.random() >= 0.1 && Math.random() < 0.2) genes[i] = 1;
			else genes[i] = 0;
		}
		
		return this;
	}
	
	public int[] getGenes() {
		isFitnessChanged = true;
		
		return genes;
	}
	
	public int getFitness() {
		if(isFitnessChanged) { // fitness가 변경되었는지 확인
			fitness = recalculaterFitness();
			isFitnessChanged = false;
		}
		
		return fitness;
	}
	
	// Fitness를 구하는 방법
	public int recalculaterFitness() {
		int chromosomeFitness = 0;
		for(int i=0; i<genes.length; i++) {
			if(genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]) chromosomeFitness++; // 각 염색체를 대상 염색체와 비교하여 Fitness를 증가
		}
		
		return chromosomeFitness; // 적합성 검사가 끝난 Fitness를 반환
	}
	
	public String toString() { // 배열의 내용을 출력하는 toString메서드
		return Arrays.toString(this.genes);
	}
}