package br.com.mynerp.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.mynerp.persistencia.Empresa;
import br.com.mynerp.persistencia.dao.exception.EmpresaNaoEncontradaException;

@Local
public interface IEmpresaServiceLocal {

	public Empresa pesquisar(int id) throws EmpresaNaoEncontradaException;

	public List<Empresa> pesquisar() throws EmpresaNaoEncontradaException;

}