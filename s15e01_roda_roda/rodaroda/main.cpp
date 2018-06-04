#include <iostream>
#include "banco.h"
#include "bancohardcoded.h"
#include "palavra.h"
#include "bancofile.h"
#include "jogador.h"
#include "jogadorhumano.h"
#include "jogadormaquina.h"
using namespace std;

int main()
{
    //Banco * banco = new BancoHardcoded();
    Banco * banco = new BancoFile();
    vector<Jogador *> jogs;

    jogs.push_back(new JogadorHumano("Humano"));
    jogs.push_back(new JogadorMaquina("Maq1"));
    jogs.push_back(new JogadorMaquina("Maq2"));

    Palavra p = banco->obterPalavra();
    cout << p.getCategoria() << endl;

    int ind = 0;
    Jogador * j = jogs[0];
    while(!p.estaRevelada()){
        char let;
        cout << "Turno: " << j->getNome() << endl;
        let = j->chutarLetra(p.getChutes(), p.getCifra());
        if(!p.testarChute(let)){
            ind = (ind + 1) % jogs.size();
            j = jogs[ind];
        }

    }
    //for desalocando todos os jogs
    delete banco;
    return 0;
}










