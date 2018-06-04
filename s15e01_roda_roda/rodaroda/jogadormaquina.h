#ifndef JOGADORMAQUINA_H
#define JOGADORMAQUINA_H

#include "jogador.h"
#include <iostream>
using namespace std;

class JogadorMaquina : public Jogador
{
public:
    JogadorMaquina(string nome):
        Jogador(nome)
    {}

    virtual char chutarLetra(string chutes, string cifra){
        string opcoes;

        for(char c = 'a'; c <= 'z'; c++)
            if(chutes.find(c) == string::npos)
                opcoes += c;


        cout << "chutes " << chutes << endl;
        cout << "cifra atual " << cifra << endl;
        cout << "Digite letra: " << endl;
        return opcoes[rand() % opcoes.size()];
    }
};

#endif // JOGADORMAQUINA_H













