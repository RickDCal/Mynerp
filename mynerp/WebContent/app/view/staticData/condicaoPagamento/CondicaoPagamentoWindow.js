Ext.define('Mynerp.view.staticData.CondicaoPagamento.CondicaoPagamentoWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
    ],

	alias: 'widget.condicao-pagamento-window',

	height: 500,
	width: 520,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'condicao-pagamento-form',
			reference: 'condicaoPagamentoForm'
		}		
	]
});