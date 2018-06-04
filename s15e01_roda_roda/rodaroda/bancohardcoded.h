#ifndef BANCOHARDCODED_H
#define BANCOHARDCODED_H

#include <vector>
#include <cstdlib>
#include <ctime>

#include "banco.h"
#include "palavra.h"
#include <sstream>

class BancoHardcoded : public Banco{
    const std::string texto =
            "banco caixa\n"
            "banco dinheiro\n"
            "banco seguranca\n"
            "banco cofre\n"
            "ufc estudante\n"
            "ufc professor\n"
            "ufc agua\n";

    std::vector<Palavra> banco;

public:
    BancoHardcoded()
    {
        srand(time(NULL));
        std::stringstream ss(texto);

        string linha;
        while(std::getline(ss, linha)){
            string cat;
            string pal;
            stringstream(linha) >> cat >> pal;
            banco.push_back(Palavra(cat, pal));
        }
    }
    Palavra obterPalavra(){
        return banco[rand() % banco.size()];
    }
};

#endif // BANCOHARDCODED_H











