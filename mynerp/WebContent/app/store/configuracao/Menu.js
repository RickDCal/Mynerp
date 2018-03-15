Ext.define('Mynerp.store.configuracao.Menu', {
	extend: 'Ext.data.TreeStore',

	alias: 'store.menu-configuracoes',

	fields: [
        {name: 'position', type: 'int'}, // deixando string por causa do uuid
		{name: 'text'},
		{name: 'iconCls'},
		{name: 'className'}
	],

	autoLoad: true,

	proxy: {
		type: 'ajax', //assíncrono
		url: 'parametroServlet?action=5',

		reader: {
			type: 'json',
			rootProperty: 'data'
		},

		listeners: {
			exception: function(proxy, response, operation) {
				Mynerp.util.Util.showErrorMsg(response.responseText);
			}
		}
	},

	listeners: {
		load: function( store, records, successful, operation, node, eOpts) {
			if (records && records.length > 0) {
				var menuConfig= Ext.ComponentQuery.query('config-menu')[0];
				treeView = menuConfig.getView();
				treeView.bindStore(store); //fazendo a ligação da store depois que ela carreg pois qundo a tree pega a stroe pelo primeira 
				//ves a store está vazia pois ajax é assíncrono. quando carrega os registros o treeview já foi montado com a tree vazia.
			}
		}
	}
});