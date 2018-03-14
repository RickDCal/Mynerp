package br.com.mynerp.persistencia;

import java.util.Date;
import javax.persistence.*;

@Entity  // pra importar isso precisei inserir biblioteca de runtime do jboss 6 no projeto ejb - javax persistence
@Table(name="coleta")// o nome da tabela correspondente quando for entity
//@Inheritance(strategy=InheritanceType.JOINED)


public class Coleta {
	
	private int id;
	private Date data;
	private String nomeLocal;
	private String cidade;
	private Integer quilometragem;
	private String numeroPedido;
	private String numeroNota;
	private Double valorMercadoria;
	private Integer quantidadeVolume;
	private Double peso;
	private Double valorFrete;
	private Date dataVencimento;
	private String observacao;	
	private Pessoa cliente;
	private String seuNumero;
	private Integer idCTE;
	private Conta conta;
	private String chaveAcessoCTE;
	private String clientIdProperty;
	private CondicaoPagamento condicaoPagamento;
	private Cobranca cobranca;
	
	public Coleta() {
		
	}
		
	@Id //indica chave primária
	@Column(name="id", columnDefinition="int")//nome da coluna no bd
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incermento na chave
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="data", columnDefinition="datetime")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name="nome_local", columnDefinition="nvarchar")
	public String getNomeLocal() {
		return nomeLocal;
	}
	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
	
	@Column(name="cidade", columnDefinition="nvarchar")
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Column(name="quilometragem", columnDefinition="int")
	public Integer getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}
	
	@Column(name="numero_pedido", columnDefinition="nvarchar")
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	@Column(name="numero_nota", columnDefinition="nvarchar")
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	
	@Column(name="valor_mercadoria", columnDefinition="float")
	public Double getValorMercadoria() {
		return valorMercadoria;
	}
	public void setValorMercadoria(Double valorMercadoria) {
		this.valorMercadoria = valorMercadoria;
	}
	
	@Column(name="quantidade_volume", columnDefinition="int")
	public Integer getQuantidadeVolume() {
		return quantidadeVolume;
	}
	public void setQuantidadeVolume(Integer quantidadeVolume) {
		this.quantidadeVolume = quantidadeVolume;
	}
	
	@Column(name="peso", columnDefinition="float")
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	@Column(name="valor_frete", columnDefinition="float")
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	@Column(name="data_vencimento", columnDefinition="date")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column(name="observacao", columnDefinition="nvarchar")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@OneToOne
	@JoinColumn(name = "idpessoa")
	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	@Column(name = "seu_numero", columnDefinition="nvarchar")
	public String getSeuNumero() {
		return seuNumero;
	}
	public void setSeuNumero(String seuNumero) {
		this.seuNumero = seuNumero;
	}
	
	@Column(name = "idcte", columnDefinition="int")
	public Integer getIdCTE() {
		return idCTE;
	}
	public void setIdCTE(Integer idCTE) {
		this.idCTE = idCTE;
	}
	
	
	@OneToOne(mappedBy = "coleta", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Column(name = "chave_acesso_cte", columnDefinition="nvarchar")
	public String getChaveAcessoCTE() {
		return chaveAcessoCTE;
	}

	public void setChaveAcessoCTE(String chaveAcessoCTE) {
		this.chaveAcessoCTE = chaveAcessoCTE;
	}
	
	@Column(name="uuid", columnDefinition="nvarchar")
	public String getClientIdProperty() {
		return clientIdProperty;
	}

	public void setClientIdProperty(String clientIdProperty) {
		this.clientIdProperty = clientIdProperty;
	}
	
	@OneToOne
	@JoinColumn(name= "idcondicao", columnDefinition="int")
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}
	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	@OneToOne
	@JoinColumn(name = "idcobranca", columnDefinition="int")
	public Cobranca getCobranca() {
		return cobranca;
	}
	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	@Override
	public String toString() {
	
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Código: " + getId() + "\n <br>");
		stringBuilder.append("Data: " + getData() + "\n <br>");
		stringBuilder.append("Nome Local: " + getNomeLocal() + "\n <br>");
		stringBuilder.append("Cidade: " + getCidade() + "\n <br>");
		stringBuilder.append("Quilometragem: " + getQuilometragem() + "\n <br>");
		stringBuilder.append("Pedido: " + getNumeroPedido() + "\n <br>");
		stringBuilder.append("Nota: " + getNumeroNota() + "\n <br>");
		stringBuilder.append("Valor Mercadoria: " + getValorMercadoria() + "\n <br>");
		stringBuilder.append("Volumes: " + getQuantidadeVolume() + "\n <br>");
		stringBuilder.append("Peso: " + getPeso() + "\n <br>");
		stringBuilder.append("Valor Frete: " + getValorFrete() + "\n <br>");
		stringBuilder.append("Cond. Pagto: " + getCondicaoPagamento() != null ? getCondicaoPagamento().getNome() : "" + "\n <br>");
		stringBuilder.append("Forma. Cobranca: " + getCobranca() != null ? getCobranca().getNome() : "" + "\n <br>");		
		stringBuilder.append("Observação: " + getObservacao() + "\n <br>");
		return stringBuilder.toString();
	}

		

}
