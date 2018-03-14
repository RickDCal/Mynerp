Ext.define('Mynerp.view.cadastro.cliente.ClienteWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
    ],

	alias: 'widget.cliente-window',

	height: 580,
	width: 800,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'cliente-form',
			reference: 'clienteForm'
		}		
	]
});