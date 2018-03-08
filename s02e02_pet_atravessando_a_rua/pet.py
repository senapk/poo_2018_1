class Pet:
	qtd = 0; #static
	
	def __init__(self, nome, raca, idade):
		self.nome = nome
		self.raca = raca
		self.idade = idade
		self.isAlive = True
		self.vidas = 1
		if self.raca == "Cat":
			self.vidas = 7
		Pet.qtd += 1;
	
	def fazer_barulho(self): # metodo
		if self.isAlive:
			if self.raca == "Dog":
				print("au au")
			elif self.raca == "Cat":
				print("miau")
			else:
				print("iiiiiiiiii")
		else:
			print("RIP") #rest in peace
			
	def __str__(self):
		result = "Animal " + self.nome + " " + self.raca
		result += " " + str(self.idade) + " Vivo: " + str(self.isAlive)
		return result 

	def atravessar_a_rua(self):
		if self.isAlive:
			self.vidas -= 1
			if self.vidas == 0:
				self.isAlive = False 
	
zoo = []
zoo.append(Pet("Dog", "Dog", 5))
zoo.append(Pet("Xaninha", "Cat", 7))
zoo.append(Pet("Giba", "Soin", 2)) 

print(Pet.qtd) 
	
tem_vivo = True	
while(tem_vivo):
	tem_vivo = False
	for animal in zoo:
		animal.fazer_barulho()
		animal.atravessar_a_rua()
		if(animal.isAlive):
			tem_vivo = True
		print(animal)

