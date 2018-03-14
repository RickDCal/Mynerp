Ext.define('Mynerp.view.security.UsersGrid', {
	extend: 'Ext.grid.Panel',

	alias: 'widget.users-grid',

	bind: '{users}',

	reference: 'usersGrid',

	columns: [

		{
			width: 150,
			dataIndex: 'user',
			text: 'Username'
		},
		{
			width: 200,
			dataIndex: 'apelido',
			flex: 1,
			text: 'Name'
		},
		{
			width: 250,
			dataIndex: 'email',
			text: 'E-mail'
		},
		{
			width: 150,
			dataIndex: 'idPerfil',
			text: 'Group'
		}
	],

	listeners: {
		rowdblclick: 'onDbClick'
	}

});