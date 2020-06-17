package geneticAlgorithm;

import java.util.Arrays;

public class Population { // 염색체 집단을 나타내는 Population 클래스
	
	private Chromosome[] chromosomes;
	
	public Population(int length) { // 염색체의 배열과 길이를 가지고 생성하는 생성자
		chromosomes = new Chromosome[length];
	}
	
	public Population initializePopulation() { 
		for(int i=0; i<chromosomes.length; i++) {
			// 염색체를 초기화 하고 배열에 저장
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
		}
		sortChromosomesByFitness(); // 염색체를 분류하고 Fitness를 기준으로 정렬
		
		return this;
	}
	
	public Chromosome[] getChromosomes() { // chromosomes 배열에 대해 get메서드를 가져와 염색체를 return
		return chromosomes;
	}
	
	public void sortChromosomesByFitness() { // fitness를 기준으로 염색체를 정렬
		Arrays.sort(chromosomes, (chromosome1, chromosome2) -> {
			int flag = 0;
			if(chromosome1.getFitness() > chromosome2.getFitness()) flag = -1;
			else if(chromosome1.getFitness() < chromosome2.getFitness()) flag = 1;
			
			return flag;
		});
	}
}