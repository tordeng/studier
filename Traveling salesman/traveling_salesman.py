import csv
import numpy as np
import itertools
import statistics

data = open("european_cities.csv", "r")
reader = csv.reader(data)
liste = list(reader)
liste2 = []
[liste2.append(i[0].split(";")) for i in liste]
array = np.array(liste2)

def exhaustiveSearch(liste):
	shortestDistance = 0
	for i in itertools.permutations(liste):
		cityList = [array[0][0]]
		distance = 0
		distanceList = []
		prev = 0
		for e in i:
			cityList.append(array[0][e])
			distance += float(array[prev+1][e])
			prev = e
		distanceList.append(array[prev+1][e])
		distance += float(array[prev+1][0])
		if shortestDistance > distance or shortestDistance == 0:
			shortestDistance = distance
			distList = distanceList
			cityRoute = cityList
	print(shortestDistance)
	print(cityRoute)

def getDistance(liste):
	distance = 0
	prev = 0
	for i in liste:
		distance += float(array[prev+1][i])
		prev = i
	distance += float(array[prev+1][0])
	return distance
	
def getRoute(liste):
	cityList = [array[0][0]]
	for i in liste:
		cityList.append(array[0][i])
	return cityList

def order_crossover(a, b, start, stop):
	child = [None]*len(a)

	# Copy a slice from first parent:
	child[start:stop] = a[start:stop]
	# Fill using order from second parent:
	b_ind = stop
	c_ind = stop
	l = len(a)
	while None in child:
		if b[b_ind % l] not in child:
			child[c_ind % l] = b[b_ind % l]
			c_ind += 1
		b_ind += 1
	
	return child
	
def order_crossover_pair(a, b):
	half = len(a) // 2
	start = np.random.randint(0, len(a)-half)
	stop = start + half
	return order_crossover(a, b, start, stop), order_crossover(b, a, start, stop)

def swap_mutation(genotype):
	locuses = np.random.choice(len(genotype), 2, replace=False)
	genotype[locuses[0]], genotype[locuses[1]] = genotype[locuses[1]], genotype[locuses[0]]
	return genotype
	
def newGeneration(solutions, bestGen):
	population = []
	for i in solutions:
		population.append((getDistance(i), i))
	population.sort(key = lambda distance: distance[0])
	parents = [population[i][1] for i in range(len(population)//2)]
	bestGen.append(statistics.mean([population[i][0] for i in range(len(population)//2)]))
	unselected = [population[len(population)-i-1][1] for i in range(len(population)//2)]
	
	children = []
	for i in range(len(parents)//2):
		a, b = order_crossover_pair(parents[i], parents[len(parents)//2+1])
		children.append(a)
		children.append(b)
	for i in children:
		swap_mutation(i)
	
	return parents + unselected + children, bestGen
	
def geneticAlg(liste, pop, generations):
	inspections = 0
	generationBest = []
	solutions = []
	for i in range(pop):
		solutions.append(np.random.permutation(liste))
		
	for i in range(generations):
		solutions, generationBest = newGeneration(solutions, generationBest)

	population = []
	for i in solutions:
		population.append((getDistance(i), i))
		inspections += 1
	
	population.sort(key = lambda distance: distance[0])
	print(population[0][0], getRoute(population[0][1]))
	

#runs an exhaustive search for 10 cities.
#exhaustiveSearch([i for i in range(1, 10)])

#runs a genetic algorithm on 10 cities with a population size of 20 over 20 generations.
for i in range(20):
	geneticAlg([i for i in range(1, 10)], 20, 20)

#runs a genetic algorithm on 24 cities with a population size of 20 over 20 generations.	
#for i in range (20):
#	geneticAlg([i for i in range(1, 24)], 20, 20)