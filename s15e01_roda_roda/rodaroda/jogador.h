#ifndef JOGADOR_H
#define JOGADOR_H

#include <iostream>
using namespace std;

class Jogador
{
    string nome;
public:
    Jogador(string nome):
        nome(nome)
    {}
    string getNome(){
        return nome;
    }

    virtual char chutarLetra(string chutes, string cifra) = 0;
};

#endif // JOGADOR_H









