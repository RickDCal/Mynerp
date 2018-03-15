Ext.define('Mynerp.view.cadastro.fornecedor.FornecedorWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
    ],

	alias: 'widget.fornecedor-window',

	height: 580,
	width: 800,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'fornecedor-form',
			reference: 'fornecedorForm'
		}		
	]
});