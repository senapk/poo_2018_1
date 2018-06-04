#ifndef PALAVRA_H
#define PALAVRA_H

#include <iostream>
using namespace std;
class Palavra
{
    string categoria;
    string palavra;
    string chutes;

public:
    Palavra(string cat, string pal);

    bool testarChute(char letra);
    string getCifra();
    bool estaRevelada();
    string getChutes();
    string getCategoria();
};

#endif // PALAVRA_H












