// Ext.define('Mynerp.model.TextCombo', {
// 	extend: 'Ext.data.Model',

// 	idProperty: 'id', /// aqui estava como text
// 	fields: [
// 		{name: 'id'},
// 		{name: 'text'}
		
// 	]
// });

Ext.define('Mynerp.model.TextCombo', {
    extend: 'Ext.data.Model',

    idProperty: 'text',

    fields: [
        { name: 'text' }
    ]
});