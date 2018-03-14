Ext.define('Mynerp.view.listaSelecao.base.Grid', {
	extend: 'Ext.grid.Panel',

	requires: [
	'Mynerp.util.Glyphs'
	],

	columnLines: true,

	viewConfig: {
		stripeRows: true
	},

	listeners: {
        rowdblclick: 'onListaSelecaoDbClick'
    },

	initComponent: function () {
		var me = this;

		me.columns = Ext.Array.merge(
			
			[{
				text: 'Código',
				width: 80,
				dataIndex: 'id',
				filter: true
			},
			{
				text: 'Nome',
				width: 230,
				dataIndex: 'nome',
				filter: true
			}
			],
			me.columns
		);

		me.callParent(arguments);
	}

});