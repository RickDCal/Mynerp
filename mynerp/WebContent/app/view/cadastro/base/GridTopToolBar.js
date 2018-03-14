Ext.define('Mynerp.view.cadastro.base.GridTopToolBar', {
	extend: 'Ext.toolbar.Toolbar',
	xtype: 'cadastros-grid-toolbar',

	requires: [
		'Mynerp.util.Glyphs'
	],

	dock: 'top',

	initComponent: function () {
		var me = this;

		me.items = Ext.Array.merge(		

			[
				{
				xtype: 'button',
				reference: 'addGridButton',
				text: 'Novo',
				itemId: 'add',
				glyph: Mynerp.util.Glyphs.getGlyph('add'),
				listeners: {
					click: 'onAdd'
					}				
				},
				{
				xtype: 'button',
				reference: 'editGridButton',
				text: 'Editar',
				itemId: 'edit',
				glyph: Mynerp.util.Glyphs.getGlyph('edit'),
				listeners: {
					click: 'onEdit'
					}				
				},
				{
				xtype: 'button',
				reference: 'deleteGridButton',
				text: 'Excluir',
				itemId: 'delete',
				glyph: Mynerp.util.Glyphs.getGlyph('destroy'),
				listeners: {
					click: 'onDeleteFromToolbar'
					}				
				},
				{ xtype: 'tbseparator' } // pipe separador
					
			],

			me.items
		);
		me.callParent(arguments);
	}
	
});