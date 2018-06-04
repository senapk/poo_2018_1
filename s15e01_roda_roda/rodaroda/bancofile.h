#ifndef BANCOFILE_H
#define BANCOFILE_H

#include "banco.h"
#include <vector>
#include <fstream>
#include <sstream>
#include "palavra.h"
using namespace std;

class BancoFile : public Banco
{
    string file = "../rodaroda/file.txt";
    vector<Palavra> banco;
public:
    BancoFile()
    {
        srand(time(NULL));
        std::ifstream ist(file);
        if(!ist){
            cout << "arquivo nao existe\n";
        }

        string linha;
        while(std::getline(ist, linha)){
            string cat;
            string pal;
            stringstream(linha) >> cat >> pal;
            banco.push_back(Palavra(cat, pal));
        }
    }
    virtual Palavra obterPalavra(){
        return banco[rand() % banco.size()];
    }
};

#endif // BANCOFILE_H
