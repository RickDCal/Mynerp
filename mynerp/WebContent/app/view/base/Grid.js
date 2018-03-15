Ext.define('Mynerp.view.base.Grid', {
	extend: 'Ext.grid.Panel',

	requires: [
	'Mynerp.util.Glyphs'
	],

	columnLines: true,

	viewConfig: {
		stripeRows: true
	},

	initComponent: function () {
		var me = this;

		me.columns = Ext.Array.merge(
			me.columns,

			[{
				text: 'Código',
				width: 150,
				dataIndex: 'id',
				filter: true
			},
			{
				xtype: 'widgetcolumn', 
				width: 50,
				sortable: false,
				menuDisabled: true,
				widget: {
					xtype: 'button',
					glyph: Mynerp.util.Glyphs.getGlyph('edit'),
					tooltip: 'Edit',
					handler: 'onEdit' // atenção
				}
			},
			{
				xtype: 'widgetcolumn', 
				width: 50,
				sortable: false,
				menuDisabled: true,
				widget: {
					xtype: 'button',
					glyph: Mynerp.util.Glyphs.getGlyph('destroy'),
					tooltip: 'Delete',
					handler: 'onDelete' // atenção
				}
			}]
		);

		me.callParent(arguments);
	}

});