Ext.define('Mynerp.model.menu.Accordion', {
	extend: 'Ext.data.Model',
	
	requires: [
	 	'Mynerp.model.menu.TreeNode'
	 	],

	fields: [
	 	{name: 'id', type: 'int'},
	 	{name: 'text'},
	 	{name: 'iconCls'}
	 ],

	 idProperty: 'id', // opcional e eu coloquei pra não esquecer que existe. só é necessário se o campo de id tiver nome diferente de 'id'

	 hasMany: {
		model: 'Mynerp.model.menu.TreeNode',
	 	foreignKey: 'parent_id',
	 	name: 'items'
	 }
});