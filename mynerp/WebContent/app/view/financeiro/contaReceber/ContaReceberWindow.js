Ext.define('Mynerp.view.financeiro.contaReceber.ContaReceberWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs',
   		'Mynerp.view.financeiro.contaReceber.ContaReceberModel'
    ],

	alias: 'widget.conta-receber-window',

	height: 575,
	width: 800,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'conta-receber-form',
			reference: 'contaReceberForm'
		}		
	]
});