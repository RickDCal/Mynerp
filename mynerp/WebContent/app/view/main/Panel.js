Ext.define('Mynerp.view.main.Panel', {
	extend: 'Ext.tab.Panel',


	xtype: 'mainpanel',
	activeTab: 0,

	items: [
	        {xtype: 'panel',
	    	 title: 'Home',
	    	 closable: false,
	    	 iconCls: 'fa fa-home fa-lg tabIcon',
	    	 layout: 'fit'}	

	]
});