package br.com.mynerp.persistencia.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;

@Local
public interface IEmpresaDAO {

	public Empresa obter(int id) throws EmpresaNaoEncontradaException;

	public List<Empresa> obter() throws EmpresaNaoEncontradaException;

}