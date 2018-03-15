Ext.define('Mynerp.controller.Menu', {
	extend: 'Ext.app.Controller',

	stores: [
		'Menu'
	],

	refs: [
		{
			ref: 'mainPanel',
			selector: 'mainpanel'
		}
	],

	renderDynamicMenu: function(view, options) {

		var dynamicMenus = [];

		view.body.mask('Loading Menus... please wait...'); 

		this.getMenuStore().load(function(records, op, success) {
		//getMenuStore é criado pelo controller, pois eu passei uma store pra ele aí ele monta get + nome da store + Store
		// o método load da store permite receber uma função como callback passada no parâmetro, o callback é executado qdo a store carrega 
		//esta linha é bvem parecida com a da documerntação da store
			Ext.each(records, function(root) {

				var menu = Ext.create('Mynerp.view.menu.Tree', {
					title: root.get('text'),
					//title: translations[root.get('text')],
					iconCls: root.get('iconCls')
				});

				var treeNodeStore = root.items(),
				nodes = [],
			item;

				for (var i = 0; i <treeNodeStore.getCount(); i++) {
					item = treeNodeStore.getAt(i);

					nodes.push({
						text: item.get('text'),
						//text: translations[item.get('text')],
						leaf: true,
						glyph: item.get('iconCls'),
						id: item.get('id'),
						className: item.get('className')
					});

				}
			menu.getRootNode().appendChild(nodes); // getRootNode é método de Ext.tree.Panel da qual Mynerp.view.Tree extend
			dynamicMenus.push(menu); //  ext.array ...push: Adds one or more elements to the end of an array and returns the new length of the array
			});
			view.add(dynamicMenus);
			view.body.unmask();
		});
	},

	onTreePanelItemClick: function(view, record, item, index, event, options) {

		var mainPanel = this.getMainPanel();
		var newTab = mainPanel.items.findBy(
			function (tab) {
				return tab.title === record.get('text');
			});

		if (!newTab) {
			newTab = mainPanel.add({
				xtype: record.get('className'),
				closable: true,
				glyph: record.get('glyph') + '@FontAwesome',
				title: record.get('text')
			});

		}

		mainPanel.setActiveTab(newTab);

		},

	init: function(application) {

		this.control({

			"menutree": {
				itemclick: this.onTreePanelItemClick
			},
			"mainmenu": {
				render: this.renderDynamicMenu
			}

		});
		
	}

});