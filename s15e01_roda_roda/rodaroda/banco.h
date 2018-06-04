#ifndef BANCO_H
#define BANCO_H

#include "palavra.h"

class Banco
{
public:
    Banco()
    {
    }
    virtual Palavra obterPalavra() = 0;
    virtual ~Banco(){
    }
};

#endif // BANCO_H






