Ext.define('Mynerp.view.listaSelecao.fornecedor.ListaFornecedorWindow', {
	extend: 'Mynerp.view.listaSelecao.base.WindowForm',

	alias: 'widget.lista-fornecedor-window',

	requires: [
		'Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresGrid'
	],

	scrollable: true,
	
	items: [ //itens da janela
		{
			xtype: 'lista-fornecedores-grid',
			reference: 'refListaFornecedor'
		}		
	]
});