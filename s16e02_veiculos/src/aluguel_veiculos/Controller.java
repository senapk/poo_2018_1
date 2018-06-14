package aluguel_veiculos;

import java.awt.List;

class Msg{
	String id;
	String texto;
}

class Comunicador {
	List<Msg> inbox;
	
	void enviar(Msg msg){
		List<Comunicador>  lista = pegarDestinatarios();
	}
	
	receber(Msg msg){
		inbox.add(msg);
	}
	
	List<Msg> ler(){
		List<Msg> aux = inbox;
		inbox.clear();
		return aux;
	}
	
	abstract List<Comunicador> pegarDestinatarios();
	
}

class Paciente{
	id
	diagnostico
	Medico[]
    addMedico(medico) {
		if medico ja existe
			return
		add na lista de medicos
		medico.addPaciente(this);
	}
	
	pegarDestinatarios(){
		return medicos;
	}
}

class Medico{
	id
	especialidade
	Paciente[]
	addPaciente(paciente) {
		if ja existe
			return
		add na lista de pacientes
		paciente.add(this)
	}
	
	getDestinatarios(){
		return pacientes;
	}
}

class Supervisor {
	lista medicos
	lista de pacientes
	
	pegarDest() {
		return List<Comunicador>(medicos).add(pacientes);	
	}
}

public class Controller {
	Paciente jose;
	Medico fern;
	
	jose.addMedico(fernando);
	
}
