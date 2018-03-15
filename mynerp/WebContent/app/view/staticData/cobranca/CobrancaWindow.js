Ext.define('Mynerp.view.staticData.Cobranca.CobrancaWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
    ],

	alias: 'widget.cobranca-window',

	height: 340,
	width: 520,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'cobranca-form',
			reference: 'cobrancaForm'
		}		
	]
});