```
int soma(int a, int b)

class Personagem{
    //esses sao construtores
    //java
    public Personagem(String name)
    //python
    def __init__(self, name):
    //c++
    public:
    Personagem(string name)
    //typescript
    constructor(name: string)
    
    //isso sao metodos
    andar()
    falar()//metodo
}

//exemplo de como instanciar objetos em várias linguagens
//c++
Personagem fulano("jose");
//python
fulano = Personagem("jose");
//java
Personagem fulano = new Personagem("jose");
//typescript
let fulano = new Personagem("jose")

//chamando metodos
fulano.andar()


//################################
PASSAGEM de PARAMETROS

//c++
void charge(int value = 1, )
//parametros default
Pessoa(string nome = "fulana", int idade = 0, char sexo = 'f')

//python ordem opcional
__init__(self, nome = "fulana", idade = 0, sexo = "f")
maria = Pessoa(sexo = "m")

#############################################################

Metodos como parametros

funcao - first class
funções lambda

###########################################################

tipagem implicita - linguagens dinamica
tipagem explicita

//python
    def __str__(self)
//typescript
    toString()
//java
class Pessoa{
    //sobrescrita
    public String toString(){  
        return "bixa nojenta " + this.nome;
    }
        

Pessoa maria = new Pessoa("maria", 16)

//java - exemplo tipagem fraca
sysout("comi " + 2 + " salgadinhos com " + maria);//comi 2 salgadinhos com bixa nojenta maria
//python
print("comi " + str(2) + "salgadinhos");


tipagem forte - não mistura tipos
tipagem fraca


#################################
casts entre tipos
string para float
//java
Float.parseFloat("5.78")
//python
float("5.78")


##################################
    equals
    sobrecarga de operadores
    java é parcialmente orientada a objetos
    int a = 5;
    int b = 7;
    a == b
    classe Dog{
        public boolean equals(Dog d)
            return (this.nome == d.nome) && (this.sexo == d.sexo)
    
    Dog rufus = new Dog("rufus", 'm')
    Dog cristina = new Dog("cristina, 'f')
    Dog dog = new Dog("rufus", 'm')
    rufus.equals(dog) -> true
    rufus == dog -> false, eles nao sao o mesmo objeto(nao são o mesmo bloco de memoria)
    
    //python
    class Dog:
        def __sum__(self, outro):
            if self.sexo != outro.sexo
                return Dog(
                
    rufus + cristina -> rutina, 'm'
    Dog copular(Dog other)
    
#############################################


diretor.venderAreia(500)
    try{
    
    }catch(AtropeleiAlguemException){
    

coord.enviarCaminhao(morro2, 200)
coord.enviarCaminhao(morro3, 300)
    try{
        func.operarMaquina()
    }catch(MaquinaQuebrouException){
        
    }
    

func.operarMaquina() throw new MaquinaQuebrouException
                     throw new AtropeleiAlguemException
                     throw new abduzidoOVNIException
func.transAreia()

```





    
    
    















