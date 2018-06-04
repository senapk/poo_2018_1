#ifndef JOGADORHUMANO_H
#define JOGADORHUMANO_H
#include "jogador.h"

class JogadorHumano : public Jogador
{
public:
    JogadorHumano(string nome):
        Jogador(nome)
    {}

    virtual char chutarLetra(string chutes, string cifra){
        cout << "chutes " << chutes << endl;
        cout << "cifra atual " << cifra << endl;
        cout << "Digite letra: " << endl;
        char letra;
        cin >> letra;
        return letra;
    }
};

#endif // JOGADORHUMANO_H
