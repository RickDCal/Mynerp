Ext.define('Mynerp.view.financeiro.contaPagar.ContaPagarWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs',
   		'Mynerp.view.financeiro.contaPagar.ContaPagarModel'
    ],

	alias: 'widget.conta-pagar-window',

	height: 575,
	width: 800,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'conta-pagar-form',
			reference: 'contaPagarForm'
		}		
	]
});