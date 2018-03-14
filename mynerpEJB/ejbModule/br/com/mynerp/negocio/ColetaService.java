package br.com.mynerp.negocio;



import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mynerp.negocio.exception.ColetaInexistenteException;
import br.com.mynerp.negocio.exception.DiretorioNaoEncontradoException;
import br.com.mynerp.negocio.exception.FalhaAoConverterDataException;
import br.com.mynerp.negocio.exception.FalhaAoCriarJSONException;
import br.com.mynerp.negocio.exception.FalhaAoGerarFinanceiroException;
import br.com.mynerp.persistencia.Cliente;
import br.com.mynerp.persistencia.Coleta;
import br.com.mynerp.persistencia.Contato;
import br.com.mynerp.persistencia.Endereco;
import br.com.mynerp.persistencia.ParametroColeta;
import br.com.mynerp.persistencia.dao.IColetaDAO;
import br.com.mynerp.persistencia.dao.IGenericDAO;
import br.com.mynerp.persistencia.dao.exception.ColetaNaoEncontradaException;
import br.com.mynerp.persistencia.dao.exception.ObjetoNaoEncontradoException;
import br.com.mynerp.util.Utilities;



@Stateless
public class ColetaService implements IColetaServiceLocal {

	@EJB
	private IColetaDAO coletaDao;

	@EJB
	private IGenericDAO genericDao;

	@EJB
	private IContaServiceLocal contaService;

	@EJB
	private IPessoaServiceLocal pessoaService;

	public ColetaService() {

	}	

	public List<Coleta> pesquisar(Date dataInicial, Date dataFinal, Cliente cliente, String nomeCliente, String nomeLocal, String idPagamento, Integer position, Integer max) throws ColetaInexistenteException {		

		try {
			return coletaDao.obter(dataInicial, dataFinal, cliente, nomeCliente, nomeLocal, idPagamento, position, max);				
		}catch (ColetaNaoEncontradaException e){
			throw new ColetaInexistenteException();				
		}
	}	

	public List<Coleta> pesquisar (Integer position, Integer max) throws ColetaInexistenteException {		

		try {
			return coletaDao.obter(position, max);				
		}catch (ColetaNaoEncontradaException e){
			throw new ColetaInexistenteException();				
		}
	} 


	public Coleta pesquisar (int id) throws ColetaInexistenteException {


		try {
			return coletaDao.obter(id);				
		}catch (ColetaNaoEncontradaException e){
			throw new ColetaInexistenteException();				
		}
	} 

	public void cadastrar (Coleta coleta) throws ColetaInexistenteException {
		coletaDao.inserir((Coleta)coleta);
	}	

	public void atualizar (Coleta coleta) throws ColetaInexistenteException {
		coletaDao.alterar((Coleta)coleta);
	} 

	public void remover (Coleta coleta) throws ColetaInexistenteException {
		coletaDao.remover((Coleta)coleta);
	} 

	public JsonObject coletaJSON(Coleta coleta) throws FalhaAoCriarJSONException {


		Gson gson = new Gson();

		List<Contato> contatos = new ArrayList<Contato>();
		List<Endereco> enderecos = new ArrayList<Endereco>();

		coleta.getCliente().setContatos(contatos);
		coleta.getCliente().setEnderecos(enderecos);
		Cliente cliente = new Cliente();
		cliente.setId(coleta.getCliente().getId());
		cliente.setNome(coleta.getCliente().getNome());
		if (coleta.getCondicaoPagamento() != null) {
			coleta.getCondicaoPagamento().setParcelas(null);
		}		

		int geraCr = 1;
		if (coleta.getConta() == null) {
			geraCr = 0;
		}

		coleta.setConta(null);

		coleta.setCliente(cliente);
		String coletaJson = gson.toJson(coleta);

		JsonParser jp = new JsonParser();		

		JsonObject jo = (JsonObject) jp.parse(coletaJson);
		jo.addProperty("idPessoa", coleta.getCliente().getId());
		jo.addProperty("nomePessoa", coleta.getCliente().getNome());
		jo.remove("cliente");
		jo.addProperty("indGeraCR", geraCr);
		if (coleta.getCondicaoPagamento() != null) {
//			por enquanto não vou usar condição de pagamento, depois te/ que colocar
//			jo.addProperty("idCondicao", coleta.getCondicaoPagamento().getId());
//			jo.addProperty("nomeCondicao",coleta.getCondicaoPagamento().getNome());
			jo.remove("condicaoPagamento");
		}
		if (coleta.getCobranca() != null) {
			jo.addProperty("idCobranca", ""+coleta.getCobranca().getId());
			jo.addProperty("nomeCobranca", coleta.getCobranca().getNome());
			jo.remove("cobranca");
		}
		
		if (coleta.getDataVencimento() == null) {
			jo.add("dataVencimento", null);
		}
		
		
		return jo;		

	}

