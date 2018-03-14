Ext.define('Mynerp.view.comercial.coleta.ColetaWindow', {
	extend: 'Mynerp.view.base.WindowForm',

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
    ],

	alias: 'widget.coleta-window',

	height: 600,
	width: 800,

	closable: false,
	modal: true,
	scrollable: true,

	items: [ //itens da janela
		{
			xtype: 'coleta-form',
			reference: 'coletaForm'
		}		
	]
});