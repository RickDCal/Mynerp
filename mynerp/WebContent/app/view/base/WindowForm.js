Ext.define('Mynerp.view.base.WindowForm', {
	extend: 'Ext.window.Window',

	alias: 'widget.windowform',

	requires: [
		'Mynerp.util.Util',
		'Mynerp.util.Glyphs',
		'Mynerp.view.base.CancelSaveToolbar'
	],

	height: 600,
	width: 800,
	autoScroll: true,
	layout: {
		type: 'fit'
	},
	modal: true,
	closable: false,

	bind: { // bind serve para pegar dados do model
		title: '{title}',
		glyph: '{glyph}'
	},

	dockedItems: [
		{
			xtype: 'cancel-save-toolbar'
		}
	]
});