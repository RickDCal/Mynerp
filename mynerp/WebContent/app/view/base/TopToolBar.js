Ext.define('Mynerp.view.base.TopToolBar', {
	extend: 'Ext.toolbar.Toolbar',
	xtype: 'top-tool-bar',

	requires: [
		'Mynerp.util.Glyphs'
	],

	dock: 'top',

	items: [
		{
			xtype: 'button',
			text: 'Add',
			itemId: 'add',
			glyph: Mynerp.util.Glyphs.getGlyph('add'),
			listeners: {
				click: 'onAdd'
			}
		}
	]
});