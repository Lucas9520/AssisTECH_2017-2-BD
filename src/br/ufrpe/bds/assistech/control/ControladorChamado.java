package br.ufrpe.bds.assistech.control;

import java.util.ArrayList;
import java.util.List;
import br.ufrpe.bds.assistech.model.bean.Chamado;
import br.ufrpe.bds.assistech.model.dao.ChamadoDAO;

public class ControladorChamado {
	
	private ChamadoDAO chamado;
	
	public ControladorChamado() {
		this.chamado = new ChamadoDAO();
	}
	
	public void cadastrar(Chamado c){
		
		try {
			chamado.cadastrar(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean sohNumeros( String s ) {  
		boolean d = true;  
		
		for ( int i = 0; i < s.length(); i++ ) { 
			// verifica se a string s contém letras. A primeira letra que houver ele sai do laço.
			if ( Character.isAlphabetic( s.charAt(i)) ) {  
				d = false;  
				break;  
			}  
		}  
		return d;  
	}
	
	public void delete(Chamado c){
		chamado.remover(c);
	}

	public List<Chamado> listarTodos() {
		List<Chamado> chamados = new ArrayList<Chamado>();
		try {
			chamados = chamado.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chamados;
	}

	public List<Chamado> listarPorSequencial(String str) {
		List<Chamado> chamados = new ArrayList<Chamado>();
		try {
			chamados = chamado.listarPorSequencial(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chamados;
	}
}
