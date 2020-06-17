package geneticAlgorithm;

public class GeneticAlgorithm { // 유전자 알고리즘의 작동을 관리하는 클래스

	public static final int POPULATION_SIZE = 8; // 염색체의 수
	public static final int[] TARGET_CHROMOSOME = {2,4,5,3,1,7,7,1,6,9}; // 대상 염색체
	private static final double MUTATION_RATE = 0.25; // 돌연변이율
	public static final int NUMB_OF_ELITE_CHROMOSOMES = 1; // 엘리트 염색체의 수
	public static final int TOURNAMENT_SELECTION_SIZE = 4; // 토너먼트 인구 규모
	
	public Population evolve(Population population) { // 진화과정
		return mutatePopulation(crossoverPopulation(population)); // crossoverPopulation에서 반환된 것을 호출하여 돌연변이 결과를 반환
	}
	
	// crossoverPopulation에서 이전 Population의 길이와 crossoverPopulation을 return
	private Population crossoverPopulation(Population population) { // 교차한 염색체를 적용
		Population crossoverPopulation = new Population(population.getChromosomes().length);
		// 엘리트 염색체를 교차와 돌연변이에서 제외
		for(int i=0; i<NUMB_OF_ELITE_CHROMOSOMES; i++) {
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		
		for(int i=NUMB_OF_ELITE_CHROMOSOMES; i<population.getChromosomes().length; i++) {
			Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0]; // 엘리트 염색체
			Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2); // chromosome1과 chromosome2를 교차
		}
		
		return crossoverPopulation;
	}
	
	private Population mutatePopulation(Population population) { // 전달된 모든 염색체에 돌연변이를 적용
		Population mutatePopulation = new Population(population.getChromosomes().length);
		for(int i=0; i<NUMB_OF_ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		
		for(int i = NUMB_OF_ELITE_CHROMOSOMES; i<population.getChromosomes().length; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		
		return mutatePopulation;
	}
	
	// 유전자를 random으로 하는 method
	// 각각의 모체 염색체로 부터 택하여 새로운 염색체를 적용
	private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for(int i=0; i<chromosome1.getGenes().length; i++) {
			if(Math.random() < 0.5) crossoverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
			else crossoverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
		}
		
		return crossoverChromosome;
	}
	
	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for(int i=0; i<chromosome.getGenes().length; i++) { // 염색체의 모든 유전자를 확인
			if(Math.random() < MUTATION_RATE) { // 돌연변이율 보다 작을때 염색체에 대한 유전자에 수를 할당
				if(Math.random() < 0.1) mutateChromosome.getGenes()[i] = 9;
				else if(Math.random() >= 0.1 && Math.random() < 0.2) mutateChromosome.getGenes()[i] = 8;
				else if(Math.random() >= 0.2 && Math.random() < 0.3) mutateChromosome.getGenes()[i] = 7;
				else if(Math.random() >= 0.3 && Math.random() < 0.4) mutateChromosome.getGenes()[i] = 6;
				else if(Math.random() >= 0.4 && Math.random() < 0.5) mutateChromosome.getGenes()[i] = 5;
				else if(Math.random() >= 0.5 && Math.random() < 0.6) mutateChromosome.getGenes()[i] = 4;
				else if(Math.random() >= 0.6 && Math.random() < 0.7) mutateChromosome.getGenes()[i] = 3;
				else if(Math.random() >= 0.7 && Math.random() < 0.8) mutateChromosome.getGenes()[i] = 2;
				else if(Math.random() >= 0.8 && Math.random() < 0.9) mutateChromosome.getGenes()[i] = 1;
				else mutateChromosome.getGenes()[i] = 0;
			}
			else mutateChromosome.getGenes()[i] = chromosome.getGenes()[i]; // 돌연변이울 보다 크면 유전자를 유지
			
		}
		
		return mutateChromosome;
	}
	
	// 염색체 교차 선택을 위한 method
	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
		 // random으로 염색체를 선택
		for(int i=0; i<TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPopulation.getChromosomes()[i] = population.getChromosomes()[(int)(Math.random() * population.getChromosomes().length)];
		}
		tournamentPopulation.sortChromosomesByFitness();
		
		return tournamentPopulation;
	}
}