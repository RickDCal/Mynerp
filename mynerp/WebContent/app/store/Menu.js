Ext.define('Mynerp.store.Menu', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.util.Util'
	],

	model: 'Mynerp.model.menu.Accordion',

	proxy: {
		type: 'ajax',
		url: 'menuServlet',

		reader: {
			type: 'json',
			rootProperty: 'data'
		},

		listeners: {
			exception: function(proxy, response, operation) {
				Mynerp.util.Util.showErrorMsg(response.responseText);
			}
		}
	}
});