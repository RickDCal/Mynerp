Ext.define('Mynerp.view.listaSelecao.cliente.ListaClienteWindow', {
	extend: 'Mynerp.view.listaSelecao.base.WindowForm',

	alias: 'widget.lista-cliente-window',

	requires: [
		'Mynerp.view.listaSelecao.cliente.ListaClientesGrid'
	],

	scrollable: true,
	
	items: [ //itens da janela
		{
			xtype: 'lista-clientes-grid',
			reference: 'refListaCliente'
		}		
	]
});