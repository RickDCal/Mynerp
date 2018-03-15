Ext.define('Mynerp.view.listaSelecao.cobranca.ListaCobrancaWindow', {
	extend: 'Mynerp.view.listaSelecao.base.WindowForm',

	alias: 'widget.lista-cobranca-window',

	requires: [
		'Mynerp.view.listaSelecao.cobranca.ListaCobrancasGrid'
	],

	scrollable: true,

	title: 'Formas de Cobrança',
	
	items: [ //itens da janela
		{
			xtype: 'lista-cobrancas-grid',
			reference: 'refListaCobranca'
		}		
	]
});