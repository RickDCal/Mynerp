Ext.define('Mynerp.view.comercial.base.BaseGrid', {
	extend: 'Ext.grid.Panel',

	requires: [
	'Mynerp.util.Glyphs',
	'Mynerp.util.SearchField',//'Ext.ux.form.SearchField',
	'Mynerp.util.SearchFieldCombo',
	'Mynerp.store.comercial.BuscarColetas',
	'Mynerp.model.comercial.Coleta'
	],

	columnLines: true,

	viewConfig: {
		stripeRows: true
	},

	initComponent: function () {
		var me = this;
		// até aqui me.columns são as colunas do grid filho, é um array tbem
		me.columns = Ext.Array.merge( //aqui vamos mudar o me.columns para receber mais colunas juntando com o merge 
		// o merge junta arrays	

			[{ // o primeiro array tem só a coluna de id
				xtype: 'numbercolumn', 
				text: 'Código',
				width: 80,
				dataIndex: 'id',
				format: '000000',
				filter: true,
				locked: true
			}],
			me.columns, // agora as colunas do filho 
			[ //e aqui embaixo as outras colunas de ação
			{
				xtype: 'widgetcolumn', // coluna editar
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
				xtype: 'widgetcolumn', // coluna excluir
				width: 50,
				reference: 'colunaExcluir',
				sortable: false,
				menuDisabled: true,
				widget: {
					xtype: 'button',
					glyph: Mynerp.util.Glyphs.getGlyph('destroy'),
					tooltip: 'Delete',
					handler: 'onDelete'//'onRemove' // atenção
				}
			}]
		);



		me.callParent(arguments);

		// console.log(me);
		// console.log(me.getStore());
	}

});