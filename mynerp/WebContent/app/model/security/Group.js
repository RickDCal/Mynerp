Ext.define('Mynerp.model.security.Group', {
	extend: 'Mynerp.model.security.Base',

	entityName: 'Group', //security.Group era o padrão se não tivesse declarado aqui 

	fields: [
		{name: 'name'}
	]
});