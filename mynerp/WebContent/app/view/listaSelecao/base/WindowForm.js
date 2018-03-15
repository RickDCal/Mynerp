Ext.define('Mynerp.view.listaSelecao.base.WindowForm', {
	extend: 'Ext.window.Window',

	alias: 'widget.listawindowform',

	requires: [
		'Mynerp.util.Util',
		'Mynerp.util.Glyphs',
		'Mynerp.view.listaSelecao.base.CancelOkToolbar'
	],

	chamador: null,

	/* tipo de campo que chamou a janela
	 VALORES ACEITOS:
	 1 - Campo de codigo em um form;
	 2 - Campo de nome em um form
	 3 - Célula de código em um grid - com editor
	 4 - Célula de nome em um grid - com editor
	 */

	height: 400,
	width: 750,
	autoScroll: true,
	layout: {
		type: 'fit'
	},
	modal: true,
	closable: false,
	campoCodigo: null, //campo que chamou a janelinha
	campoNome: null, //campo que vai receber o nome

	bind: { // bind serve para pegar dados do model
		title: '{title}',
		glyph: '{glyph}'
	},

	dockedItems: [
		{
			xtype: 'cancel-ok-toolbar'
		}
	]
});