	public List<Coleta> gerarColetaXML(File arquivoXml, String nomeDiretorio, ParametroColeta parametroColeta) throws DiretorioNaoEncontradoException, NamingException, ObjetoNaoEncontradoException {

		List<Coleta> coletas = new ArrayList<Coleta>();

		Coleta coleta = null;

		File pasta = null;
		
		if (parametroColeta != null && parametroColeta.getCaminhoXmlCte() != null) {
		 pasta = new File(parametroColeta.getCaminhoXmlCte());	
		}

		File[] arquivosPasta = null;
		File pastaImportados = new File(parametroColeta.getCaminhoXmlCte()+ File.separator + "processados");

		if (!pastaImportados.isDirectory()) {
			pastaImportados.mkdir(); //mkdir é o comando de criação da pasta 
		}

		if (arquivoXml != null) {
			arquivosPasta = new File[1];
			arquivosPasta[0] = arquivoXml;
		}
		
		if (pasta != null) {
			if(pasta.isDirectory()){
				arquivosPasta = pasta.listFiles(
						new FileFilter() { //quero só se for xml
							public boolean accept(File arq) {
								return arq.getName().endsWith(".xml");
							}
						});
			} else {
				throw new DiretorioNaoEncontradoException(pasta.getAbsolutePath());
			}
		} else {
			return coletas;
		}

		Utilities utilService= new Utilities();
		File velhoArquivo = null;

		/*ler cada arquivo xml e gerar coletas*/
		for(File arquivo : arquivosPasta){
			velhoArquivo = arquivo;

			Integer numeroConhecimento = null;
			String chaveAcesso = null;
			Date dataEmissao =  null;			
			String nomeTomador = null;
			String cnpjTomador = null;
			String nomeFantasiaTomador = null;
			String cidadeOrigem = null;
			String cidadeDestino = null;
			String observacao = null;
			//String cnpjRemetente = null;
			String nomeRemetente = null;
			String cnpjDestinatario = null;
			String nomeDestinatario = null;
			Double valor = null;
			//String tipoVolume = null;
			Integer qtdVolume = null;
			Date prevEntrega = null;

			boolean bNct = false;
			boolean bChave = false;
			boolean bEmissao = false;
			boolean bTomador = false;
			boolean bNomeTomador = false;
			boolean bCnpjTomador = false;
			boolean bFantasiaTomador = false;
			boolean bCidInicio = false;
			boolean bCidFim = false;
			boolean bObs = false;
			boolean bRem = false;
			//boolean bCnpjRemetente = false;
			boolean bNomeRemetente = false;
			boolean bDest = false;
			boolean bCnpjDestinatario = false;
			boolean bNomeDestinatario = false;
			boolean bValor = false;
			//boolean bTipoVolume = false;
			boolean bQtdVolume = false;
			boolean bPrevEntrega = false;

			try {
				XMLInputFactory factory = XMLInputFactory.newInstance();
				XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(arquivo));

				while(eventReader.hasNext()){
					XMLEvent event = eventReader.nextEvent();

					switch(event.getEventType()){

					case XMLStreamConstants.START_ELEMENT:
						StartElement startElement = event.asStartElement();
						String qName = startElement.getName().getLocalPart();

						switch (qName) { /*switch com string só funciona a aprtir do java 7*/
						case "infCte": /*deixando este primeiro só para exemplo da funcionalidade*/
							break;
						case "nCT" : bNct = true; break;							
						case "chCTe": bChave = true; break; 
						case "dhEmi": bEmissao = true; break; 
						case "toma4": bTomador = true; break; 
						case "xMunIni": bCidInicio = true; break; 
						case "xMunFim": bCidFim = true;	break; 
						case "xObs": bObs = true; break; 
						case "rem":	bRem = true; break; 
						case "dest": bDest = true; break; 
						case "vTPrest": bValor = true; break;  
						//case "tpMed": bTipoVolume = true; break; 
						case "qCarga": bQtdVolume = true; break; 
						case "dPrev": bPrevEntrega = true;
						case "xNome": 
							if (bTomador) {
								bNomeTomador = true;
							}
							if (bRem) {
								bNomeRemetente = true;
							}
							if (bDest) {
								bNomeDestinatario = true;
							};
							break;
						case "xFant": 
							if (bTomador) {
								bFantasiaTomador = true;
							}
							break;
						case "CNPJ": 
							if (bTomador) {
								bCnpjTomador = true;
							}
							//							if (bRem) {
							//								bCnpjRemetente = true;
							//							}
							if (bDest) {
								bCnpjDestinatario = true;
							};
							break;						
						default: break;
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						Characters characters = event.asCharacters();
						if(bNct){
							numeroConhecimento = Integer.parseInt(characters.getData());
							bNct = false;
						}
						if(bChave){
							chaveAcesso = characters.getData();
							bChave = false;
						}
						if(bEmissao){
							dataEmissao = utilService.dataYYYY_MM_DDTHHppmmppss(characters.getData());
							bEmissao = false;
						}
						if(bNomeTomador){
							nomeTomador = characters.getData();
							bNomeTomador = false;
						}
						if(bFantasiaTomador){
							nomeFantasiaTomador = characters.getData();
							bFantasiaTomador = false;
						}
						if(bCnpjTomador){
							cnpjTomador = characters.getData();
							bCnpjTomador = false;
						}
						if(bCidInicio){
							cidadeOrigem = characters.getData();
							bCidInicio = false;
						}
						if(bCidFim){
							cidadeDestino = characters.getData();
							bCidFim = false;
						}
						if(bObs){
							observacao = characters.getData();
							bObs = false;
						}
						//						if(bCnpjRemetente){
						//							cnpjRemetente = characters.getData();
						//							bCnpjRemetente = false;
						//						}
						if(bNomeRemetente){
							nomeRemetente = characters.getData();
							bNomeRemetente = false;
						}
						if(bCnpjDestinatario){
							cnpjDestinatario = characters.getData();
							bCnpjDestinatario = false;
						}
						if(bNomeDestinatario){
							nomeDestinatario = characters.getData();
							bNomeDestinatario = false;
						}
						if(bValor){
							valor = Double.valueOf(characters.getData());
							bValor = false;
						}
						//						if(bTipoVolume){
						//							tipoVolume = characters.getData();
						//							bTipoVolume = false;
						//						}
						if(bQtdVolume){
							qtdVolume = Double.valueOf(characters.getData()).intValue();
							bQtdVolume = false;
						}
						if(bPrevEntrega){
							prevEntrega = utilService.dataYYYY_MM_DD(characters.getData());
							bPrevEntrega = false;
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						EndElement endElement = event.asEndElement();
						String elementName = endElement.getName().getLocalPart();

						switch (elementName) {
						case "toma4" : bTomador = false; break;
						case "rem" : bRem = false; break;
						case "dest" :bDest = false; break;
						default: break;
						}
					}
				}
				coleta = new Coleta();
				coleta.setChaveAcessoCTE(chaveAcesso);
				coleta.setCidade("De " + cidadeOrigem + " para " + cidadeDestino);				
				coleta.setData(prevEntrega != null ? prevEntrega : dataEmissao);
				coleta.setDataVencimento(prevEntrega != null ? prevEntrega : dataEmissao);
				coleta.setIdCTE(numeroConhecimento);
				coleta.setObservacao(observacao);
				coleta.setQuantidadeVolume(qtdVolume);
				coleta.setSeuNumero("" + numeroConhecimento);
				coleta.setValorFrete(valor);

				String local = null;

				if (cnpjTomador == cnpjDestinatario) {
					local = nomeRemetente;
				} else {
					local = nomeDestinatario;
				}			

				coleta.setNomeLocal(local);

				Cliente cliente = new Cliente();

				try {
					List<Cliente> clientes =  pessoaService.pesquisarClientesCnpj(cnpjTomador, 0, 1);					 

					if (clientes != null && clientes.size() >0 ) {
						for (int i = 0; i < clientes.size(); i++) {
							cliente = clientes.get(i);						
						}
					} else {
						cliente.setCpfCnpj(cnpjTomador);
						cliente.setDataCadastro(new Date());
						cliente.setIdsexo(1);
						cliente.setNome(nomeTomador);
						cliente.setNomeFantasia(nomeFantasiaTomador);
						if (cnpjTomador.length() > 11) {
							cliente.setPfPj(1);
						} else {
							cliente.setPfPj(2);
						}
						genericDao.inserir(cliente);
					}

					coleta.setCliente(cliente);

				} catch (Exception e) {
					e.printStackTrace();
				} 

				this.cadastrar(coleta);
				String ObsCR = "Referente coleta conhecimento" + coleta.getIdCTE();

				contaService.geraContaReceber(cliente, coleta, parametroColeta.getCondicaoPadraoColeta(), 
						parametroColeta.getCobrancaPadraoColeta(), coleta.getValorFrete(), coleta.getData(), 
						""+coleta.getIdCTE(),ObsCR, true);
				//contaService.cadastrar(contaReceber); desnecessário pois o método acima já grava no banco de dados.

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FalhaAoConverterDataException e) {
				e.printStackTrace();
			} catch (ColetaInexistenteException e) {
				e.printStackTrace();
			} catch (FalhaAoGerarFinanceiroException e) {
				e.printStackTrace();
			}

			if (coleta != null && coleta.getId() > 0) {
				coletas.add(coleta);

				FileChannel sourceChannel = null;
				FileChannel destinationChannel = null;

				try {
					sourceChannel  = new FileInputStream(arquivo).getChannel();
					File novoArquivo = new File(pastaImportados+File.separator+arquivo.getName());
					destinationChannel = new FileOutputStream(novoArquivo).getChannel();
					sourceChannel.transferTo(0, sourceChannel.size(),destinationChannel);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (sourceChannel!= null && sourceChannel.isOpen()) {
						try {
							sourceChannel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (destinationChannel != null && destinationChannel.isOpen()) {
						try {
							destinationChannel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}			
			}

			if (velhoArquivo != null && velhoArquivo.isFile()) {
				if(!velhoArquivo.delete()) {
					System.out.println("falha ao deletar o arquivo " + velhoArquivo.getName());
				};
			}
		}
		return coletas;
	}

}



