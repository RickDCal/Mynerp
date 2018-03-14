Ext.define('Mynerp.model.security.Base', {
	extend: 'Mynerp.model.Base',

	identifier: 'uuid',

	fields: [
		//{name: 'id', type: 'int' , defaultValue: 0}
		{name: 'id'} // string por causa do uuid

	]

});