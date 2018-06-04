TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    palavra.cpp

HEADERS += \
    banco.h \
    bancohardcoded.h \
    palavra.h \
    bancofile.h \
    jogador.h \
    jogadorhumano.h \
    jogadormaquina.h

