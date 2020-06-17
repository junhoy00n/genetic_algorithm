package geneticAlgorithm;

import java.util.Arrays;

public class Driver { // 응용프로그램 Driver 클래스

	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation(); // 염색체의 수에 따라 인스턴스화
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(); // GeneticAlgorithm를 인스턴스화
		
		System.out.println("------------------------------------------------------------");
		// 0세대 염색체 정보
		System.out.println("Generation #0" + " | Fitness chromosome fitness: " + population.getChromosomes()[0].getFitness());
		printPopulation(population, "Target Chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
		
		int generationNumber = 0; // 유전자 세대
		// 세대수 마다 Fitness를 확인
		while(population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
			// 대상 엽섹체의 Fitness에 대해 가장 적합한 엽색체를 찾고 전부 일치하지 않으면 다음세대로 넘어감
			generationNumber++;
			System.out.println("------------------------------------------------------------");
			population = geneticAlgorithm.evolve(population);
			population.sortChromosomesByFitness(); // Fitness를 기준으로 정렬
			System.out.println("Generation #" + generationNumber + " | Fitness chromosome fitness: " + population.getChromosomes()[0].getFitness());
			printPopulation(population, "Target Chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
		}
	}
	
	public static void printPopulation(Population population, String heading) {
		System.out.println(heading);
		System.out.println("------------------------------------------------------------");
		for(int i=0; i<population.getChromosomes().length; i++) {
			System.out.println("Chromosome #" + i + " : " + Arrays.toString(population.getChromosomes()[i].getGenes()) // 모든 염색체의 정보를 출력
			+ " | Fitness: " + population.getChromosomes()[i].getFitness());
		}
	}
}