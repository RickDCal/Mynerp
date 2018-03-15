package br.com.mynerp.persistencia.dao;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.Pessoa;
import br.com.mynerp.persistencia.dao.exception.ColetaNaoEncontradaException;


@Local
public interface IColetaDAO {

	public List<Coleta> obter() throws ColetaNaoEncontradaException;
	
	public List<Coleta> obter (Date dataInicial, Date dataFinal, Pessoa pessoa, String nomeCliente, String nomeLocal, String idPagamento, Integer position, Integer max) throws ColetaNaoEncontradaException;

	public Coleta obter(int id) throws ColetaNaoEncontradaException;

	public List<Coleta> obter(Integer position, Integer max)	throws ColetaNaoEncontradaException;

	public Coleta inserir(Coleta coleta);

	public Coleta alterar(Coleta coleta);

	public void remover(Coleta coleta);

}