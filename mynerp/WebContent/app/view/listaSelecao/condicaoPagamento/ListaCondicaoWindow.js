Ext.define('Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicaoWindow', {
	extend: 'Mynerp.view.listaSelecao.base.WindowForm',

	alias: 'widget.lista-condicao-window',

	requires: [
		'Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesGrid'
	],

	scrollable: true,

	title: 'Condições de Pagamento',
	
	items: [ //itens da janela
		{
			xtype: 'lista-condicoes-grid',
			reference: 'refListaCondicao'
		}		
	]
});