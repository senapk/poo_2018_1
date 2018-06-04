#include "palavra.h"

Palavra::Palavra(string cat, string pal):
    categoria(cat), palavra(pal)
{
}

bool Palavra::testarChute(char letra){
    chutes += letra;
    if(palavra.find(letra) != string::npos)
        return true;
    return false;
}

string Palavra::getCifra(){
    string cifra = palavra;
    for(char& let : cifra)
        if(chutes.find(let) == string::npos)
            let = '*';
    return cifra;
}

bool Palavra::estaRevelada(){
    return (getCifra() == palavra);
}

string Palavra::getChutes(){
    return chutes;
}

string Palavra::getCategoria(){
    return categoria;
}

